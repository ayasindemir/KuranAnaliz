package org.ay.demir.kuran.kelime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/kuran")
public class KelimeMealController {

	@Autowired
	private KelimeMealService kuranService;

	@GetMapping(path = "getBySureNo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KelimeMeal>> getBySureNo(@RequestParam("sureNo") String p_strSureNo) {
		return ResponseEntity.ok(kuranService.getBySureNo(p_strSureNo));
	}

	@GetMapping(path = "getBySureNoAndAyetNo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KelimeMeal>> getBySureNoAndAyetNo(@RequestParam("sureNo") String p_strSureNo,
			@RequestParam("ayetNo") String p_strAyetNo) {
		return ResponseEntity.ok(kuranService.getBySureAndAyetNo(p_strSureNo, p_strAyetNo));
	}

	@GetMapping(path = "getByLatinKok", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KelimeMeal>> getByLatinKok(@RequestParam("kok") String p_strKok) {
		return ResponseEntity.ok(kuranService.getByLatinKok(p_strKok));
	}

	@GetMapping(path = "getByKokHarfler", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KelimeMeal>> getByKokHarfler(@RequestParam("kokler") String p_strKokler) {
		return ResponseEntity.ok(kuranService.getByKokler(p_strKokler));
	}

}
