package org.ay.demir.kuran.acik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "a_audio", schema = "kuran", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class AKuranAudio {

	private Long id;
	private String mp3;
	private Integer duration;
	private String mp3En;
	private Integer durationEn;
	private Long surahId;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMp3() {
		return mp3;
	}

	public void setMp3(String mp3) {
		this.mp3 = mp3;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getMp3En() {
		return mp3En;
	}

	public void setMp3En(String mp3En) {
		this.mp3En = mp3En;
	}

	public Integer getDurationEn() {
		return durationEn;
	}

	public void setDurationEn(Integer durationEn) {
		this.durationEn = durationEn;
	}

	public Long getSurahId() {
		return surahId;
	}

	public void setSurahId(Long surahId) {
		this.surahId = surahId;
	}

}
