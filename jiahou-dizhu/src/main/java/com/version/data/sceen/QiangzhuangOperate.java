package com.version.data.sceen;

import java.io.Serializable;

public class QiangzhuangOperate implements Serializable {
	private static final long serialVersionUID = -402552482596984218L;
	private String uniqueId;
	private int chooseFan;

	public QiangzhuangOperate() {
		super();
	}

	public QiangzhuangOperate(String uniqueId, int chooseFan) {
		super();
		this.uniqueId = uniqueId;
		this.chooseFan = chooseFan;
	}

	public String getUniqueId() {
		return uniqueId;
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
