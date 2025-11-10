package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_words", schema = "kuran")
public class AKuranWords {

	private Long id;
	private Long sortNumber;
	private String transcription;
	private String arabic;
	private String turkish;
	private Long surahId;
	private Long verseId;
	private Long rootId;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Long sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getTranscription() {
		return transcription;
	}

	public void setTranscription(String transcription) {
		this.transcription = transcription;
	}

	public String getArabic() {
		return arabic;
	}

	public void setArabic(String arabic) {
		this.arabic = arabic;
	}

	public String getTurkish() {
		return turkish;
	}

	public void setTurkish(String turkish) {
		this.turkish = turkish;
	}

	public Long getSurahId() {
		return surahId;
	}

	public void setSurahId(Long surahId) {
		this.surahId = surahId;
	}

	public Long getVerseId() {
		return verseId;
	}

	public void setVerseId(Long verseId) {
		this.verseId = verseId;
	}

	public Long getRootId() {
		return rootId;
	}

	public void setRootId(Long rootId) {
		this.rootId = rootId;
	}

}
