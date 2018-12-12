package com.version.data;

import java.io.Serializable;

public class Card implements Serializable {
	private static final long serialVersionUID = -7444549915829027146L;
	private int color;// 花色 1方块,2梅花, 3红桃,4黑桃,5小王,6大王,7花牌
	private Integer num;// 点数 2-14小王15,大王16,花牌17 A===>14
	private boolean laizi;// 是否为癞子
	private boolean isShow = true;// 是否显示
	private int cardId;// 手牌id

	public Card(int color, int num, boolean laizi, boolean isShow) {
		this.color = color;
		this.num = num;
		this.laizi = laizi;
		this.isShow = isShow;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public boolean isLaizi() {
		return laizi;
	}

	public void setLaizi(boolean laizi) {
		this.laizi = laizi;
	}

	public Card() {
		super();
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	@Override
	public String toString() {
		return "Card [color=" + color + ", num=" + num + ", laizi=" + laizi + ", isShow=" + isShow + ", cardId="
				+ cardId + "]";
	}

}
