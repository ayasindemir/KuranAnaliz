package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.RootDiff;
import org.ay.demir.kuran.acik.model.RootWord;
import org.ay.demir.kuran.acik.model.Surah;
import org.ay.demir.kuran.acik.model.Verse;
import org.ay.demir.kuran.acik.model.Word;
import org.ay.demir.kuran.acik.repository.RootDiffRepository;
import org.ay.demir.kuran.acik.repository.RootWordRepository;
import org.ay.demir.kuran.acik.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@Service
public class WordService {

	@Autowired
	private SurahService surahService;

	@Autowired
	private VerseService verseService;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RootWordRepository rootWordsRepo;

	@Autowired
	private WordRepository wordsRepo;

	@Autowired
	private RootDiffRepository rootDiffsRepo;

	@Transactional
	public void downloadWords() throws Exception {

		List<Surah> surahs = surahService.getAll();

		for (Surah surah : surahs) {

			List<Verse> verseList = verseService.getBySurahId(surah.getId());

			for (Verse verse : verseList) {
				AcikKuranUtils.downloadWords(surah.getId(), verse.getId(), entityManager);
			}

			System.out.println("Downloaded words for Surah " + surah.getId());
		}
	}

	@Transactional
	public void downloadRootDiffs() throws Exception {
		List<RootWord> rootWordsList = (List<RootWord>) rootWordsRepo.findAll();
		for (RootWord rootWord : rootWordsList) {
			AcikKuranUtils.downloadRootDiffs(rootWord.getLatin(), entityManager);
		}
	}

	public List<Word> getAllWords() {
		return (List<Word>) wordsRepo.findAll();
	}

	public List<RootWord> getAllRootWords() {
		return (List<RootWord>) rootWordsRepo.findAll();
	}

	public List<RootDiff> getAllRootDiffs() {
		return (List<RootDiff>) rootDiffsRepo.findAll();
	}

}
