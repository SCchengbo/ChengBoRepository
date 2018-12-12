package com.version.data.config;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DouniuExtendConfig implements Serializable {
	private static final long serialVersionUID = -1201990508761840967L;
	private final Map<Integer, Integer> multipleMap = new HashMap<Integer, Integer>();// 牛-倍数
	private int qiangzhuangTimer;// 抢庄倒计时
	private int yazhuTimer;// 押注倒计时
	private int liangPaiTimer = 15000;// 亮牌倒计时

	public Map<Integer, Integer> getMultipleMap() {
		return multipleMap;
	}

	public int getQiangzhuangTimer() {
		return qiangzhuangTimer;
	}

	public void setQiangzhuangTimer(int qiangzhuangTimer) {
		this.qiangzhuangTimer = qiangzhuangTimer;
	}

	public int getYazhuTimer() {
		return yazhuTimer;
	}

	public void setYazhuTimer(int yazhuTimer) {
		this.yazhuTimer = yazhuTimer;
	}

	public int getLiangPaiTimer() {
		return liangPaiTimer;
	}

	public void setLiangPaiTimer(int liangPaiTimer) {
		this.liangPaiTimer = liangPaiTimer;
	}

	@Override
	public String toString() {
		return "DouniuExtendConfig [multipleMap=" + multipleMap + ", qiangzhuangTimer=" + qiangzhuangTimer
				+ ", yazhuTimer=" + yazhuTimer + ", liangPaiTimer=" + liangPaiTimer + "]";
	}

}
