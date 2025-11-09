package org.ay.demir.kuran.sure;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "sure", schema = "kuran", uniqueConstraints = { @UniqueConstraint(columnNames = { "sure_no" }) })
public class Sure implements Comparable<Sure> {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "sure_no")
	private Integer sureNo;

	@Column(name = "ayet_sayisi")
	private Integer ayetSayisi;

	@Column(name = "sure_ad")
	private String sureAdi;

	@Column(name = "sure_ad_arapca")
	private String sureAdiArapca;

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

	public Integer getAyetSayisi() {
		return ayetSayisi;
	}

	public void setAyetSayisi(Integer ayetSayisi) {
		this.ayetSayisi = ayetSayisi;
	}

	public String getSureAdi() {
		return sureAdi;
	}

	public void setSureAdi(String sureAdi) {
		this.sureAdi = sureAdi;
	}

	public String getSureAdiArapca() {
		return sureAdiArapca;
	}

	public void setSureAdiArapca(String sureAdiArapca) {
		this.sureAdiArapca = sureAdiArapca;
	}

	@Override
	public String toString() {
		return "Sure [sureNo=" + sureNo + ", ayetSayisi=" + ayetSayisi + ", sureAdi=" + sureAdi + ", sureAdiArapca="
				+ sureAdiArapca + "]";
	}

	@Override
	public int compareTo(Sure o) {
		return this.sureNo.compareTo(o.sureNo);
	}

}
