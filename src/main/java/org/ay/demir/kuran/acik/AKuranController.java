package org.ay.demir.kuran.acik;

import java.util.List;

import org.ay.demir.kuran.acik.model.AKuranAuthor;
import org.ay.demir.kuran.acik.model.AKuranRootChars;
import org.ay.demir.kuran.acik.model.AKuranRoots;
import org.ay.demir.kuran.acik.model.AKuranSurah;
import org.ay.demir.kuran.acik.model.AKuranTranslation;
import org.ay.demir.kuran.acik.model.AKuranVerses;
import org.ay.demir.kuran.acik.service.AKuranAuthorService;
import org.ay.demir.kuran.acik.service.AKuranRootCharsService;
import org.ay.demir.kuran.acik.service.AKuranRootsService;
import org.ay.demir.kuran.acik.service.AKuranSurahService;
import org.ay.demir.kuran.acik.service.AKuranTranslationService;
import org.ay.demir.kuran.acik.service.AKuranVerseService;
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
public class AKuranController {

	@Autowired
	private AKuranRootCharsService rootCharsService;

	@Autowired
	private AKuranAuthorService authorsService;

	@Autowired
	private AKuranRootsService rootsService;

	@Autowired
	private AKuranSurahService surahService;

	@Autowired
	private AKuranVerseService verseService;

	@Autowired
	private AKuranTranslationService translationService;

	@PostMapping(path = "/authors/downloadAuthors")
	public void downloadAuthors() throws Exception {
		authorsService.downloadAuthors();
	}

	@GetMapping(path = "/authors/getAllAuthors", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AKuranAuthor>> getAllAuthors() {
		return ResponseEntity.ok(authorsService.getAll());
	}

	@PostMapping(path = "/rootchars/downloadRootChars")
	public void downloadRootChars() throws Exception {
		rootCharsService.downloadRootChars();
	}

	@GetMapping(path = "/rootchars/getAllRootChars", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AKuranRootChars>> getAllRootChars() {
		return ResponseEntity.ok(rootCharsService.getAll());
	}

	@PostMapping(path = "/roots/downloadRoots")
	public void downloadRoots() throws Exception {
		rootsService.downloadRoots();
	}

	@GetMapping(path = "/roots/getAllRoots", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AKuranRoots>> getAllRoots() {
		return ResponseEntity.ok(rootsService.getAll());
	}

	@PostMapping(path = "/surahs/downloadSurahs")
	public void downloadSureFromInternet() throws Exception {
		surahService.downloadAcikKuranSure();
	}

	@GetMapping(path = "/surahs/getAllSurahs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AKuranSurah>> getAllSurahs() {
		return ResponseEntity.ok(surahService.getAll());
	}

	@PostMapping(path = "/verses/downloadVerses")
	public void downloadVersesFromInternet() throws Exception {
		verseService.downloadVerses();
	}

	@GetMapping(path = "/verses/getAllVerses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AKuranVerses>> getAllVerses() {
		return ResponseEntity.ok(verseService.getAll());
	}

	@PostMapping(path = "/verses/downloadTranslations")
	public void downloadTranslations() throws Exception {
		translationService.downloadTranslations();
	}

	@GetMapping(path = "/verses/getTranslationsByAuthor", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AKuranTranslation>> getTranslationsByAuthor(@RequestParam("authorId") Long authorId)
			throws Exception {
		return ResponseEntity.ok(translationService.getByAuthorId(authorId));
	}

}
