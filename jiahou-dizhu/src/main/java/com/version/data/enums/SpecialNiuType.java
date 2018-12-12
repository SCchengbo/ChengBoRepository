package com.version.data.enums;

import java.io.Serializable;

public enum SpecialNiuType implements Serializable {
	WU_XIAO_NIU(14), ZHA_DAN_NIU(13), HU_LU_NIU(12), WU_HUA_NIU(11);
	
	private int niuType;

	private SpecialNiuType(int niuType) {
		this.niuType = niuType;
	}

	public int getNiuType() {
		return niuType;
	}

	public void setNiuType(int niuType) {
		this.niuType = niuType;
	}
}
