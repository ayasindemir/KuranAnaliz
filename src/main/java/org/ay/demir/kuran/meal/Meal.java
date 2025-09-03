package org.ay.demir.kuran.meal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "meal", schema = "kuran", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "sure_no, ayet_no", "yazar" }) })
public class Meal {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "sure_no")
	private Integer sureNo;

	@Column(name = "ayet_no")
	private Integer ayetNo;

	@Column(name = "meal", length = 1500)
	private String meal;

	@Column(name = "yazar")
	private String yazar;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSureNo() {
		return sureNo;
	}

	public void setSureNo(Integer sureNo) {
		this.sureNo = sureNo;
	}

	public Integer getAyetNo() {
		return ayetNo;
	}

	public void setAyetNo(Integer ayetNo) {
		this.ayetNo = ayetNo;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getYazar() {
		return yazar;
	}

	public void setYazar(String yazar) {
		this.yazar = yazar;
	}
}
