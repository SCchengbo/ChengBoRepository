package com.version.event;

import com.alibaba.fastjson.JSONObject;
import com.version.common.NetEventListener;
import com.version.common.NetEventType;
import com.version.common.entity.Room;
import com.version.common.util.LoggerUtil;
import com.version.data.DiZhuGameBase;
import com.version.data.ExtendCache;
import com.version.entity.logic.LogicController;

/**
 * 创建房间后触发事件
 *
 * @author sunhua
 * @version 1.0版本<br>
 * @ProjectName:com-version-xuechan-dizhu
 * @PackageName:com.version.event
 * @ClassName:XuechanSomeOneCreateRoomEvent
 * @Date:2017年7月21日 下午2:53:03
 */
public class GameSomeOneCreateRoomEvent implements NetEventListener {
	@Override
	public NetEventType netEventType() {
		return NetEventType.GAME_SOMEONE_CREATE_ROOM;
	}

	@Override
	public void notifyEvent(Object... objs) {
		Room room = (Room) objs[0];
		int maxNum = room.getAiRoomData().getMaxNum();
		LogicController logicController = (LogicController) objs[1];
		LoggerUtil.info("某人创建了房间", JSONObject.toJSONString(room));
		room.setExtendCache(new ExtendCache());
	}

	@Override
	public boolean asynchronous() {
		return false;
	}
}
