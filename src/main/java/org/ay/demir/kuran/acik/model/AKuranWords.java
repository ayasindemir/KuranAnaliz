package org.ay.demir.kuran.acik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "a_words", schema = "kuran")
public class AKuranWords implements Comparable<AKuranWords> {

	private Long id;
	private Long sort;
	private String ar;
	private String tr;
	private Long sN;
	private Long vN;
	private Long root;

	@Id
	@JsonIgnore
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "sort_number")
	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	@Column(name = "arabic")
	public String getAr() {
		return ar;
	}

	public void setAr(String ar) {
		this.ar = ar;
	}

	@Column(name = "turkish")
	public String getTr() {
		return tr;
	}

	public void setTr(String tr) {
		this.tr = tr;
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

	@Column(name = "root_id")
	public Long getRoot() {
		return root;
	}

	public void setRoot(Long root) {
		this.root = root;
	}

	@Override
	public int compareTo(AKuranWords o) {
		int c = this.sN.compareTo(o.sN);
		if (c == 0) {
			c = this.vN.compareTo(o.vN);
		}
		if (c == 0) {
			c = this.sort.compareTo(o.sort);
		}
		return c;
	}

}
