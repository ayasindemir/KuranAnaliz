package org.ay.demir.kuran.mushaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "mushaf")
public class MushafController {

	@Autowired
	private MushafService mushafService;

	@PostMapping(path = "uploadMushaf")
	public void uploadFromFile() throws Exception {
		mushafService.uploadMushafFromFile();
	}

	@GetMapping(path = "getAllMushaf", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Mushaf>> getAllMushaf() {
		return ResponseEntity.ok(mushafService.getAllSurelerFromDB());
	}

	@GetMapping(path = "getBySureNo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Mushaf>> getBySureNo(@RequestParam("sureNo") int sureNo) {
		return ResponseEntity.ok(mushafService.getBySureNo(sureNo));
	}

	@GetMapping(path = "getBySureNoAndAyetNo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Mushaf>> getBySureNoAndAyetNo(@RequestParam("sureNo") int sureNo,
			@RequestParam("ayetNo") int ayetNo) {
		return ResponseEntity.ok(mushafService.getBySureAndAyetNo(sureNo, ayetNo));
	}

}
