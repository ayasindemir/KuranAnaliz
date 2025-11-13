package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_root_chars", schema = "kuran")
public class RootChar implements Comparable<RootChar> {

	private Long id;
	private String latin;
	private String arabic;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLatin() {
		return latin;
	}

	public void setLatin(String latin) {
		this.latin = latin;
	}

	public String getArabic() {
		return arabic;
	}

	public void setArabic(String arabic) {
		this.arabic = arabic;
	}

	@Override
	public int compareTo(RootChar rc) {
		return this.id.compareTo(rc.id);
	}

}
