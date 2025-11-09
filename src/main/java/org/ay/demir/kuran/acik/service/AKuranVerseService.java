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
	AKuranAuthorService authorService;

	@Autowired
	AKuranTransFootNotesService footNoteService;

	@Autowired
	AKuranTranslationService translationService;

	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void downloadVerses() throws Exception {

		// First download and save surah verse
		List<AKuranSurah> surahList = surahService.getAll();
		for (AKuranSurah surah : surahList) {
			AKuranUtils.downloadVersesOnly(surah.getId(), entityManager);
		}

//		for (AKuranSurah surah : surahList) {
//			List<AKuranAuthor> authorList = authorService.getAll();
//			for (AKuranAuthor author : authorList) {
//				AKuranUtils.downloadVersesTranslationsOnly(surah.getId(), author.getId(), translationService,
//						entityManager);
//			}
//		}

//		for (AKuranSurah surah : surahList) {
//			List<AKuranAuthor> authorList = authorService.getAll();
//			for (AKuranAuthor author : authorList) {
//				AKuranUtils.downloadTransFootNotesOnly(surah.getId(), author.getId(), entityManager);
//			}
//		}

//		for (AKuranSurah surah : surahList) {
//			List<AKuranAuthor> authorList = authorService.getAll();
//			for (AKuranAuthor author : authorList) {
//				AKuranUtils.downloadVersesTranslations(surah.getId(), author.getId(), footNoteService,
//						translationService);
//			}
//		}
	}

	public List<AKuranVerses> getAll() {
		return (List<AKuranVerses>) verseRepo.findAll();
	}

}
