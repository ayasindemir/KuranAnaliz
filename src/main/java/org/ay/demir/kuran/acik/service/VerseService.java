package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Surah;
import org.ay.demir.kuran.acik.model.Verse;
import org.ay.demir.kuran.acik.repository.VerseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class VerseService {

	@Autowired
	VerseRepository verseRepo;

	@Autowired
	SurahService surahService;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void downloadVerses() throws Exception {

		List<Surah> surahList = surahService.getAll();
		for (Surah surah : surahList) {
			AcikKuranUtils.downloadVersesOnly(surah.getId(), entityManager);
		}
	}

	public List<Verse> getAll() {
		return (List<Verse>) verseRepo.findAll();
	}

	public List<Verse> getBySurahId(Long id) {
		return verseRepo.findBySN(id);
	}

}
