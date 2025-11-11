package org.ay.demir.kuran.acik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_verses", schema = "kuran")
public class AKuranVerses {

	private Long id;
	private Long sN;
	private Long vN;
	private String verse;
	private Integer page;
	private Integer jN;
	private String pro;

	@Id
	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "surah_id")
	public Long getSN() {
		return sN;
	}

	public void setSN(Long sN) {
		this.sN = sN;
	}

	@Column(name = "verse_number")
	public Long getVN() {
		return vN;
	}

	public void setVN(Long vN) {
		this.vN = vN;
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

	@Column(name = "juz_number")
	public Integer getJN() {
		return jN;
	}

	public void setJN(Integer jN) {
		this.jN = jN;
	}

	@Column(name = "transcription", length = 4000)
	public String getPro() {
		return pro;
	}

	public void setPro(String pro) {
		this.pro = pro;
	}
}
