package com.version.data.enums;

import java.io.Serializable;

public enum CardColor implements Serializable {
	FANGKUAI(1, "方块"), MEIHUA(2, "梅花"), HONGTAO(3, "红桃"), HEITAO(4, "黑桃"), XIAOWANG(
			5, "小王"), DAWANG(6, "大王"), HUAPAI(7, "花牌");
	private int color;
	private String name;

	private CardColor(int color, String name) {
		this.color = color;
		this.name = name;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
