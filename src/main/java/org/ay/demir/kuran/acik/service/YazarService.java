package org.ay.demir.kuran.acik.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Yazar;
import org.ay.demir.kuran.acik.repository.YazarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class YazarService {

	@Autowired
	private YazarRepository yazarRepo;

	private static final String URL = "https://api.acikkuran.com/authors";

	public List<Yazar> getAll() {
		List<Yazar> yazarList = (List<Yazar>) yazarRepo.findAll();
		Collections.sort(yazarList);
		return yazarList;
	}

	public void indir() throws Exception {

		JsonNode root = AcikKuranUtils.downloadContent(URL);

		List<Yazar> resultList = new ArrayList<Yazar>();

		for (JsonNode jYazar : root.get("data")) {
			Yazar yazar = new Yazar();
			yazar.id = jYazar.get("id").asLong();
			yazar.dil = jYazar.get("language").asText();
			yazar.ad = jYazar.get("name").asText();
			resultList.add(yazar);
		}

		yazarRepo.saveAll(resultList);
	}
}
