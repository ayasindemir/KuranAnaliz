package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AKuranUtils;
import org.ay.demir.kuran.acik.model.AKuranSurah;
import org.ay.demir.kuran.acik.model.AKuranVerses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class AKuranWordsService {

	@Autowired
	private AKuranSurahService surahService;

	@Autowired
	private AKuranVerseService verseService;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void downloadWords() throws Exception {

		List<AKuranSurah> surahs = surahService.getAll();

		for (AKuranSurah surah : surahs) {

			List<AKuranVerses> verseList = verseService.getBySurahId(surah.getId());

			for (AKuranVerses verse : verseList) {
				AKuranUtils.downloadWords(surah.getId(), verse.getId(), entityManager);
				System.out.println("Downloaded words for Surah " + surah.getId() + ", Verse " + verse.getVerseNumber());
			}
		}
	}

}
