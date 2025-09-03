package org.ay.demir.kuran.meal;

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
@RequestMapping(path = "meal")
public class MealController {

	@Autowired
	private MealService mealService;

	@PostMapping(path = "uploadMeal")
	public void uploadFromFile() throws Exception {
		mealService.uploadMealFromFile();
	}

	@GetMapping(path = "getBySureNoAndAyetNo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Meal>> getAllBySureNoAndAyetNo(@RequestParam("sureNo") Integer p_iSureNo,
			@RequestParam("ayetNo") Integer p_iAyetNo) {
		return ResponseEntity.ok(mealService.getAllBySureNoAndAyetNo(p_iSureNo, p_iAyetNo));
	}

}
