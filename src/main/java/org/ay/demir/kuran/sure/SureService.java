package org.ay.demir.kuran.sure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import org.ay.demir.kuran.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SureService {

	@Autowired
	SureRepository sureRepo;

	public void uploadSurelerFromFile() throws Exception {
		TreeMap<Integer, String> sureMap = FileUtils.uploadSurelerFromTxtFile();

		Set<Integer> keySet = sureMap.keySet();

		List<Sure> sureList = new ArrayList<Sure>();
		for (Iterator<Integer> it = keySet.iterator(); it.hasNext();) {
			Integer sirano = it.next();
			String ad = sureMap.get(sirano);

			Sure s = new Sure();
			s.setSureNo(sirano);
			s.setSureAdi(ad);
			sureList.add(s);
		}

		sureRepo.saveAll(sureList);
	}

	public List<Sure> getAllSurelerFromDB() {
		return (List<Sure>) sureRepo.findAll();
	}

}
