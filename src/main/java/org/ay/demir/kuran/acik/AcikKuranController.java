package org.ay.demir.kuran.acik;

import java.util.List;

import org.ay.demir.kuran.acik.model.Ayet;
import org.ay.demir.kuran.acik.model.Harf;
import org.ay.demir.kuran.acik.model.Kelime;
import org.ay.demir.kuran.acik.model.KokKelime;
import org.ay.demir.kuran.acik.model.KokKelimeGrup;
import org.ay.demir.kuran.acik.model.Meal;
import org.ay.demir.kuran.acik.model.MealDipNot;
import org.ay.demir.kuran.acik.model.Sure;
import org.ay.demir.kuran.acik.model.Yazar;
import org.ay.demir.kuran.acik.service.AyetService;
import org.ay.demir.kuran.acik.service.HarfService;
import org.ay.demir.kuran.acik.service.KelimeService;
import org.ay.demir.kuran.acik.service.KokKelimeGrupService;
import org.ay.demir.kuran.acik.service.KokKelimeGrupServiceUpdate;
import org.ay.demir.kuran.acik.service.MealService;
import org.ay.demir.kuran.acik.service.SureService;
import org.ay.demir.kuran.acik.service.YazarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "aKuran")
public class AcikKuranController {

	@Autowired
	private YazarService yazarService;

	@Autowired
	private SureService sureService;

	@Autowired
	private HarfService harfService;

	@Autowired
	private AyetService ayetService;

	@Autowired
	private MealService mealService;

	@Autowired
	private KelimeService kelimeService;

	@Autowired
	private KokKelimeGrupService kokKelimeGrupService;

	@Autowired
	private KokKelimeGrupServiceUpdate ss;

	@PostMapping(path = "/yazar/download")
	public void downloadAuthors() throws Exception {
		yazarService.indir();
	}

	@GetMapping(path = "/yazar/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Yazar>> getAllAuthors() {
		return ResponseEntity.ok(yazarService.getAll());
	}

	@PostMapping(path = "/sure/download")
	public void downloadSurahs() throws Exception {
		sureService.indir();
	}

	@GetMapping(path = "/sure/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sure>> getAllSurahs() {
		return ResponseEntity.ok(sureService.getAll());
	}

	@PostMapping(path = "/harf/download")
	public void downloadRootChars() throws Exception {
		harfService.indir();
	}

	@GetMapping(path = "/harf/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Harf>> getAllRootChars() {
		return ResponseEntity.ok(harfService.getAll());
	}

	@PostMapping(path = "/ayet/download")
	public void downloadVerses() throws Exception {
		ayetService.indir();
	}

	@GetMapping(path = "/ayet/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ayet>> getAllVerses() {
		return ResponseEntity.ok(ayetService.getAll());
	}

	@PostMapping(path = "/meal/download")
	public void downloadTranslations() throws Exception {
		mealService.indir();
	}

	@GetMapping(path = "/meal/getByYazar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Meal>> getTranslationsByAuthor(Long yazarId) throws Exception {
		return ResponseEntity.ok(mealService.getByYazarId(yazarId));
	}

	@GetMapping(path = "/meal/getAllMealDipNot", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MealDipNot>> getAllTranslationFootNotes() throws Exception {
		return ResponseEntity.ok(mealService.getAllDipNot());
	}

	@PostMapping(path = "/kelime/download")
	public void downloadWords() throws Exception {
		kelimeService.indir();
	}

	@GetMapping(path = "/kelime/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Kelime>> getAllWords() throws Exception {
		return ResponseEntity.ok(kelimeService.getAllKelime());
	}

	@GetMapping(path = "/kelime/getAllRootWords", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KokKelime>> getAllRootWords() throws Exception {
		return ResponseEntity.ok(kelimeService.getAllKokKelime());
	}

	@PostMapping(path = "/kelime/downloadRootDiffs")
	public void downloadRootDiffs() throws Exception {
		kokKelimeGrupService.indir();
	}

	@GetMapping(path = "/kelime/getAllRootDiffs", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KokKelimeGrup>> getAllRootDiffs() throws Exception {
		return ResponseEntity.ok(kokKelimeGrupService.getAll());
	}

	@PostMapping(path = "/test/test12345")
	public void test12345() throws Exception {
		ss.indir();
	}

}
