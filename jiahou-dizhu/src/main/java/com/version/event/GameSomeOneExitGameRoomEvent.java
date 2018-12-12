package com.version.event;

import com.version.common.NetEventListener;
import com.version.common.NetEventType;
import com.version.common.entity.Controller;
import com.version.common.entity.Room;
import com.version.sdk.common.NetContext;
import com.version.service.api.IDiZhuService;

/**
 * 正常退出房间触发事件
 *
 * @author sunhua
 * @version 1.0版本<br>
 * @ProjectName:com-version-xuechan-dizhu
 * @PackageName:com.version.event
 * @ClassName:XuechanSomeOneExitRoomEvent
 * @Date:2017年7月21日 下午2:53:29
 */
public class GameSomeOneExitGameRoomEvent implements NetEventListener {
	@Override
	public NetEventType netEventType() {
		return NetEventType.GAME_SOMEONE_EXIT_ROOM;
	}

	@Override
	public void notifyEvent(Object... objs) throws Exception {
		Controller exitor = (Controller) objs[0];
		Room room = (Room) objs[1];
		// 某人正常退出房间之后,退出消息框架已发出,这里主要做资源回收操作
		IDiZhuService douniuService = NetContext.getInstance(IDiZhuService.class);
	}

	@Override
	public boolean asynchronous() {
		return false;
	}
}
