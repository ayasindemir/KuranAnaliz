package org.ay.demir.kuran.mushaf;

import java.util.List;

import org.ay.demir.kuran.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MushafService {

	@Autowired
	MushafRepository mushafRepo;

	public void uploadMushafFromFile() throws Exception {
		List<Mushaf> mushafList = FileUtils.uploadMushafFromTxtFile();

		for (Mushaf mushaf : mushafList) {

			if (mushaf.getAyet().contains("بِسْمِ اللَّهِ الرَّحْمَـٰنِ الرَّحِيمِ")) {
				if (mushaf.getSureNo() == 1) {
					continue;
				} else if (mushaf.getSureNo() == 27 && mushaf.getAyetNo() == 30) {
					continue;
				} else {
					String newAyet = mushaf.getAyet().replaceAll("بِسْمِ اللَّهِ الرَّحْمَـٰنِ الرَّحِيمِ", "").trim();
					mushaf.setAyet(newAyet);
				}
			}
		}
		System.out.println("Mushaf upload is done. Total ayet count: " + mushafList.size());

		mushafRepo.saveAll(mushafList);
	}

	public List<Mushaf> getBySureNo(int sureNo) {
		return (List<Mushaf>) mushafRepo.findBySureNo(sureNo);
	}

	public List<Mushaf> getBySureAndAyetNo(int sureNo, int ayetNo) {
		return (List<Mushaf>) mushafRepo.findBySureNoAndAyetNo(sureNo, ayetNo);
	}

	public List<Mushaf> getAllSurelerFromDB() {
		return (List<Mushaf>) mushafRepo.findAll();
	}

}
