package org.ay.demir.kuran.acik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meal")
public class Meal implements Comparable<Meal> {

	@Id
	@JsonIgnore
	public Long id;

	@Column(length = 4000)
	public String meal;

	@JsonIgnore
	public Long yazarId;

	public Long sureId;

	public Long ayetNo;

	@Override
	public int compareTo(Meal a) {
		int c = this.sureId.compareTo(a.sureId);
		if (c == 0) {
			c = this.ayetNo.compareTo(a.ayetNo);
		}

		return c;
	}

}
