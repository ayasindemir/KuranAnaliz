package org.ay.demir.kuran.acik.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Sure;
import org.ay.demir.kuran.acik.repository.SureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class SureService {

	@Autowired
	SureRepository sureRepo;

	private static final String URL = "https://api.acikkuran.com/surahs";

	public List<Sure> getAll() {
		List<Sure> sureList = (List<Sure>) sureRepo.findAll();
		Collections.sort(sureList);
		return sureList;
	}

	public void indir() throws IOException, InterruptedException {

		JsonNode root = AcikKuranUtils.downloadContent(URL);

		List<Sure> resultList = new ArrayList<Sure>();

		for (JsonNode jSure : root.get("data")) {
			Sure sure = new Sure();
			sure.id = jSure.get("id").asLong();
			sure.adTr = jSure.get("name").asText();
			sure.adEn = jSure.get("name_en").asText();
			sure.ayetSayisi = jSure.get("verse_count").asInt();
			sure.baslangicSayfasi = jSure.get("page_number").asInt();
			sure.adAr = jSure.get("name_original").asText();
			resultList.add(sure);
		}

		sureRepo.saveAll(resultList);
	}

}
