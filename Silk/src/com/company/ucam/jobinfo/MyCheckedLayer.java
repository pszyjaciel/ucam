package com.company.ucam.jobinfo;

public class MyCheckedLayer {

	String lName;
	boolean lTypeSilk;
	boolean lTypeOutline;
	boolean lTypeOuter;

	public MyCheckedLayer(String name, boolean tSilk, boolean tOutline, boolean tOuter) {
		this.lName = name;
		this.lTypeOuter = tOuter;
		this.lTypeOutline = tOutline;
		this.lTypeSilk = tSilk;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public boolean islTypeSilk() {
		return lTypeSilk;
	}

	public void setlTypeSilk(boolean lTypeSilk) {
		this.lTypeSilk = lTypeSilk;
	}

	public boolean islTypeOutline() {
		return lTypeOutline;
	}

	public void setlTypeOutline(boolean lTypeOutline) {
		this.lTypeOutline = lTypeOutline;
	}

	public boolean islTypeOuter() {
		return lTypeOuter;
	}

	public void setlTypeOuter(boolean lTypeOuter) {
		this.lTypeOuter = lTypeOuter;
	}
}
