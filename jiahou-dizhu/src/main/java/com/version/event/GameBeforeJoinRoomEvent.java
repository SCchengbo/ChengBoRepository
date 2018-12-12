package com.version.event;

import com.version.common.NetCallbackEventListener;
import com.version.common.NetEventType;

/**
 * 加入游戏房间前触发事件
 *
 * @author sunhua
 * @version 1.0版本<br>
 * @ProjectName:com-version-xuechan-dizhu
 * @PackageName:com.version.event
 * @ClassName:XuechanBeforeJoinRoomEvent
 * @Date:2017年7月21日 下午2:49:37
 */
public class GameBeforeJoinRoomEvent implements
		NetCallbackEventListener<Boolean> {
	@Override
	public NetEventType netEventType() {
		return NetEventType.GAME_BEFORE_JOIN_ROOM;
	}

	@Override
	public Boolean notifyEvent(Object... objs) throws Exception {
		// LogicController logicController = (LogicController) objs[0];
		// Room room = (Room) objs[1];
		// TimesConfig timesConfig = (TimesConfig) objs[2];
		// IDouniuService douniuService = NetContext
		// .getInstance(IDouniuService.class);
		// return douniuService.beforeJoinRoomCheck(logicController, room,
		// timesConfig);
		return true;
	}

	@Override
	public boolean asynchronous() {
		return false;
	}
}
