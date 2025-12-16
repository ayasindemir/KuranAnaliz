package org.ay.demir.kuran.acik.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Harf;
import org.ay.demir.kuran.acik.repository.HarfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class HarfService {

	@Autowired
	private HarfRepository harfRepo;

	private static final String URL = "https://api.acikkuran.com/rootchars";

	public List<Harf> getAll() {
		List<Harf> harfList = (List<Harf>) harfRepo.findAll();
		Collections.sort(harfList);
		return harfList;
	}

	public void indir() throws IOException, InterruptedException {

		JsonNode root = AcikKuranUtils.downloadContent(URL);

		List<Harf> resultList = new ArrayList<Harf>();

		for (JsonNode jHarf : root.get("data")) {
			Harf harf = new Harf();
			harf.id = jHarf.get("id").asLong();
			harf.harfAr = jHarf.get("arabic").asText();
			resultList.add(harf);
		}

		harfRepo.saveAll(resultList);
	}

}
