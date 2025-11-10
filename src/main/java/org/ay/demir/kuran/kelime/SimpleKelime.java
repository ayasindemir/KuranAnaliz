package org.ay.demir.kuran.kelime;

public class SimpleKelime {

	private int kn;

	private String ar;

	private String mt;

	private String ak;

	public SimpleKelime(int kelimeNo, String arapcaHarekeli, String mealTr, String arapcaKok) {
		this.kn = kelimeNo;
		this.ar = arapcaHarekeli;
		this.mt = mealTr;
		this.ak = arapcaKok;
	}

	public int getKn() {
		return kn;
	}

	public void setKn(int kn) {
		this.kn = kn;
	}

	public String getAr() {
		return ar;
	}

	public void setAr(String ar) {
		this.ar = ar;
	}

	public String getMt() {
		return mt;
	}

	public void setMt(String mt) {
		this.mt = mt;
	}

	public String getAk() {
		return ak;
	}

	public void setAk(String ak) {
		this.ak = ak;
	}
}
