package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AKuranUtils;
import org.ay.demir.kuran.acik.model.AKuranAuthor;
import org.ay.demir.kuran.acik.model.AKuranSurah;
import org.ay.demir.kuran.acik.model.AKuranTranlationFootNotes;
import org.ay.demir.kuran.acik.repository.AKuranTranslationFootNotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class AKuranTranslationFootNotesService {

	@Autowired
	private AKuranSurahService surahService;

	@Autowired
	private AKuranAuthorService authorService;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private AKuranTranslationFootNotesRepository footNotesRepository;

	@Transactional
	public void downloadTranslationFootNotes() throws Exception {

		List<AKuranSurah> surahList = surahService.getAll();

		for (AKuranSurah surah : surahList) {

			List<AKuranAuthor> authorList = authorService.getAll();

			for (AKuranAuthor author : authorList) {
				AKuranUtils.downloadTranslationFootNotes(surah.getId(), author.getId(), entityManager);
			}
		}
	}

	public List<AKuranTranlationFootNotes> getAllFootNotes() {
		return (List<AKuranTranlationFootNotes>) footNotesRepository.findAll();
	}

}
