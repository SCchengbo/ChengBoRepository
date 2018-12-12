package com.version.data.cal;

import java.util.HashMap;
import java.util.Map;

import com.version.common.entity.BigPanel;

public class BigCalPanel extends BigPanel {
	private static final long serialVersionUID = -7978036927652353789L;
	private final Map<String, Integer> niuniuCount = new HashMap<String, Integer>();// 牛牛次数
	private final Map<String, Integer> winCount = new HashMap<String, Integer>();// 赢次数
	private final Map<String, Integer> loseCount = new HashMap<String, Integer>();// 输次数
	private final Map<String, Integer> tongShaCount = new HashMap<String, Integer>();// 通杀次数
	private final Map<String, Integer> tongpeiCount = new HashMap<String, Integer>();// 通赔次数
	private final Map<String, Integer> scoreMap = new HashMap<String, Integer>();// 赢的总积分
	private final Map<String,CalRole> bigCalRoles = new HashMap<String, CalRole>();//大结算角色数据
	private String roomerId;
	private String roomId;
	private int totalTimes;
	
	public Map<String, Integer> getNiuniuCount() {
		return niuniuCount;
	}

	public Map<String, Integer> getWinCount() {
		return winCount;
	}

	public Map<String, Integer> getLoseCount() {
		return loseCount;
	}

	public Map<String, Integer> getTongShaCount() {
		return tongShaCount;
	}

	public Map<String, Integer> getTongpeiCount() {
		return tongpeiCount;
	}

	public Map<String, Integer> getScoreMap() {
		return scoreMap;
	}

	public String getRoomerId() {
		return roomerId;
	}

	public void setRoomerId(String roomerId) {
		this.roomerId = roomerId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public int getTotalTimes() {
		return totalTimes;
	}

	public void setTotalTimes(int totalTimes) {
		this.totalTimes = totalTimes;
	}

	public Map<String, CalRole> getBigCalRoles() {
		return bigCalRoles;
	}


}
