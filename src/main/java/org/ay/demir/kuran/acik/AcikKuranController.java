package org.ay.demir.kuran.acik;

import java.util.List;

import org.ay.demir.kuran.acik.model.Audio;
import org.ay.demir.kuran.acik.model.Author;
import org.ay.demir.kuran.acik.model.RootChar;
import org.ay.demir.kuran.acik.model.RootDiff;
import org.ay.demir.kuran.acik.model.RootWord;
import org.ay.demir.kuran.acik.model.Surah;
import org.ay.demir.kuran.acik.model.TranslationFootNote;
import org.ay.demir.kuran.acik.model.Translation;
import org.ay.demir.kuran.acik.model.Verse;
import org.ay.demir.kuran.acik.model.Word;
import org.ay.demir.kuran.acik.service.AudioService;
import org.ay.demir.kuran.acik.service.AuthorService;
import org.ay.demir.kuran.acik.service.RootCharService;
import org.ay.demir.kuran.acik.service.SurahService;
import org.ay.demir.kuran.acik.service.TranslationFootNotesService;
import org.ay.demir.kuran.acik.service.TranslationService;
import org.ay.demir.kuran.acik.service.VerseService;
import org.ay.demir.kuran.acik.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "aKuran")
public class AcikKuranController {

	@Autowired
	private AuthorService authorsService;

	@Autowired
	private SurahService surahService;

	@Autowired
	private RootCharService rootCharsService;

	@Autowired
	private VerseService verseService;

	@Autowired
	private TranslationService translationService;

	@Autowired
	private TranslationFootNotesService footNotesService;

	@Autowired
	private WordService wordsService;

	@Autowired
	private AudioService audioService;

	@PostMapping(path = "/authors/download")
	public void downloadAuthors() throws Exception {
		authorsService.downloadAuthors();
	}

	@GetMapping(path = "/authors/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Author>> getAllAuthors() {
		return ResponseEntity.ok(authorsService.getAll());
	}

	@PostMapping(path = "/surahs/download")
	public void downloadSurahs() throws Exception {
		surahService.downloadSurahs();
	}

	@GetMapping(path = "/surahs/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Surah>> getAllSurahs() {
		return ResponseEntity.ok(surahService.getAll());
	}

	@PostMapping(path = "/rootchars/download")
	public void downloadRootChars() throws Exception {
		rootCharsService.downloadRootChars();
	}

	@GetMapping(path = "/rootchars/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RootChar>> getAllRootChars() {
		return ResponseEntity.ok(rootCharsService.getAll());
	}

	@PostMapping(path = "/verses/download")
	public void downloadVerses() throws Exception {
		verseService.downloadVerses();
	}

	@GetMapping(path = "/verses/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Verse>> getAllVerses() {
		return ResponseEntity.ok(verseService.getAll());
	}

	@PostMapping(path = "/translations/download")
	public void downloadTranslations() throws Exception {
		translationService.downloadTranslations();
	}

	@GetMapping(path = "/translations/getByAuthor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Translation>> getTranslationsByAuthor(@RequestParam("authorId") Long authorId)
			throws Exception {
		return ResponseEntity.ok(translationService.getByAuthorId(authorId));
	}

	@PostMapping(path = "/words/download")
	public void downloadWords() throws Exception {
		wordsService.downloadWords();
	}

	@GetMapping(path = "/words/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Word>> getAllWords() throws Exception {
		return ResponseEntity.ok(wordsService.getAllWords());
	}

	@PostMapping(path = "/verses/downloadTranslationFootNotes")
	public void downloadTranslationFootNotes() throws Exception {
		footNotesService.downloadTranslationFootNotes();
	}

	@GetMapping(path = "/verses/getAllTranslationFootNotes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TranslationFootNote>> getAllTranslationFootNotes() throws Exception {
		return ResponseEntity.ok(footNotesService.getAllFootNotes());
	}

	@GetMapping(path = "/words/getAllRootDiffs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RootDiff>> getAllRootDiffs() throws Exception {
		return ResponseEntity.ok(wordsService.getAllRootDiffs());
	}

	@GetMapping(path = "/words/getAllRootWords", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RootWord>> getAllRootWords() throws Exception {
		return ResponseEntity.ok(wordsService.getAllRootWords());
	}

	@PostMapping(path = "/words/downloadRootDiffs")
	public void downloadRootDiffs() throws Exception {
		wordsService.downloadRootDiffs();
	}

	@GetMapping(path = "/audio/getAllAudio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Audio>> getAllAudio() throws Exception {
		return ResponseEntity.ok(audioService.getAll());
	}

}
