package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AKuranUtils;
import org.ay.demir.kuran.acik.model.AKuranAuthor;
import org.ay.demir.kuran.acik.model.AKuranSurah;
import org.ay.demir.kuran.acik.model.AKuranTranslation;
import org.ay.demir.kuran.acik.repository.AKuranTranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class AKuranTranslationService {

	@Autowired
	private AKuranSurahService surahService;

	@Autowired
	private AKuranAuthorService authorService;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private AKuranTranslationRepository translationRepo;

	@Transactional
	public void downloadTranslations() throws Exception {

		List<AKuranSurah> surahList = surahService.getAll();

		for (AKuranSurah surah : surahList) {

			List<AKuranAuthor> authorList = authorService.getAll();

			for (AKuranAuthor author : authorList) {
				AKuranUtils.downloadAllVersesTranslations(surah.getId(), author.getId(), entityManager);
			}
		}
	}

	public List<AKuranTranslation> getByAuthorId(Long auhtorId) {
		return (List<AKuranTranslation>) translationRepo.findByAuthorId(auhtorId);
	}
}
