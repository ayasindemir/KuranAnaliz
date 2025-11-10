package org.ay.demir.kuran.acik;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.ay.demir.kuran.acik.model.AKuranAudio;
import org.ay.demir.kuran.acik.model.AKuranAuthor;
import org.ay.demir.kuran.acik.model.AKuranRootChars;
import org.ay.demir.kuran.acik.model.AKuranRootWord;
import org.ay.demir.kuran.acik.model.AKuranSurah;
import org.ay.demir.kuran.acik.model.AKuranTranlationFootNotes;
import org.ay.demir.kuran.acik.model.AKuranTranslation;
import org.ay.demir.kuran.acik.model.AKuranVerses;
import org.ay.demir.kuran.acik.model.AKuranWords;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;

public class AKuranUtils {

	private static String apiBaseUrl = "https://api.acikkuran.com/";
	private static String rootchars = "rootchars";
	private static String authors = "authors";
	private static String surahs = "surahs";

	public static List<AKuranAuthor> downloadAuthors() throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBaseUrl + authors)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode root = new ObjectMapper().readTree(response.body());

		List<AKuranAuthor> resultList = new ArrayList<AKuranAuthor>();
		for (JsonNode item : root.get("data")) {
			AKuranAuthor author = new AKuranAuthor();
			author.setId(item.get("id").asLong());
			author.setDescr(item.get("description").asText());
			author.setLang(item.get("language").asText());
			author.setName(item.get("name").asText());
			resultList.add(author);
		}

		return resultList;
	}

	public static void downloadSurahs(EntityManager entityManager) throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBaseUrl + surahs)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode root = new ObjectMapper().readTree(response.body());

		for (JsonNode item : root.get("data")) {
			AKuranSurah surah = new AKuranSurah();
			surah.setId(item.get("id").asLong());
			surah.setNameTr(item.get("name").asText());
			surah.setNameEng(item.get("name_en").asText());
			surah.setVerseCount(item.get("verse_count").asInt());
			surah.setPageNumber(item.get("page_number").asInt());
			surah.setNameArabic(item.get("name_original").asText());
			entityManager.merge(surah);

			JsonNode audioNode = item.get("audio");
			AKuranAudio audio = new AKuranAudio();
			audio.setMp3(audioNode.get("mp3").asText());
			audio.setDuration(audioNode.get("duration").asInt());
			audio.setMp3En(audioNode.get("mp3_en").asText());
			audio.setDurationEn(audioNode.get("duration_en").asInt());
			audio.setSurahId(surah.getId());
			entityManager.merge(audio);
		}

		entityManager.flush();
		entityManager.clear();
	}

	public static List<AKuranRootChars> downloadRootChars() throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBaseUrl + rootchars)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode rootChars = new ObjectMapper().readTree(response.body());

		List<AKuranRootChars> resultList = new ArrayList<AKuranRootChars>();
		for (JsonNode item : rootChars.get("data")) {
			AKuranRootChars rootChar = new AKuranRootChars();
			rootChar.setId(item.get("id").asLong());
			rootChar.setLatin(item.get("latin").asText());
			rootChar.setArabic(item.get("arabic").asText());
			resultList.add(rootChar);
		}

		return resultList;
	}

	public static void downloadVersesOnly(Long surahId, EntityManager entityManager)
			throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBaseUrl + "surah/" + surahId)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode root = new ObjectMapper().readTree(response.body());

		JsonNode surah = root.get("data");

		for (JsonNode jVerse : surah.get("verses")) {
			AKuranVerses verse = new AKuranVerses();
			verse.setId(jVerse.get("id").asLong());
			verse.setSurahId(jVerse.get("surah_id").asLong());
			verse.setVerseNumber(jVerse.get("verse_number").asLong());
			verse.setVerse(jVerse.get("verse").asText());
			verse.setPage(jVerse.get("page").asInt());
			verse.setJuzNumber(jVerse.get("juz_number").asInt());
			verse.setTranscription(jVerse.get("transcription").asText());
			entityManager.merge(verse);
			System.out.println("Surah: " + surah.get("id") + " Verse: " + verse.getVerseNumber());
		}

		entityManager.flush();
		entityManager.clear();
	}

	public static List<AKuranVerses> downloadAllVersesTranslations(Long surahId, Long authorId,
			EntityManager entityManager) throws IOException, InterruptedException {

		List<AKuranVerses> resultList = new ArrayList<AKuranVerses>();

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiBaseUrl + "surah/" + surahId + "?author=" + authorId)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode root = new ObjectMapper().readTree(response.body());

		JsonNode surah = root.get("data");

		for (JsonNode jVerse : surah.get("verses")) {
			AKuranTranslation translation = new AKuranTranslation();
			JsonNode jTranslation = jVerse.get("translation");
			translation.setId(jTranslation.get("id").asLong());
			translation.setText(jTranslation.get("text").asText());
			translation.setAuthorId(jTranslation.get("author").get("id").asLong());
			translation.setVerseId(jVerse.get("id").asLong());
			translation.setSurahId(surahId);
			entityManager.merge(translation);
		}
		System.out.println("Surah: " + surah.get("id") + " Author: " + authorId);
		entityManager.flush();
		entityManager.clear();

		return resultList;
	}

	public static void downloadTranslationFootNotes(Long surahId, Long authorId, EntityManager entityManager)
			throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiBaseUrl + "surah/" + surahId + "?author=" + authorId)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode root = new ObjectMapper().readTree(response.body());

		JsonNode surah = root.get("data");

		for (JsonNode jVerse : surah.get("verses")) {
			JsonNode jTranslation = jVerse.get("translation");
			Long trID = jTranslation.get("id").asLong();

			for (JsonNode footNote : jTranslation.get("footnotes")) {
				AKuranTranlationFootNotes fn = new AKuranTranlationFootNotes();
				fn.setId(footNote.get("id").asLong());
				fn.setText(footNote.get("text").asText());
				fn.setNumber(footNote.get("number").asLong());
				fn.setTranslationId(trID);

				if (fn.getId() != null) {
					entityManager.merge(fn);
				}
			}
		}

		entityManager.flush();
		entityManager.clear();
		System.out.println("Surah: " + surah.get("id") + " Author: " + authorId);

	}

	public static void downloadWords(Long surahId, Long verseId, EntityManager entityManager)
			throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiBaseUrl + "surah/" + surahId + "/verse/" + verseId + "/words")).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode root = new ObjectMapper().readTree(response.body());

		for (JsonNode jword : root.get("data")) {
			if (jword == null || jword.isNull()) {
				continue;
			}
			if (jword.get("id") == null || jword.get("id").isNull()) {
				continue;
			}
			AKuranWords word = new AKuranWords();
			word.setId(jword.get("id").asLong());
			word.setSurahId(surahId);
			word.setVerseId(verseId);
			word.setSortNumber(jword.get("sort_number").asLong());
			word.setArabic(jword.get("arabic").asText());
			word.setTurkish(jword.get("turkish").asText());

			JsonNode jRoot = jword.get("root");
			if (jRoot == null || jRoot.isNull()) {
				continue;
			} else {
				AKuranRootWord rootWord = new AKuranRootWord();
				rootWord.setId(jRoot.get("id").asLong());
				rootWord.setLatin(jRoot.get("latin").asText());
				rootWord.setArabic(jRoot.get("arabic").asText());

				entityManager.merge(rootWord);
				word.setRootId(rootWord.getId());

			}

			entityManager.merge(word);
		}

		entityManager.flush();
		entityManager.clear();
	}

}
