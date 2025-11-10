package org.ay.demir.kuran.kelime;

import java.util.List;

public class SimpleKelimeMeal {

	private int sn;

	private List<SimpleAyet> al;

	public SimpleKelimeMeal(int sureNo, List<SimpleAyet> ayetList) {
		this.sn = sureNo;
		this.al = ayetList;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public List<SimpleAyet> getAl() {
		return al;
	}

	public void setAl(List<SimpleAyet> al) {
		this.al = al;
	}

}
