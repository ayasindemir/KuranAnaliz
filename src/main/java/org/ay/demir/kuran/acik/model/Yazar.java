package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "yazar")
public class Yazar implements Comparable<Yazar> {

	@Id
	public Long id;

	public String dil;

	public String ad;

	@Override
	public int compareTo(Yazar a) {
		return this.ad.compareTo(a.ad);
	}
}
