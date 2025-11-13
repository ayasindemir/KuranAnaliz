package org.ay.demir.kuran.page;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sura_verses")
public class SuraVerses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sura_name", nullable = false)
	private String suraName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id")
	private Page page;

	@OneToMany(mappedBy = "suraVerses", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Verses> verses;

	public Long getId() {
		return id;
	}

	public String getSuraName() {
		return suraName;
	}

	public void setSuraName(String suraName) {
		this.suraName = suraName;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Verses> getVerses() {
		return verses;
	}

	public void setVerses(List<Verses> verses) {
		this.verses = verses;
	}
}
