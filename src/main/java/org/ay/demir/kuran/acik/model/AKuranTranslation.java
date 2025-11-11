package org.ay.demir.kuran.acik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_translation", schema = "kuran")
public class AKuranTranslation implements Comparable<AKuranTranslation> {

	private Long id;
	private String txt;
	private Long authorId;
	private Long verseId;
	private Long vN;
	private Long sN;

	@JsonIgnore
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "text", length = 4000)
	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	@JsonIgnore
	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	@JsonIgnore
	public Long getVerseId() {
		return verseId;
	}

	public void setVerseId(Long verseId) {
		this.verseId = verseId;
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

	@Override
	public int compareTo(AKuranTranslation a) {
		int c = this.sN.compareTo(a.sN);

		if (c == 0) {
			c = this.vN.compareTo(a.vN);
		}

		return c;
	}

}
