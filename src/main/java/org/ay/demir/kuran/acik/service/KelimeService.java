package org.ay.demir.kuran.acik.service;

import java.util.Collections;
import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Ayet;
import org.ay.demir.kuran.acik.model.Kelime;
import org.ay.demir.kuran.acik.model.KokKelime;
import org.ay.demir.kuran.acik.model.Sure;
import org.ay.demir.kuran.acik.repository.KelimeRepository;
import org.ay.demir.kuran.acik.repository.KokKelimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.EntityManager;

@Service
public class KelimeService {

	@Autowired
	private SureService sureService;

	@Autowired
	private AyetService ayetService;

	@Autowired
	private KelimeRepository kelimeRepo;

	@Autowired
	private KokKelimeRepository kokKelimeRepo;

	@Autowired
	private EntityManager entityManager;

	private static final String URL = "https://api.acikkuran.com/surah/";

	@Transactional
	public void indir() throws Exception {

		List<Sure> sureList = sureService.getAll();

		for (Sure sure : sureList) {

			List<Ayet> ayetList = ayetService.getBySureId(sure.id);

			for (Ayet ayet : ayetList) {

				JsonNode root = AcikKuranUtils.downloadContent(URL + sure.id + "/verse/" + ayet.ayetNo + "/words");

				for (JsonNode jKelime : root.get("data")) {
					if (jKelime == null || jKelime.isNull()) {
						continue;
					}
					if (jKelime.get("id") == null || jKelime.get("id").isNull()) {
						continue;
					}
					Kelime kelime = new Kelime();
					kelime.id = jKelime.get("id").asLong();
					kelime.sureId = sure.id;
					kelime.ayetId = ayet.id;
					kelime.ayetNo = ayet.ayetNo;
					kelime.kelimeNo = jKelime.get("sort_number").asLong();
					kelime.kelime = jKelime.get("arabic").asText();
					kelime.meal = jKelime.get("turkish").asText();

					JsonNode jRoot = jKelime.get("root");
					if (jRoot != null && !jRoot.isNull()) {
						KokKelime kokKelime = new KokKelime();
						kokKelime.id = jRoot.get("id").asLong();
						kokKelime.kok = jRoot.get("arabic").asText();
						kokKelime.kokLatin = jRoot.get("latin").asText();
						entityManager.merge(kokKelime);

						kelime.kokId = kokKelime.id;
					}

					entityManager.merge(kelime);
				}
				System.out.println("Sure " + sure.id + " Ayet: " + ayet.ayetNo);

				entityManager.flush();
				entityManager.clear();
			}
		}
	}

	public List<Kelime> getAllKelime() {
		List<Kelime> kelimeList = (List<Kelime>) kelimeRepo.findAll();
		Collections.sort(kelimeList);
//		test();
		return kelimeList;
	}

	public List<KokKelime> getAllKokKelime() {
		List<KokKelime> kokKelimeList = (List<KokKelime>) kokKelimeRepo.findAll();
		Collections.sort(kokKelimeList);
		return kokKelimeList;
	}

	public void test() {

		List<Sure> sureList = sureService.getAll();
		Collections.sort(sureList);

		List<MyTest> myTestList = new java.util.ArrayList<>();

		for (Sure sure : sureList) {
			MyTest myTest = new MyTest();
			myTest.sureNo = sure.id;
			myTest.sureAdiTr = sure.adTr;
			myTest.sureAdiAr = sure.adAr;

			List<Kelime> sureKelimeleri = kelimeRepo.findBySureId(sure.id);
			Collections.sort(sureKelimeleri);

			for (Kelime sureKelime : sureKelimeleri) {
				MyTest2 myTest2 = new MyTest2();
				myTest2.ayetNo = sureKelime.ayetNo;

				List<Kelime> sureAyetKelimeleri = kelimeRepo.findBySureIdAndAyetNo(sure.id, sureKelime.ayetNo);
				Collections.sort(sureAyetKelimeleri);

				for (Kelime ayetKelime : sureAyetKelimeleri) {
					MyTest3 myTest3 = new MyTest3();
					myTest3.kelimeNo = ayetKelime.kelimeNo;
					myTest3.turkce = ayetKelime.meal;
					myTest3.arapca = ayetKelime.kelime;
					myTest2.kelimeList.add(myTest3);
				}
				myTest.ayet = myTest2;
			}
			
			myTestList.add(myTest);
		}

	}

	public class MyTest {
		Long sureNo;
		String sureAdiTr;
		String sureAdiAr;
		MyTest2 ayet = new MyTest2();
	}

	public class MyTest2 {
		Long ayetNo;
		List<MyTest3> kelimeList = new java.util.ArrayList<>();
	}

	public class MyTest3 {
		Long kelimeNo;
		String turkce;
		String arapca;
	}
}
