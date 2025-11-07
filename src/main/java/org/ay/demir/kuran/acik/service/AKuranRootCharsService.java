package org.ay.demir.kuran.acik.service;

import java.util.List;
import java.util.Optional;

import org.ay.demir.kuran.acik.AKuranUtils;
import org.ay.demir.kuran.acik.model.AKuranRootChars;
import org.ay.demir.kuran.acik.repository.AKuranRootCharsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AKuranRootCharsService {

	@Autowired
	private AKuranRootCharsRepository rootCharsRepo;

	public void downloadRootChars() throws Exception {
		List<AKuranRootChars> rootCharsList = AKuranUtils.downloadRootChars();
		rootCharsRepo.saveAll(rootCharsList);
	}

	public List<AKuranRootChars> getAll() {
		return (List<AKuranRootChars>) rootCharsRepo.findAll();
	}

	public Optional<AKuranRootChars> findById(Long id) {
		return rootCharsRepo.findById(id);
	}

}
