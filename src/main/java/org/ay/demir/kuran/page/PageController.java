package org.ay.demir.kuran.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "page")
public class PageController {

	@Autowired
	private PageService pageService;

	@PostMapping(path = "uploadPages")
	public void uploadFromFile() throws Exception {
		pageService.uploadPagesFromFile();
	}

}
