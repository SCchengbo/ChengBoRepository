package com.version.data.cal;

import java.io.Serializable;

public class CalRole implements Serializable {
	private static final long serialVersionUID = 1896307261734860061L;
	private String headImg="";
	private String nick="";
	private String uniqueId;
	private String health="0";
	private int head;

	public CalRole(String headImg, int head, String nick, String uniqueId,
			String health) {
		super();
		this.headImg = headImg;
		this.head = head;
		this.nick = nick;
		this.uniqueId = uniqueId;
		this.health = health;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public CalRole() {
		super();
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}
}
