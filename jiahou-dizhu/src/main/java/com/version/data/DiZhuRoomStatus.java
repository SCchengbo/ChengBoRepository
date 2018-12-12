package com.version.data;

import java.io.Serializable;

public enum DiZhuRoomStatus implements Serializable {
	READY(0), CHOOSE_DIZHU(10), GAMING(11);
	private int roomStatus;

	private DiZhuRoomStatus(int roomStatus) {
		this.roomStatus = roomStatus;
	}

	public int getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(int roomStatus) {
		this.roomStatus = roomStatus;
	}
}
