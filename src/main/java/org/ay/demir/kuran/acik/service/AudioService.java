package org.ay.demir.kuran.acik.service;

import java.util.List;

import org.ay.demir.kuran.acik.model.Audio;
import org.ay.demir.kuran.acik.repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AudioService {

	@Autowired
	private AudioRepository audioRepo;

	public List<Audio> getAll() {
		return (List<Audio>) audioRepo.findAll();
	}

}
