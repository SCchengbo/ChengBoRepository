package com.version.data.vo;

import java.io.Serializable;

public class PlayerTmpVO implements Serializable {
	private static final long serialVersionUID = 7237056398229845410L;
	private String uniqueId;
	private CalVO calVO;

	public PlayerTmpVO(String uniqueId, CalVO calVO) {
		this.uniqueId = uniqueId;
		this.calVO = calVO;
	}

	public PlayerTmpVO() {
		super();
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public CalVO getCalVO() {
		return calVO;
	}

	public void setCalVO(CalVO calVO) {
		this.calVO = calVO;
	}

}
