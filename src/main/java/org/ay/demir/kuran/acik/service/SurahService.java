package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Surah;
import org.ay.demir.kuran.acik.repository.SurahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class SurahService {

	@Autowired
	SurahRepository surahRepo;

	@Autowired
	EntityManager entityManager;

	@Transactional
	public void downloadSurahs() throws Exception {
		AcikKuranUtils.downloadSurahs(entityManager);
	}

	public List<Surah> getAll() {
		return (List<Surah>) surahRepo.findAll();
	}

}
