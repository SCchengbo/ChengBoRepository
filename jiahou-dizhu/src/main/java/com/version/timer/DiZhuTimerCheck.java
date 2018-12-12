package com.version.timer;

import java.util.Map;
import java.util.Map.Entry;

import org.quartz.JobDataMap;

import com.version.NetSdkApi;
import com.version.common.annotation.IInterval;
import com.version.common.entity.Room;
import com.version.common.work.TimeWork;
import com.version.entity.enums.RoomType;

@IInterval(interval = 2000)
public class DiZhuTimerCheck extends TimeWork {
	private static final long serialVersionUID = -6523968620589340794L;

	@Override
	public void execute(JobDataMap paramJobDataMap) throws Exception {
		// LoggerUtil.info(""+now);
		Map<String, Room> allRooms = NetSdkApi.getAllGameRoom();
		for (Entry<String, Room> entry : allRooms.entrySet()) {
			Room room = entry.getValue();
			if (room.getRoomType() == RoomType.CARD_ROOM_VALUE) {
				// 房卡模式的直接跳过
				// continue;
			}
			// WorkManager.getManager().submit(DiZhuTimerCheckQueueWork.class, room);

		}
	}
}
