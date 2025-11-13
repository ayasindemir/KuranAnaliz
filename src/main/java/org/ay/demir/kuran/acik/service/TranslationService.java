package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Author;
import org.ay.demir.kuran.acik.model.Surah;
import org.ay.demir.kuran.acik.model.Translation;
import org.ay.demir.kuran.acik.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class TranslationService {

	@Autowired
	private SurahService surahService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private TranslationRepository translationRepo;

	@Transactional
	public void downloadTranslations() throws Exception {

		List<Surah> surahList = surahService.getAll();

		for (Surah surah : surahList) {

			List<Author> authorList = authorService.getAll();

			for (Author author : authorList) {
				AcikKuranUtils.downloadAllVersesTranslations(surah.getId(), author.getId(), entityManager);
			}
		}
	}

	public List<Translation> getByAuthorId(Long auhtorId) {
		return (List<Translation>) translationRepo.findByAuthorId(auhtorId);
	}
}
