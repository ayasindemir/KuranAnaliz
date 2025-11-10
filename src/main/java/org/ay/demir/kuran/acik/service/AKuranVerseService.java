package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AKuranUtils;
import org.ay.demir.kuran.acik.model.AKuranSurah;
import org.ay.demir.kuran.acik.model.AKuranVerses;
import org.ay.demir.kuran.acik.repository.AKuranVerseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class AKuranVerseService {

	@Autowired
	AKuranVerseRepository verseRepo;

	@Autowired
	AKuranSurahService surahService;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void downloadVerses() throws Exception {

		List<AKuranSurah> surahList = surahService.getAll();
		for (AKuranSurah surah : surahList) {
			AKuranUtils.downloadVersesOnly(surah.getId(), entityManager);
		}
	}

	public List<AKuranVerses> getAll() {
		return (List<AKuranVerses>) verseRepo.findAll();
	}

	public List<AKuranVerses> getBySurahId(Long id) {
		return verseRepo.findBySurahId(id);
	}

}
