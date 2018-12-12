package com.version.timer;

import com.version.common.auto.AutoAynStart;
import com.version.common.work.TimeWork;
import com.version.common.work.TimerCenter;

public class DiZhuTimerCheckWork extends AutoAynStart {
	private static final long serialVersionUID = 6227825500059603911L;

	@Override
	public long interval() {
		return TimerCenter.getCenter().getInterval(DiZhuTimerCheck.class);
	}

	@Override
	public Class<? extends TimeWork> getClz() {
		return DiZhuTimerCheck.class;
	}

}
