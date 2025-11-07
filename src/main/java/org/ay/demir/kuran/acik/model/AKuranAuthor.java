package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_author", schema = "kuran")
public class AKuranAuthor implements Comparable<AKuranAuthor> {

	private Long id;
	private String descr;
	private String lang;
	private String name;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescr() {
		return descr == null ? name : descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(AKuranAuthor a) {
		return this.id.compareTo(a.id);
	}
}
