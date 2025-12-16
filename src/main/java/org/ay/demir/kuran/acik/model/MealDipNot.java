package org.ay.demir.kuran.acik.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meal_dipnot", schema = "acik_kuran")
public class MealDipNot {

	@Id
	public Long id;

	@Column(length = 16300)
	public String dipNot;

	public Long notNo;

	public Long sureId;

	public Long ayetNo;

	public Long yazarId;

}
