package com.version.event;

import com.version.common.NetEventListener;
import com.version.common.NetEventType;
import com.version.common.entity.Room;
import com.version.entity.logic.LogicController;
import com.version.sdk.common.NetContext;
import com.version.service.api.IDiZhuService;

/**
 * 房间内每个玩家同意解散房间触发该事件
 *
 * @author sunhua
 * @version 1.0版本<br>
 * @ProjectName:com-version-xuechan-dizhu
 * @PackageName:com.version.event
 * @ClassName:GameSomeOneAgreeDissolutionCardRoomEvent
 * @Date:2017年7月21日 下午2:50:16
 */
public class GameSomeOneAgreeDissolutionCardRoomEvent implements NetEventListener {

	@Override
	public NetEventType netEventType() {
		return NetEventType.GAME_SOMEONE_AGREE_DISSOLUTION_CARD_ROOM;
	}

	@Override
	public void notifyEvent(Object... objs) throws Exception {
		Room room = (Room) objs[0];
		LogicController logicController = (LogicController) objs[1];
		boolean agree = (boolean) objs[2];
		IDiZhuService douniuService = NetContext.getInstance(IDiZhuService.class);
	}

	@Override
	public boolean asynchronous() {
		return false;
	}

}
