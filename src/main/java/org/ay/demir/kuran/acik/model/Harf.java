package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "harf", schema = "acik_kuran")
public class Harf implements Comparable<Harf> {

	@Id
	public Long id;

	public String harfAr;

	@Override
	public int compareTo(Harf h) {
		return this.harfAr.compareTo(h.harfAr);
	}

}
