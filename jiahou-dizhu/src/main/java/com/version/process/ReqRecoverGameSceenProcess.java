package com.version.process;

import com.google.protobuf.GeneratedMessage.Builder;
import com.version.common.annotation.IProcess;
import com.version.common.entity.Room;
import com.version.entity.logic.LogicController;
import com.version.process.server.ReqRoomProcess;
import com.version.protobuf.pb.MsgDiZhu.DiZhuCode;
import com.version.protobuf.pb.MsgDiZhu.ReqRecoverGameSceen_8884;
import com.version.sdk.common.NetContext;
import com.version.service.api.IDiZhuService;

@IProcess(code = DiZhuCode.REQ_RECOVER_GAMESCEEN_8884_VALUE)
public class ReqRecoverGameSceenProcess extends ReqRoomProcess<ReqRecoverGameSceen_8884> {
	private static final long serialVersionUID = 2744267369477696274L;

	@Override
	public Builder<?> build() {
		return ReqRecoverGameSceen_8884.newBuilder();
	}

	@Override
	public void process(Room room, LogicController controller, ReqRecoverGameSceen_8884 message) throws Exception {
		IDiZhuService service = NetContext.getInstance(IDiZhuService.class);
		service.reqRecoverGameSceen(room, controller);

	}

}
