package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AKuranUtils;
import org.ay.demir.kuran.acik.model.AKuranSurah;
import org.ay.demir.kuran.acik.repository.AKuranSurahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class AKuranSurahService {

	@Autowired
	AKuranSurahRepository surahRepo;

	@Autowired
	EntityManager entityManager;

	@Transactional
	public void downloadAcikKuranSure() throws Exception {
		AKuranUtils.downloadSurahs(entityManager);
	}

	public List<AKuranSurah> getAll() {
		return (List<AKuranSurah>) surahRepo.findAll();
	}

}
