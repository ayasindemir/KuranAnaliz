package org.ay.demir.kuran.sure;

import java.util.List;

import org.ay.demir.kuran.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SureService {

	@Autowired
	SureRepository sureRepo;

	public void uploadSurelerFromFile() throws Exception {
		List<Sure> sureList = FileUtils.uploadSurelerFromTxtFile();
		sureRepo.saveAll(sureList);
	}

	public List<Sure> getAllSurelerFromDB() {
		return (List<Sure>) sureRepo.findAll();
	}

}
