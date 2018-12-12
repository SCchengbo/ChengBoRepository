package com.version.data.sceen;

import java.io.Serializable;

public class LiangPaiOperate implements Serializable {
	private static final long serialVersionUID = -3850976405125954243L;
	private String uniqueId;

	public LiangPaiOperate() {
		super();
	}

	public LiangPaiOperate(String uniqueId) {
		super();
		this.uniqueId = uniqueId;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
}
