package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AKuranUtils;
import org.ay.demir.kuran.acik.model.AKuranRootWord;
import org.ay.demir.kuran.acik.model.AKuranSurah;
import org.ay.demir.kuran.acik.model.AKuranVerses;
import org.ay.demir.kuran.acik.repository.AKuranRootWordsRepository;
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

	@Autowired
	private AKuranRootWordsRepository rootWordsRepo;

	@Transactional
	public void downloadWords() throws Exception {

		List<AKuranSurah> surahs = surahService.getAll();

		for (AKuranSurah surah : surahs) {

			List<AKuranVerses> verseList = verseService.getBySurahId(surah.getId());

			for (AKuranVerses verse : verseList) {
				AKuranUtils.downloadWords(surah.getId(), verse.getId(), entityManager);
				System.out.println("Downloaded words for Surah " + surah.getId() + ", Verse " + verse.getVN());
			}
		}
	}

	@Transactional
	public void downloadRootDiffs() throws Exception {
		List<AKuranRootWord> rootWordsList = (List<AKuranRootWord>) rootWordsRepo.findAll();
		for (AKuranRootWord rootWord : rootWordsList) {
			AKuranUtils.downloadRootDiffs(rootWord.getLatin(), entityManager);
		}
	}

}
