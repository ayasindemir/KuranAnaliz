package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.KelimeGrupUpdated;
import org.ay.demir.kuran.acik.model.KokKelime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.EntityManager;

@Service
public class KokKelimeGrupServiceUpdate {

	@Autowired
	private KelimeService kelimeService;

	@Autowired
	private EntityManager entityManager;

	private static final String URL = "https://api.acikkuran.com";

	@Transactional
	public void indir() throws Exception {

		List<KokKelime> rootWordsList = (List<KokKelime>) kelimeService.getAllKokKelime();

		String nextUrl = "/root/latin/";

		for (KokKelime kokKelime : rootWordsList) {
			JsonNode verseParts = AcikKuranUtils.downloadContent(URL + nextUrl + kokKelime.kokLatin + "/verseparts");
			setData(verseParts);
		}
	}

	public void setData(JsonNode verseParts) throws Exception {
		JsonNode jLinks = verseParts.get("links");

		String jNext = null;
		if (jLinks != null&& !jLinks.equals(null)) {
			jNext = jLinks.get("next").asText();
		}

		for (JsonNode jData : verseParts.get("data")) {
			KelimeGrupUpdated updated = new KelimeGrupUpdated();
			updated.id = jData.get("id").asLong();
			updated.rootDiffId = jData.get("rootdiff_id").asLong();

			JsonNode jRoot = jData.get("root");
			updated.rootId = jRoot.get("id").asLong();
			updated.latin = jRoot.get("latin").asText();

			JsonNode jVerse = jData.get("verse");
			updated.verseId = jVerse.get("id").asLong();
			updated.surahId = jVerse.get("surah_id").asLong();
			updated.verseNumber = jVerse.get("verse_number").asLong();

			updated.sortNumber = jData.get("sort_number").asLong();

			entityManager.merge(updated);
			System.out.println(updated.toString());
		}
		
		entityManager.flush();
		entityManager.clear();

		if (jNext != null && !jNext.equals("null")) {
			JsonNode versePartsNext = AcikKuranUtils.downloadContent(URL + jNext);
			setData(versePartsNext);
		}

	}

}
