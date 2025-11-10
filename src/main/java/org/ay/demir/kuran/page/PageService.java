package org.ay.demir.kuran.page;

import java.util.List;

import org.ay.demir.kuran.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

	@Autowired
	PageRepository pageRepo;

	public void uploadPagesFromFile() throws Exception {
		List<Page> pageList = FileUtils.uploadQuranPages();
		pageRepo.saveAll(pageList);
	}

}
