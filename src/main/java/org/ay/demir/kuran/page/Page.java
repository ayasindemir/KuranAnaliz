package org.ay.demir.kuran.page;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pages")
public class Page {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "page_index", nullable = false, unique = true)
	private int pageIndex;

	@OneToMany(mappedBy = "page", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<SuraVerses> suraVerses;

	public Long getId() {
		return id;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<SuraVerses> getSuraVerses() {
		return suraVerses;
	}

	public void setSuraVerses(List<SuraVerses> suraVerses) {
		this.suraVerses = suraVerses;
	}
}
