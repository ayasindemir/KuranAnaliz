package org.ay.demir.kuran.acik.service;

import java.util.Collections;
import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.KokKelime;
import org.ay.demir.kuran.acik.model.KokKelimeGrup;
import org.ay.demir.kuran.acik.repository.KokKelimeGrupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.EntityManager;

@Service
public class KokKelimeGrupService {

	@Autowired
	private KelimeService kelimeService;

	@Autowired
	private KokKelimeGrupRepository kokKelimeGrupRepo;

	@Autowired
	private EntityManager entityManager;

	private static final String URL = "https://api.acikkuran.com/root/latin/";

	@Transactional
	public void indir() throws Exception {
		List<KokKelime> rootWordsList = (List<KokKelime>) kelimeService.getAllKokKelime();

		for (KokKelime kokKelime : rootWordsList) {

			JsonNode root = AcikKuranUtils.downloadContent(URL + kokKelime.kokLatin);

			JsonNode jKokKelime = root.get("data");

			for (JsonNode diff : jKokKelime.get("diffs")) {
				KokKelimeGrup kokKelimeGrp = new KokKelimeGrup();
				kokKelimeGrp.id = diff.get("id").asLong();
				kokKelimeGrp.kelime = diff.get("diff").asText();
				kokKelimeGrp.toplam = diff.get("count").asInt();
				kokKelimeGrp.kokKelimeId = jKokKelime.get("id").asLong();
				kokKelimeGrp.kokKelime = jKokKelime.get("arabic").asText();
				entityManager.merge(kokKelimeGrp);
			}

			entityManager.flush();
			entityManager.clear();
			System.out.println("Kok Kelime: " + jKokKelime.get("arabic"));
		}
	}

	public List<KokKelimeGrup> getAll() {
		List<KokKelimeGrup> grupList = (List<KokKelimeGrup>) kokKelimeGrupRepo.findAll();
		Collections.sort(grupList);
		return grupList;
	}

}
