package org.ay.demir.kuran.meal;

import java.util.List;

import org.ay.demir.kuran.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealService {

	@Autowired
	MealRepository mealRepo;

	public void uploadMealFromFile() throws Exception {
		List<Meal> mealList1 = FileUtils.uploadMealFromTxtFile("meal_ali_bulac.txt", "Ali Bulaç");
		mealRepo.saveAll(mealList1);

		List<Meal> mealList2 = FileUtils.uploadMealFromTxtFile("meal_diyanet_vakfi.txt", "Diyanet Vakfı");
		mealRepo.saveAll(mealList2);

		List<Meal> mealList3 = FileUtils.uploadMealFromTxtFile("meal_edip_yuksel.txt", "Edip Yüksel");
		mealRepo.saveAll(mealList3);

		List<Meal> mealList4 = FileUtils.uploadMealFromTxtFile("meal_suleyman_ates.txt", "Süleyman Ateş");
		mealRepo.saveAll(mealList4);

		List<Meal> mealList5 = FileUtils.uploadMealFromTxtFile("meal_yasar_nuri_ozturk.txt", "Yaşar Nuri Öztürk");
		mealRepo.saveAll(mealList5);
	}

	public List<Meal> getAllBySureNoAndAyetNo(Integer p_iSureNo, Integer p_iAyetNo) {
		return (List<Meal>) mealRepo.findBySureNoAndAyetNo(p_iSureNo, p_iAyetNo);
	}

}
