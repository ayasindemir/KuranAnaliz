package org.ay.demir.kuran.acik.service;

import java.util.List;
import java.util.Optional;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.RootChar;
import org.ay.demir.kuran.acik.repository.RootCharRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootCharService {

	@Autowired
	private RootCharRepository rootCharsRepo;

	public void downloadRootChars() throws Exception {
		List<RootChar> rootCharsList = AcikKuranUtils.downloadRootChars();
		rootCharsRepo.saveAll(rootCharsList);
	}

	public List<RootChar> getAll() {
		return (List<RootChar>) rootCharsRepo.findAll();
	}

	public Optional<RootChar> findById(Long id) {
		return rootCharsRepo.findById(id);
	}

}
