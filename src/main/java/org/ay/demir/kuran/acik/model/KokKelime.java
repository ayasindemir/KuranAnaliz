package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kok_kelime", schema = "acik_kuran")
public class KokKelime implements Comparable<KokKelime> {

	@Id
	public Long id;

	public String kok;

	public String kokLatin;

	@Override
	public int compareTo(KokKelime o) {
		return this.kok.compareTo(o.kok);
	}
}
