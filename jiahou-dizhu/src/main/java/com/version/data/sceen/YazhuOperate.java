package com.version.data.sceen;

import java.io.Serializable;

public class YazhuOperate implements Serializable {
	private static final long serialVersionUID = 4010391114080858447L;
	private String uniqueId;
	private int chooseFan;
	public String getUniqueId() {
		return uniqueId;
	}
	public YazhuOperate(String uniqueId, int chooseFan) {
		super();
		this.uniqueId = uniqueId;
		this.chooseFan = chooseFan;
	}
	public YazhuOperate() {
		super();
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public int getChooseFan() {
		return chooseFan;
	}
	public void setChooseFan(int chooseFan) {
		this.chooseFan = chooseFan;
	}
}
