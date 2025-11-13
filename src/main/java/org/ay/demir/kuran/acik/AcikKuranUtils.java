package org.ay.demir.kuran.acik;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.ay.demir.kuran.acik.model.Audio;
import org.ay.demir.kuran.acik.model.Author;
import org.ay.demir.kuran.acik.model.RootChar;
import org.ay.demir.kuran.acik.model.RootDiff;
import org.ay.demir.kuran.acik.model.RootWord;
import org.ay.demir.kuran.acik.model.Surah;
import org.ay.demir.kuran.acik.model.TranslationFootNote;
import org.ay.demir.kuran.acik.model.Translation;
import org.ay.demir.kuran.acik.model.Verse;
import org.ay.demir.kuran.acik.model.Word;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;

public class AcikKuranUtils {

	private static String apiBaseUrl = "https://api.acikkuran.com/";
	private static String rootchars = "rootchars";
	private static String authors = "authors";
	private static String surahs = "surahs";

	public static List<Author> downloadAuthors() throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBaseUrl + authors)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode root = new ObjectMapper().readTree(response.body());

		List<Author> resultList = new ArrayList<Author>();
		for (JsonNode item : root.get("data")) {
			Author author = new Author();
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
			Surah surah = new Surah();
			surah.setId(item.get("id").asLong());
			surah.setNameTr(item.get("name").asText());
			surah.setNameEng(item.get("name_en").asText());
			surah.setVerseCount(item.get("verse_count").asInt());
			surah.setPageNumber(item.get("page_number").asInt());
			surah.setNameArabic(item.get("name_original").asText());
			entityManager.merge(surah);

			JsonNode audioNode = item.get("audio");
			Audio audio = new Audio();
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

	public static List<RootChar> downloadRootChars() throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiBaseUrl + rootchars)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode rootChars = new ObjectMapper().readTree(response.body());

		List<RootChar> resultList = new ArrayList<RootChar>();
		for (JsonNode item : rootChars.get("data")) {
			RootChar rootChar = new RootChar();
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
			Verse verse = new Verse();
			verse.setId(jVerse.get("id").asLong());
			verse.setSN(jVerse.get("surah_id").asLong());
			verse.setVN(jVerse.get("verse_number").asLong());
			verse.setVerse(jVerse.get("verse").asText());
			verse.setPage(jVerse.get("page").asInt());
			verse.setJN(jVerse.get("juz_number").asInt());
			verse.setPro(jVerse.get("transcription").asText());
			entityManager.merge(verse);
			System.out.println("Surah: " + surah.get("id") + " Verse: " + verse.getVN());
		}

		entityManager.flush();
		entityManager.clear();
	}

	public static List<Verse> downloadAllVersesTranslations(Long surahId, Long authorId, EntityManager entityManager)
			throws IOException, InterruptedException {

		List<Verse> resultList = new ArrayList<Verse>();

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(apiBaseUrl + "surah/" + surahId + "?author=" + authorId)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode root = new ObjectMapper().readTree(response.body());

		JsonNode surah = root.get("data");

		for (JsonNode jVerse : surah.get("verses")) {
			Translation translation = new Translation();
			JsonNode jTranslation = jVerse.get("translation");
			translation.setId(jTranslation.get("id").asLong());
			translation.setTxt(jTranslation.get("text").asText());
			translation.setAuthorId(jTranslation.get("author").get("id").asLong());
			translation.setVerseId(jVerse.get("id").asLong());
			translation.setVN(jVerse.get("verse_number").asLong());
			translation.setSN(surahId);
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
				TranslationFootNote fn = new TranslationFootNote();
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
			Word word = new Word();
			word.setId(jword.get("id").asLong());
			word.setSN(surahId);
			word.setVN(verseId);
			word.setSort(jword.get("sort_number").asLong());
			word.setAr(jword.get("arabic").asText());
			word.setTr(jword.get("turkish").asText());

			JsonNode jRoot = jword.get("root");
			if (jRoot == null || jRoot.isNull()) {
				// nothing
			} else {
				RootWord rootWord = new RootWord();
				rootWord.setId(jRoot.get("id").asLong());
				rootWord.setLatin(jRoot.get("latin").asText());
				rootWord.setArabic(jRoot.get("arabic").asText());

				entityManager.merge(rootWord);
				word.setRoot(rootWord.getId());
			}

			entityManager.merge(word);
		}

		entityManager.flush();
		entityManager.clear();
	}

	public static void downloadRootDiffs(String latin, EntityManager entityManager)
			throws IOException, InterruptedException {

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.acikkuran.com/root/latin/" + latin))
				.GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode root = new ObjectMapper().readTree(response.body());

		JsonNode rootWord = root.get("data");

		Long rootWordId = rootWord.get("id").asLong();

		for (JsonNode diff : rootWord.get("diffs")) {
			RootDiff rootDiff = new RootDiff();
			rootDiff.setId(diff.get("id").asLong());
			rootDiff.setDiff(diff.get("diff").asText());
			rootDiff.setCount(diff.get("count").asInt());
			rootDiff.setRootId(rootWordId);
			entityManager.merge(rootDiff);
		}

		entityManager.flush();
		entityManager.clear();
		System.out.println("RootWord: " + rootWord.get("arabic"));
	}

}
