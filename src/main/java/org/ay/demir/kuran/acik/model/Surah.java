package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_surah", schema = "kuran")
public class Surah implements Comparable<Surah> {

	private Long id;
	private String nameTr;
	private String nameEng;
	private String nameArabic;
	private Integer verseCount;
	private Integer pageNumber;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameTr() {
		return nameTr;
	}

	public void setNameTr(String nameTr) {
		this.nameTr = nameTr;
	}

	public String getNameEng() {
		return nameEng;
	}

	public void setNameEng(String nameEng) {
		this.nameEng = nameEng;
	}

	public String getNameArabic() {
		return nameArabic;
	}

	public void setNameArabic(String nameArabic) {
		this.nameArabic = nameArabic;
	}

	public Integer getVerseCount() {
		return verseCount;
	}

	public void setVerseCount(Integer verseCount) {
		this.verseCount = verseCount;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public int compareTo(Surah s) {
		return this.id.compareTo(s.id);
	}
}
