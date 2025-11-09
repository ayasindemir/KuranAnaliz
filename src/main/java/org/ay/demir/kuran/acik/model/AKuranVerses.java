package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_verses", schema = "kuran")
public class AKuranVerses {

	private Long id;
	private Long surahId;
	private Long verseNumber;
	private String verse;
	private Integer page;
	private Integer juzNumber;
	private String transcription;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSurahId() {
		return surahId;
	}

	public void setSurahId(Long surahId) {
		this.surahId = surahId;
	}

	public Long getVerseNumber() {
		return verseNumber;
	}

	public void setVerseNumber(Long verseNumber) {
		this.verseNumber = verseNumber;
	}

	@Column(length = 4000)
	public String getVerse() {
		return verse;
	}

	public void setVerse(String verse) {
		this.verse = verse;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getJuzNumber() {
		return juzNumber;
	}

	public void setJuzNumber(Integer juzNumber) {
		this.juzNumber = juzNumber;
	}

	@Column(length = 4000)
	public String getTranscription() {
		return transcription;
	}

	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}

}
