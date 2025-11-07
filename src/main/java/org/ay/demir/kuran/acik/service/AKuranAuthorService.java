package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AKuranUtils;
import org.ay.demir.kuran.acik.model.AKuranAuthor;
import org.ay.demir.kuran.acik.repository.AKuranAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AKuranAuthorService {

	@Autowired
	private AKuranAuthorRepository authorsRepo;

	public void downloadAuthors() throws Exception {
		List<AKuranAuthor> authorsList = AKuranUtils.downloadAuthors();
		authorsRepo.saveAll(authorsList);
	}

	public List<AKuranAuthor> getAll() {
		return (List<AKuranAuthor>) authorsRepo.findAll();
	}

	public AKuranAuthor getById(Long id) {
		return authorsRepo.findById(id).orElse(null);
	}

}
