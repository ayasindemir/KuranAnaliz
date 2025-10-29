package org.ay.demir.kuran.mushaf;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "mushaf", schema = "kuran", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "sure_no", "ayet_no" }) })
public class Mushaf implements Comparable<Mushaf> {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "sure_no")
	private Integer sureNo;

	@Column(name = "ayet_no")
	private Integer ayetNo;

	@Column(name = "ayet", length = 2000)
	private String ayet;

	public Mushaf() {

	}

	public Mushaf(Integer sureNo, Integer ayetNo, String ayet) {
		this.sureNo = sureNo;
		this.ayetNo = ayetNo;
		this.ayet = ayet;
	}

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

	public String getAyet() {
		return ayet;
	}

	public void setAyet(String ayet) {
		this.ayet = ayet;
	}

	@Override
	public String toString() {
		return "Mushaf [sureNo=" + sureNo + ", ayetNo=" + ayetNo + ", ayet=" + ayet + "]";
	}

	@Override
	public int compareTo(Mushaf o) {
		int c = this.sureNo.compareTo(o.sureNo);
		if (c == 0) {
			c = this.ayetNo.compareTo(o.ayetNo);
		}

		return c;
	}

}
