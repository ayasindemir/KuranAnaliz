package org.ay.demir.kuran.kelime;

import java.util.List;

import org.ay.demir.kuran.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KelimeMealService {

	@Autowired
	KelimeMealRepository kelimeMealRepo;

	public void uploadKelimeMealExcel() throws Exception {
		List<KelimeMeal> kelimeList = FileUtils.uploadKelimeMealExcel();

		kelimeMealRepo.saveAll(kelimeList);
	}

	public List<KelimeMeal> getBySureNo(String p_strSureNo) {
		return (List<KelimeMeal>) kelimeMealRepo.findBySureNo(p_strSureNo);
	}

	public List<KelimeMeal> getBySureAndAyetNo(String p_strSureNo, String p_strAyetNo) {
		return (List<KelimeMeal>) kelimeMealRepo.findBySureNoAndAyetNo(p_strSureNo, p_strAyetNo);
	}

	public List<KelimeMeal> getByKokler(String p_strKokler) {
		return (List<KelimeMeal>) kelimeMealRepo.findByHarflerArapca(p_strKokler);
	}

	public List<KelimeMeal> getByLatinKok(String p_strKok) {
		return (List<KelimeMeal>) kelimeMealRepo.findByLatinKok(p_strKok);
	}

}
