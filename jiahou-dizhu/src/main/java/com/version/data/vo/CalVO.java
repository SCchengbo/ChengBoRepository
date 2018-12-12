package com.version.data.vo;

import java.io.Serializable;
import java.util.List;

import com.version.data.Card;

public class CalVO implements Serializable {
	private static final long serialVersionUID = 8312159424187920373L;
	private List<Card> cards;
	private Integer multiple = 0;// 抢庄倍数
	private String uniqueId;// 玩家id
	private int score;
	private boolean isCtrlChooseMutiple;// 是否操作抢分
	private int pos;
	private int outCardCount;// 出牌次数统计 （春天或者反春）
	private boolean tuoGuanType = false;// 是否托管 false 表示不托管
	private boolean isLandlord;// 该玩家是不是地主

	public CalVO() {
		super();
	}

	public CalVO(List<Card> cards, String uniqueId, int pos) {
		super();
		this.cards = cards;
		this.pos = pos;
		this.uniqueId = uniqueId;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Integer getMultiple() {
		return multiple;
	}

	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

	public boolean isCtrlChooseMutiple() {
		return isCtrlChooseMutiple;
	}

	public void setCtrlChooseMutiple(boolean isCtrlChooseMutiple) {
		this.isCtrlChooseMutiple = isCtrlChooseMutiple;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getOutCardCount() {
		return outCardCount;
	}

	public void setOutCardCount(int outCardCount) {
		this.outCardCount = outCardCount;
	}

	public boolean isLandlord() {
		return isLandlord;
	}

	public void setLandlord(boolean isLandlord) {
		this.isLandlord = isLandlord;
	}

	public boolean isTuoGuanType() {
		return tuoGuanType;
	}

	public void setTuoGuanType(boolean tuoGuanType) {
		this.tuoGuanType = tuoGuanType;
	}

}
