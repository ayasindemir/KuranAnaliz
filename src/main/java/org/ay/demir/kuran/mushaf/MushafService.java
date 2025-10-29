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
