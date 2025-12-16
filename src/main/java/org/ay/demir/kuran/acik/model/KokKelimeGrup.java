package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kok_kelime_grup", schema = "acik_kuran")
public class KokKelimeGrup implements Comparable<KokKelimeGrup> {

	@Id
	public Long id;

	public String kelime;

	public Integer toplam;

	public Long kokKelimeId;
	
	public String kokKelime;

	@Override
	public int compareTo(KokKelimeGrup o) {
		int c = this.kokKelimeId.compareTo(o.kokKelimeId);
		if (c == 0) {
			c = this.kelime.compareTo(o.kelime);
		}
		return 0;
	}

}
