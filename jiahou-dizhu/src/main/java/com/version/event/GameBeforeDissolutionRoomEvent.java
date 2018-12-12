package com.version.event;

import com.version.common.NetEventListener;
import com.version.common.NetEventType;

/**
 * 房间销毁后触发事件
 *
 * @author sunhua
 * @version 1.0版本<br>
 * @ProjectName:com-version-xuechan-dizhu
 * @PackageName:com.version.event
 * @ClassName:XuechanDissolutionGameRoom
 * @Date:2017年7月21日 下午2:49:46
 */
public class GameBeforeDissolutionRoomEvent implements NetEventListener {
	@Override
	public NetEventType netEventType() {
		return NetEventType.GAME_BEFORE_DISSOLUTION_ROOM;
	}

	@Override
	public void notifyEvent(Object... objs) throws Exception {
		// Room room = (Room) objs[0];
		// if (room.getTimes() != 0
		// && room.getRoomType() == RoomType.CARD_ROOM_VALUE) {
		// IDouniuService douniuService = NetContext
		// .getInstance(IDouniuService.class);
		// douniuService.bigCalculatePanel(room);
		// }

	}

	@Override
	public boolean asynchronous() {
		return false;
	}
}
