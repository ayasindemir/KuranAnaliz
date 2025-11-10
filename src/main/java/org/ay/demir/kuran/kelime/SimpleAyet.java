package org.ay.demir.kuran.kelime;

import java.util.List;

public class SimpleAyet {

	private int an;

	private List<SimpleKelime> kl;

	public SimpleAyet(int ayetNo, List<SimpleKelime> kelimeler) {
		this.an = ayetNo;
		this.kl = kelimeler;
	}

	public int getAn() {
		return an;
	}

	public void setAn(int an) {
		this.an = an;
	}

	public List<SimpleKelime> getKl() {
		return kl;
	}

	public void setKl(List<SimpleKelime> kl) {
		this.kl = kl;
	}

}
