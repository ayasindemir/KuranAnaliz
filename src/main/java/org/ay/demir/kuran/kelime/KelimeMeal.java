package org.ay.demir.kuran.kelime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "detay", schema = "kuran", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "ayet_no", "yazar" }) })
public class KelimeMeal {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "cuz_no")
	private String cuzNo;

	@Column(name = "hizip_no")
	private String hizipNo;

	@Column(name = "sure_no")
	private String sureNo;

	@Column(name = "sure_ad")
	private String sureAdi;

	@Column(name = "ayet_no")
	private String ayetNo;

	@Column(name = "kelime_no")
	private String kelimeNo;

	@Column(name = "latin")
	private String latin;

	@Column(name = "arapca_harekeli")
	private String arapcaHarekeli;

	@Column(name = "meal_tr")
	private String mealTr;

	@Column(name = "arapca_harekesiz")
	private String arapcaHarekesiz;

	@Column(name = "latin_kok")
	private String latinKok;

	@Column(name = "arapca_kok")
	private String arapcaKok;

	@Column(name = "kok_harf_sayisi")
	private String kokHarfSayisi;

	@Column(name = "kok4_harf")
	private String kok4Harf;

	@Column(name = "kok3_harf")
	private String kok3Harf;

	@Column(name = "kok2_harf")
	private String kok2Harf;

	@Column(name = "kok1_harf")
	private String kok1Harf;

	@Column(name = "harf_sira4")
	private String harfSiraNo4;

	@Column(name = "harf_sira3")
	private String harfSiraNo3;

	@Column(name = "harf_sira2")
	private String harfSiraNo2;

	@Column(name = "harf_sira1")
	private String harfSiraNo1;

	@Column(name = "harfler_latin")
	private String harflerLatince;

	@Column(name = "harfler_arapca")
	private String harflerArapca;

	@Column(name = "irab_tr")
	private String irabTurkce;

	@Column(name = "meal_eng")
	private String mealEng;

	@Column(name = "fiil_turu")
	private String fiilTuru;

	@Column(name = "pasif")
	private String pasif;

	@Column(name = "zamir_turu")
	private String zamirTuru;

	@Column(name = "babi")
	private String babi;

	@Column(name = "hemzesiz_harekesiz_ar")
	private String hemzesizHarekesizArapca;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCuzNo() {
		return cuzNo;
	}

	public void setCuzNo(String cuzNo) {
		this.cuzNo = cuzNo;
	}

	public String getHizipNo() {
		return hizipNo;
	}

	public void setHizipNo(String hizipNo) {
		this.hizipNo = hizipNo;
	}

	public String getSureNo() {
		return sureNo;
	}

	public void setSureNo(String sureNo) {
		this.sureNo = sureNo;
	}

	public String getSureAdi() {
		return sureAdi;
	}

	public void setSureAdi(String sureAdi) {
		this.sureAdi = sureAdi;
	}

	public String getAyetNo() {
		return ayetNo;
	}

	public void setAyetNo(String ayetNo) {
		this.ayetNo = ayetNo;
	}

	public String getKelimeNo() {
		return kelimeNo;
	}

	public void setKelimeNo(String kelimeNo) {
		this.kelimeNo = kelimeNo;
	}

	public String getLatin() {
		return latin;
	}

	public void setLatin(String latin) {
		this.latin = latin;
	}

	public String getArapcaHarekeli() {
		return arapcaHarekeli;
	}

	public void setArapcaHarekeli(String arapcaHarekeli) {
		this.arapcaHarekeli = arapcaHarekeli;
	}

	public String getMealTr() {
		return mealTr;
	}

	public void setMealTr(String mealTr) {
		this.mealTr = mealTr;
	}

	public String getArapcaHarekesiz() {
		return arapcaHarekesiz;
	}

	public void setArapcaHarekesiz(String arapcaHarekesiz) {
		this.arapcaHarekesiz = arapcaHarekesiz;
	}

	public String getLatinKok() {
		return latinKok;
	}

	public void setLatinKok(String latinKok) {
		this.latinKok = latinKok;
	}

	public String getArapcaKok() {
		return arapcaKok;
	}

	public void setArapcaKok(String arapcaKok) {
		this.arapcaKok = arapcaKok;
	}

	public String getKokHarfSayisi() {
		return kokHarfSayisi;
	}

	public void setKokHarfSayisi(String kokHarfSayisi) {
		this.kokHarfSayisi = kokHarfSayisi;
	}

	public String getKok4Harf() {
		return kok4Harf;
	}

	public void setKok4Harf(String kok4Harf) {
		this.kok4Harf = kok4Harf;
	}

	public String getKok3Harf() {
		return kok3Harf;
	}

	public void setKok3Harf(String kok3Harf) {
		this.kok3Harf = kok3Harf;
	}

	public String getKok2Harf() {
		return kok2Harf;
	}

	public void setKok2Harf(String kok2Harf) {
		this.kok2Harf = kok2Harf;
	}

	public String getKok1Harf() {
		return kok1Harf;
	}

	public void setKok1Harf(String kok1Harf) {
		this.kok1Harf = kok1Harf;
	}

	public String getHarfSiraNo4() {
		return harfSiraNo4;
	}

	public void setHarfSiraNo4(String harfSiraNo4) {
		this.harfSiraNo4 = harfSiraNo4;
	}

	public String getHarfSiraNo3() {
		return harfSiraNo3;
	}

	public void setHarfSiraNo3(String harfSiraNo3) {
		this.harfSiraNo3 = harfSiraNo3;
	}

	public String getHarfSiraNo2() {
		return harfSiraNo2;
	}

	public void setHarfSiraNo2(String harfSiraNo2) {
		this.harfSiraNo2 = harfSiraNo2;
	}

	public String getHarfSiraNo1() {
		return harfSiraNo1;
	}

	public void setHarfSiraNo1(String harfSiraNo1) {
		this.harfSiraNo1 = harfSiraNo1;
	}

	public String getHarflerLatince() {
		return harflerLatince;
	}

	public void setHarflerLatince(String harflerLatince) {
		this.harflerLatince = harflerLatince;
	}

	public String getHarflerArapca() {
		return harflerArapca;
	}

	public void setHarflerArapca(String harflerArapca) {
		this.harflerArapca = harflerArapca;
	}

	public String getIrabTurkce() {
		return irabTurkce;
	}

	public void setIrabTurkce(String irabTurkce) {
		this.irabTurkce = irabTurkce;
	}

	public String getMealEng() {
		return mealEng;
	}

	public void setMealEng(String mealEng) {
		this.mealEng = mealEng;
	}

	public String getFiilTuru() {
		return fiilTuru;
	}

	public void setFiilTuru(String fiilTuru) {
		this.fiilTuru = fiilTuru;
	}

	public String getPasif() {
		return pasif;
	}

	public void setPasif(String pasif) {
		this.pasif = pasif;
	}

	public String getZamirTuru() {
		return zamirTuru;
	}

	public void setZamirTuru(String zamirTuru) {
		this.zamirTuru = zamirTuru;
	}

	public String getBabi() {
		return babi;
	}

	public void setBabi(String babi) {
		this.babi = babi;
	}

	public String getHemzesizHarekesizArapca() {
		return hemzesizHarekesizArapca;
	}

	public void setHemzesizHarekesizArapca(String hemzesizHarekesizArapca) {
		this.hemzesizHarekesizArapca = hemzesizHarekesizArapca;
	}

	@Override
	public String toString() {
		return "KuranDetay [id=" + id + ", cuzNo=" + cuzNo + ", hizipNo=" + hizipNo + ", sureNo=" + sureNo
				+ ", sureAdi=" + sureAdi + ", ayetNo=" + ayetNo + ", kelimeNo=" + kelimeNo + ", latin=" + latin
				+ ", arapcaHarekeli=" + arapcaHarekeli + ", mealTr=" + mealTr + ", arapcaHarekesiz=" + arapcaHarekesiz
				+ ", latinKok=" + latinKok + ", arapcaKok=" + arapcaKok + ", kokHarfSayisi=" + kokHarfSayisi
				+ ", kok4Harf=" + kok4Harf + ", kok3Harf=" + kok3Harf + ", kok2Harf=" + kok2Harf + ", kok1Harf="
				+ kok1Harf + ", harfSiraNo4=" + harfSiraNo4 + ", harfSiraNo3=" + harfSiraNo3 + ", harfSiraNo2="
				+ harfSiraNo2 + ", harfSiraNo1=" + harfSiraNo1 + ", harflerLatince=" + harflerLatince
				+ ", harflerArapca=" + harflerArapca + ", irabTurkce=" + irabTurkce + ", mealEng=" + mealEng
				+ ", fiilTuru=" + fiilTuru + ", pasif=" + pasif + ", zamirTuru=" + zamirTuru + ", babi=" + babi
				+ ", hemzesizHarekesizArapca=" + hemzesizHarekesizArapca + "]";
	}

}
