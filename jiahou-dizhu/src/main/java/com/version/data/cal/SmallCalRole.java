package com.version.data.cal;

public class SmallCalRole extends CalRole {
	private static final long serialVersionUID = 1986356988717935074L;
	private int currentScore;// 当前积分或金币
	private int win;// 自己的输赢
	private int pos;// 位置
	private String uId;
	private String headUrl;
	private String nick;

	public SmallCalRole(int currentScore, int win, String uId, String headUrl, String nick) {
		this.currentScore = currentScore;
		this.win = win;
		this.pos = pos;
		this.uId = uId;
		this.headUrl = headUrl;
		this.nick = nick;
	}

	public int getWin() {
		return win;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(int currentScore) {
		this.currentScore = currentScore;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}
