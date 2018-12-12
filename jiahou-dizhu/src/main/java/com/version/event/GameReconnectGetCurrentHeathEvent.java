package com.version.event;

import com.version.common.NetCallbackEventListener;
import com.version.common.NetEventType;
import com.version.common.entity.Room;
import com.version.data.cal.BigCalPanel;
import com.version.entity.logic.LogicController;

public class GameReconnectGetCurrentHeathEvent implements
		NetCallbackEventListener<Integer> {

	@Override
	public NetEventType netEventType() {
		return NetEventType.GAME_GET_CURRENT_HEALTH;
	}

	@Override
	public Integer notifyEvent(Object... objs) throws Exception {
		Room room = (Room) objs[0];
		LogicController logicController = (LogicController) objs[1];
		BigCalPanel bigCalPanel = (BigCalPanel) room.getBigPanel();
		int score = logicController.getScore();
		if (bigCalPanel != null) {
			if (bigCalPanel.getScoreMap().containsKey(
					logicController.getUniqueId())) {
				score = bigCalPanel.getScoreMap().get(
						logicController.getUniqueId());
			}
		}
		return score;
	}

	@Override
	public boolean asynchronous() {
		return false;
	}

}
