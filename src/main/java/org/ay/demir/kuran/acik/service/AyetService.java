package org.ay.demir.kuran.acik.service;

import java.util.Collections;
import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Ayet;
import org.ay.demir.kuran.acik.model.Sure;
import org.ay.demir.kuran.acik.repository.AyetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.EntityManager;

@Service
public class AyetService {

	@Autowired
	AyetRepository ayetRepo;

	@Autowired
	SureService sureService;

	private static final String URL = "https://api.acikkuran.com/surah/";

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void indir() throws Exception {

		List<Sure> surahList = sureService.getAll();

		for (Sure surah : surahList) {

			JsonNode root = AcikKuranUtils.downloadContent(URL + surah.id);

			JsonNode jSure = root.get("data");

			for (JsonNode jAyet : jSure.get("verses")) {
				Ayet ayet = new Ayet();
				ayet.id = jAyet.get("id").asLong();
				ayet.sureId = jAyet.get("surah_id").asLong();
				ayet.ayetNo = jAyet.get("verse_number").asLong();
				ayet.ayetAr = jAyet.get("verse").asText();
				ayet.sayfaNo = jAyet.get("page").asInt();
				ayet.cuzNo = jAyet.get("juz_number").asInt();
				ayet.okunusu = jAyet.get("transcription").asText();
				entityManager.merge(ayet);
				System.out.println("Sure: " + jSure.get("id") + " Ayet: " + ayet.ayetNo);
			}

			entityManager.flush();
			entityManager.clear();
		}
	}

	public List<Ayet> getAll() {
		List<Ayet> ayetList = (List<Ayet>) ayetRepo.findAll();
		Collections.sort(ayetList);
		return ayetList;
	}

	public List<Ayet> getBySureId(Long sureId) {
		List<Ayet> ayetList = (List<Ayet>) ayetRepo.findBySureId(sureId);
		Collections.sort(ayetList);
		return ayetList;
	}

}
