package com.version.event;

import com.version.common.NetCallbackEventListener;
import com.version.common.NetEventType;

/**
 * 创建游戏房间前触发事件
 * 
 * @author sunhua
 * @version 1.0版本<br>
 * @ProjectName:com-version-xuechan-dizhu
 * @PackageName:com.version.event
 * @ClassName:XuechanBeforeCreateRoomEvent
 * @Date:2017年7月21日 下午2:48:19
 */
public class GameBeforeCreateRoomEvent implements
		NetCallbackEventListener<Boolean> {
	@Override
	public NetEventType netEventType() {
		return NetEventType.GAME_BEFORE_CREATE_ROOM;
	}

	@Override
	public boolean asynchronous() {
		return false;
	}

	@Override
	public Boolean notifyEvent(Object... objs) throws Exception {
		// LogicController logicController = (LogicController) objs[0];
		// Integer roomType = (Integer) objs[1];
		// Integer timesType = (Integer) objs[2];
		// String jsonParam = (String) objs[3];
		// Integer payType = (Integer) objs[4];
		// Integer halfWayJoin = (Integer) objs[5];
		// Integer maxPlayerNum = (Integer) objs[6];
		// TimesConfig timesConfig = (TimesConfig) objs[7];
		// Integer totalTimes = (Integer) objs[8];
		// Integer minNeed = (Integer) objs[9];
		// IDouniuService douniuService = NetContext
		// .getInstance(IDouniuService.class);
		// return douniuService.beforeCreateRoomCheck(logicController, roomType,
		// timesType, jsonParam, payType, halfWayJoin, maxPlayerNum,
		// timesConfig, totalTimes, minNeed);
		return true;
	}
}
