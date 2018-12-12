package com.version.data.cal;

import java.util.HashMap;
import java.util.Map;

import com.version.common.entity.SmallPanel;

public class SmallCalPanel extends SmallPanel {
	private static final long serialVersionUID = 6751891366219666214L;
	private int times;
	private final Map<String, SmallCalRole> roles = new HashMap<String, SmallCalRole>();// 角色基础数据
	private String landLordId;// 地主id
	private int diFen;// 本局底分
	private int cardMultiple;// 牌型总分
	private int winType;// 输赢类型
	private int zhaDanMultiple;// 炸弹倍数
	private int publicMultiple;// 公共倍数
	private int chunTianMultiple;// 春天倍数

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public Map<String, SmallCalRole> getRoles() {
		return roles;
	}

	public String getLandLordId() {
		return landLordId;
	}

	public void setLandLordId(String landLordId) {
		this.landLordId = landLordId;
	}

	public int getDiFen() {
		return diFen;
	}

	public void setDiFen(int diFen) {
		this.diFen = diFen;
	}

	public int getCardMultiple() {
		return cardMultiple;
	}

	public void setCardMultiple(int cardMultiple) {
		this.cardMultiple = cardMultiple;
	}

	public int getWinType() {
		return winType;
	}

	public void setWinType(int winType) {
		this.winType = winType;
	}

	public int getZhaDanMultiple() {
		return zhaDanMultiple;
	}

	public void setZhaDanMultiple(int zhaDanMultiple) {
		this.zhaDanMultiple = zhaDanMultiple;
	}

	public int getPublicMultiple() {
		return publicMultiple;
	}

	public void setPublicMultiple(int publicMultiple) {
		this.publicMultiple = publicMultiple;
	}

	public int getChunTianMultiple() {
		return chunTianMultiple;
	}

	public void setChunTianMultiple(int chunTianMultiple) {
		this.chunTianMultiple = chunTianMultiple;
	}

}
