package org.ay.demir.kuran.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.ay.demir.kuran.kelime.KelimeMeal;
import org.ay.demir.kuran.meal.Meal;

import com.monitorjbl.xlsx.StreamingReader;

public class FileUtils {

	public static TreeMap<Integer, String> uploadSurelerFromTxtFile() throws IOException {

		InputStream is = FileUtils.class.getClassLoader().getResourceAsStream("sureler.txt");
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(streamReader);

		TreeMap<Integer, String> sureMap = new TreeMap<Integer, String>();

		try {

			for (String line; (line = br.readLine()) != null;) {
				Integer sureNo = Integer.parseInt(line.substring(0, line.indexOf("-")));
				String sureAdi = line.substring(line.indexOf("-") + 1, line.length());
				sureMap.put(sureNo, sureAdi);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sureMap = new TreeMap<Integer, String>();
		} finally {
			br.close();
		}

		return sureMap;
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

}
