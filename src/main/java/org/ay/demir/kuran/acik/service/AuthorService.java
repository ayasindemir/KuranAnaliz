package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Author;
import org.ay.demir.kuran.acik.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorsRepo;

	public void downloadAuthors() throws Exception {
		List<Author> authorsList = AcikKuranUtils.downloadAuthors();
		authorsRepo.saveAll(authorsList);
	}

	public List<Author> getAll() {
		return (List<Author>) authorsRepo.findAll();
	}

	public Author getById(Long id) {
		return authorsRepo.findById(id).orElse(null);
	}

}
