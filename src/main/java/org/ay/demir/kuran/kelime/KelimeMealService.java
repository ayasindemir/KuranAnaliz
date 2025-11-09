package org.ay.demir.kuran.kelime;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	public List<SimpleKelimeMeal> getAllSimple() {

		List<KelimeMeal> tumListe = (List<KelimeMeal>) kelimeMealRepo.findAll();

		Map<String, List<KelimeMeal>> groupedBySure = tumListe.stream()
				.collect(Collectors.groupingBy(KelimeMeal::getSureNo));

		List<SimpleKelimeMeal> result = new ArrayList<>();

		for (Map.Entry<String, List<KelimeMeal>> sureEntry : groupedBySure.entrySet()) {
			int sureNo = Integer.parseInt(sureEntry.getKey());
			List<KelimeMeal> sureRows = sureEntry.getValue();

			// ayetNo’ya gore grupla
			Map<String, List<KelimeMeal>> groupedByAyet = sureRows.stream()
					.collect(Collectors.groupingBy(KelimeMeal::getAyetNo));

			List<SimpleAyet> ayetList = new ArrayList<>();

			for (Map.Entry<String, List<KelimeMeal>> ayetEntry : groupedByAyet.entrySet()) {
				int ayetNo = Integer.parseInt(ayetEntry.getKey());
				List<KelimeMeal> ayetRows = ayetEntry.getValue();

				// kelimeleri sirayla ekle
				List<SimpleKelime> kelimeler = ayetRows.stream()
						.sorted(Comparator.comparing(r -> Integer.parseInt(r.getKelimeNo())))
						.map(r -> new SimpleKelime(Integer.parseInt(r.getKelimeNo()), r.getArapcaHarekeli(),
								r.getMealTr(), r.getArapcaKok()))
						.collect(Collectors.toList());

				ayetList.add(new SimpleAyet(ayetNo, kelimeler));
			}

//			// Ayetleri ayet numarasina gore sirala
			ayetList.sort(Comparator.comparing(SimpleAyet::getAn));
			result.add(new SimpleKelimeMeal(sureNo, ayetList));
		}

//		// Sureleri numarasına göre sırala
		result.sort(Comparator.comparing(SimpleKelimeMeal::getSn));

		return result;

//		return tumListe.stream().map(e -> modelMapper.map(e, SimpleKelimeMeal.class)).collect(Collectors.toList());
	}

}
