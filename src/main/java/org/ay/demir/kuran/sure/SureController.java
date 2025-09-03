package org.ay.demir.kuran.sure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "sure")
public class SureController {

	@Autowired
	private SureService sureService;

	@PostMapping(path = "uploadSureler")
	public void uploadFromFile() throws Exception {
		sureService.uploadSurelerFromFile();
	}

	@GetMapping(path = "getAllSure", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sure>> getAllSure() {
		return ResponseEntity.ok(sureService.getAllSurelerFromDB());
	}

}
