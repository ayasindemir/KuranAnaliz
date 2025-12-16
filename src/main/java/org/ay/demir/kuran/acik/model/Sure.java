package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sure", schema = "acik_kuran")
public class Sure implements Comparable<Sure> {

	@Id
	public Long id;

	public String adTr;

	public String adEn;

	public String adAr;

	public Integer ayetSayisi;

	public Integer baslangicSayfasi;

	@Override
	public int compareTo(Sure s) {
		return this.id.compareTo(s.id);
	}
}
