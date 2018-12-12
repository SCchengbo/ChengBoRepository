package com.version.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtendCache implements Serializable {
	private static final long serialVersionUID = 7156999736804185709L;
	private Integer lastBankerPos;// 上一局庄家位置
	private final List<Integer> lastNiuniuPosList = new ArrayList<Integer>();// 上局牛牛的位置
	private int dissolutionCount;// 当前解散的次数
	private final List<String> maxCardTypeControllerUniqueIds = new ArrayList<String>();// 最大牌类型的玩家的id
	private final Map<String,Integer> tuzhu = new HashMap<String, Integer>();//推注数
	public List<Integer> getLastNiuniuPosList() {
		return lastNiuniuPosList;
	}

	public int getDissolutionCount() {
		return dissolutionCount;
	}

	public void setDissolutionCount(int dissolutionCount) {
		this.dissolutionCount = dissolutionCount;
	}

	public List<String> getMaxCardTypeControllerUniqueIds() {
		return maxCardTypeControllerUniqueIds;
	}

	public Integer getLastBankerPos() {
		return lastBankerPos;
	}

	public void setLastBankerPos(Integer lastBankerPos) {
		this.lastBankerPos = lastBankerPos;
	}

	public Map<String, Integer> getTuzhu() {
		return tuzhu;
	}
	
	
}

