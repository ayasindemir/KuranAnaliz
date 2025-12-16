package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ayet")
public class Ayet implements Comparable<Ayet> {

	@Id
	public Long id;

	public Long sureId;

	public Long ayetNo;

	@Column(length = 4000)
	public String ayetAr;

	public Integer sayfaNo;

	public Integer cuzNo;

	@Column(length = 4000)
	public String okunusu;

	@Override
	public int compareTo(Ayet a) {
		int c = this.sureId.compareTo(a.sureId);
		if (c == 0) {
			c = this.ayetNo.compareTo(a.ayetNo);
		}
		return c;
	}

}
