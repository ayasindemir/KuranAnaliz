package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_translation", schema = "kuran")
public class AKuranTranslation implements Comparable<AKuranTranslation> {

	private Long id;
	private String text;
	private Long authorId;
	private Long verseId;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 4000)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getVerseId() {
		return verseId;
	}

	public void setVerseId(Long verseId) {
		this.verseId = verseId;
	}

	@Override
	public int compareTo(AKuranTranslation a) {
		int c = this.authorId.compareTo(a.authorId);
		if (c == 0) {
			c = this.verseId.compareTo(a.verseId);
		}

		return c;

	}

}
