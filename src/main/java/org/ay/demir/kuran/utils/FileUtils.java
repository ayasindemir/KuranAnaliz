package org.ay.demir.kuran.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.ay.demir.kuran.kelime.KelimeMeal;
import org.ay.demir.kuran.meal.Meal;
import org.ay.demir.kuran.mushaf.Mushaf;
import org.ay.demir.kuran.page.Page;
import org.ay.demir.kuran.page.SuraVerses;
import org.ay.demir.kuran.page.Verse;
import org.ay.demir.kuran.sure.Sure;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitorjbl.xlsx.StreamingReader;

public class FileUtils {

	public static List<Sure> uploadSurelerFromTxtFile() throws IOException {

		InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("sureler2.txt");
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(streamReader);

		List<Sure> sureList = new ArrayList<Sure>();

		try {
			for (String line; (line = br.readLine()) != null;) {
				if (line != null && line.startsWith("\uFEFF")) {
					line = line.substring(1);
				}
				String[] parts = line.split(";");
				Sure sure = new Sure();
				sure.setSureNo(Integer.parseInt(parts[0].trim()));
				sure.setAyetSayisi(Integer.parseInt(parts[1]));
				sure.setSureAdi(parts[2]);
				sure.setSureAdiArapca(parts[3]);
				sureList.add(sure);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sureList = new ArrayList<Sure>();
		} finally {
			br.close();
		}

		return sureList;
	}

	public static List<Mushaf> uploadMushafFromTxtFile() throws IOException {

		InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("quran-simple-plain (1).txt");
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(streamReader);

		List<Mushaf> mushafList = new ArrayList<Mushaf>();

		try {

			for (String line; (line = br.readLine()) != null;) {

				int firsIndex = line.indexOf("|");
				int lastIndex = line.lastIndexOf("|");

				Integer sureNo = Integer.parseInt(line.substring(0, firsIndex));
				Integer ayetNo = Integer.parseInt(line.substring(firsIndex + 1, lastIndex));
				String ayet = line.substring(lastIndex + 1);
				Mushaf m = new Mushaf(sureNo, ayetNo, ayet);
				mushafList.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mushafList = new ArrayList<Mushaf>();
		} finally {
			br.close();
		}

		return mushafList;
	}

	public static List<Meal> uploadMealFromTxtFile(String p_strFileName, String p_strYazar) throws IOException {

		InputStream is = FileUtils.class.getClassLoader().getResourceAsStream(p_strFileName);
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(streamReader);

		List<Meal> mealList = new ArrayList<Meal>();

		try {
			for (String line; (line = br.readLine()) != null;) {
				int firsIndexOfSep = line.indexOf("|");
				int lastIndexOfSep = line.lastIndexOf("|");
				Integer sureNo = Integer.parseInt(line.substring(0, firsIndexOfSep));
				Integer ayetNo = Integer.parseInt(line.substring(firsIndexOfSep + 1, lastIndexOfSep));
				String mealTr = line.substring(lastIndexOfSep + 1);

				Meal meal = new Meal();
				meal.setSureNo(sureNo);
				meal.setAyetNo(ayetNo);
				meal.setMeal(mealTr);
				meal.setYazar(p_strYazar);
				mealList.add(meal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			mealList = new ArrayList<Meal>();
		} finally {
			br.close();
		}

		return mealList;
	}

	public static List<KelimeMeal> uploadKelimeMealExcel() throws Exception {
		InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("elktb_net_veriler.xlsx");

		if (is == null) {
			throw new IllegalArgumentException("File not found...");
		}

		Workbook workbook = StreamingReader.builder().rowCacheSize(100) // // bellekte kac satir?
				.bufferSize(4096) // okuma tamponu
				.open(is);

		Sheet sheet = workbook.getSheetAt(0);

		List<KelimeMeal> detayList = new ArrayList<KelimeMeal>();

		for (Row row : sheet) {

			// Continue if header row
			if (row.getRowNum() == 0) {
				continue;
			}

			Iterator<Cell> cellIterator = row.cellIterator();

			KelimeMeal detay = new KelimeMeal();
			detay.setCuzNo(cellIterator.next().getStringCellValue()); // 1. column
			if (detay.getCuzNo() == null || detay.getCuzNo().isEmpty()) {
				break;
			}
			detay.setHizipNo(cellIterator.next().getStringCellValue()); // 2. column
			detay.setSureNo(cellIterator.next().getStringCellValue()); // 3. column
			detay.setSureAdi(cellIterator.next().getStringCellValue()); // 4. column
			detay.setAyetNo(cellIterator.next().getStringCellValue()); // 5. column
			detay.setKelimeNo(cellIterator.next().getStringCellValue()); // 6. column
			detay.setLatin(cellIterator.next().getStringCellValue()); // 7. column
			detay.setArapcaHarekeli(cellIterator.next().getStringCellValue());// 8. column
			detay.setMealTr(cellIterator.next().getStringCellValue()); // 9. column
			detay.setArapcaHarekesiz(cellIterator.next().getStringCellValue()); // 10. column
			detay.setLatinKok(cellIterator.next().getStringCellValue()); // 11. column
			detay.setArapcaKok(cellIterator.next().getStringCellValue()); // 12. column
			detay.setKokHarfSayisi(cellIterator.next().getStringCellValue()); // 13. column
			detay.setKok4Harf(cellIterator.next().getStringCellValue()); // 14. column
			detay.setKok3Harf(cellIterator.next().getStringCellValue()); // 15. column
			detay.setKok2Harf(cellIterator.next().getStringCellValue()); // 16. column
			detay.setKok1Harf(cellIterator.next().getStringCellValue()); // 17. column
			detay.setHarfSiraNo4(cellIterator.next().getStringCellValue()); // 18. column
			detay.setHarfSiraNo3(cellIterator.next().getStringCellValue()); // 19. column
			detay.setHarfSiraNo2(cellIterator.next().getStringCellValue()); // 20. column
			detay.setHarfSiraNo1(cellIterator.next().getStringCellValue()); // 21. column
			detay.setHarflerLatince(cellIterator.next().getStringCellValue()); // 22. column
			detay.setHarflerArapca(cellIterator.next().getStringCellValue()); // 23. column
			detay.setIrabTurkce(cellIterator.next().getStringCellValue()); // 24. column
			detay.setMealEng(cellIterator.next().getStringCellValue()); // 25. column
			detay.setFiilTuru(cellIterator.next().getStringCellValue()); // 26. column
			detay.setPasif(cellIterator.next().getStringCellValue()); // 27. column
			detay.setZamirTuru(cellIterator.next().getStringCellValue()); // 28. column
			detay.setBabi(cellIterator.next().getStringCellValue()); // 29. column
			detay.setHemzesizHarekesizArapca(cellIterator.next().getStringCellValue()); // 30. column
			detayList.add(detay);

			System.out.println(detay.toString());
		}

		workbook.close();

		return detayList;
	}

	public static List<Page> uploadQuranPages() {
		try {
			ObjectMapper mapper = new ObjectMapper();

			// 1. JSON dosyasını oku (örnek: pages.json)
			List<Map<String, Object>> rawList = mapper.readValue(
					FileUtils.class.getClassLoader().getResourceAsStream("quran_by_pages.json"),
					new TypeReference<List<Map<String, Object>>>() {
					});

			// 2. Listeyi Entity nesnelerine dönüştür
			List<Page> pageEntities = new ArrayList<Page>();

			for (Map<String, Object> pageMap : rawList) {
				Page page = new Page();
				page.setPageIndex((Integer) pageMap.get("page_index"));

				Map<String, List<Map<String, Object>>> versesBySura = (Map<String, List<Map<String, Object>>>) pageMap
						.get("verses_by_sura");

				List<SuraVerses> suraEntities = new ArrayList<SuraVerses>();

				if (versesBySura != null) {
					for (Map.Entry<String, List<Map<String, Object>>> entry : versesBySura.entrySet()) {
						String suraName = entry.getKey();
						List<Map<String, Object>> verseList = entry.getValue();

						SuraVerses suraVerses = new SuraVerses();
						suraVerses.setSuraName(suraName);
						suraVerses.setPage(page);

						List<Verse> verseEntities = new ArrayList<Verse>();

						for (Map<String, Object> verseMap : verseList) {
							Verse verse = new Verse();

							// JSON’da bazen index int değil double gelebilir — güvenli dönüştürme
							Object idx = verseMap.get("index");
							if (idx instanceof Integer)
								verse.setIndex((Integer) idx);
							else if (idx instanceof Number)
								verse.setIndex(((Number) idx).intValue());

							verse.setText((String) verseMap.get("text"));
							verse.setSuraVerses(suraVerses);

							verseEntities.add(verse);
						}

						suraVerses.setVerses(verseEntities);
						suraEntities.add(suraVerses);
					}
				}

				page.setSuraVerses(suraEntities);
				pageEntities.add(page);
			}

			// 3. Test: ilk sayfayı yazdır
			for (Page page : pageEntities) {
				System.out.println("Sayfa " + page.getPageIndex());
				for (SuraVerses sura : page.getSuraVerses()) {
					System.out.println("  Sure: " + sura.getSuraName());
					for (Verse v : sura.getVerses()) {
						System.out.println("    [" + v.getIndex() + "] " + v.getText());
					}
				}
			}

			return pageEntities;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<Page>();
	}

}
