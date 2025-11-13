package org.ay.demir.kuran.page;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "verses")
public class Verses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "verse_index")
	private int index;

	@Column(name = "text", columnDefinition = "TEXT")
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sura_verses_id")
	private SuraVerses suraVerses;

	public Long getId() {
		return id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public SuraVerses getSuraVerses() {
		return suraVerses;
	}

	public void setSuraVerses(SuraVerses suraVerses) {
		this.suraVerses = suraVerses;
	}
}
