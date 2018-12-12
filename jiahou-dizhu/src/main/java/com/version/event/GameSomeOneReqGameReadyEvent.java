package com.version.event;

import com.version.common.NetEventListener;
import com.version.common.NetEventType;
import com.version.common.entity.Room;
import com.version.common.util.LoggerUtil;
import com.version.entity.logic.LogicController;
import com.version.sdk.common.NetContext;
import com.version.service.api.IDiZhuService;

/**
 * 房间内每个玩家准备后触发该事件
 *
 * @author sunhua
 * @version 1.0版本<br>
 * @ProjectName:com-version-xuechan-dizhu
 * @PackageName:com.version.event
 * @ClassName:XuechanGameRoomBeginEvent
 * @Date:2017年7月21日 下午2:50:16
 */
public class GameSomeOneReqGameReadyEvent implements NetEventListener {

	@Override
	public NetEventType netEventType() {
		return NetEventType.GAME_SOMEONE_REQ_GAMEREADY;
	}

	@Override
	public void notifyEvent(Object... objs) throws Exception {
		Room room = (Room) objs[0];
		LogicController logicController = (LogicController) objs[1];
		Boolean ready = (Boolean) objs[2];
		IDiZhuService douniuService = NetContext.getInstance(IDiZhuService.class);

		if (ready) {
			LoggerUtil.info("某人请求准备了");
			douniuService.someOneReqGameReady(room, logicController);
		} else {
			LoggerUtil.info("某人取消准备了");
		}
	}

	@Override
	public boolean asynchronous() {
		return false;
	}

}
