package com.version.process;

import com.google.protobuf.GeneratedMessage.Builder;
import com.version.common.annotation.IProcess;
import com.version.common.entity.Room;
import com.version.entity.logic.LogicController;
import com.version.process.server.ReqRoomProcess;
import com.version.protobuf.pb.MsgDiZhu.DiZhuCode;
import com.version.protobuf.pb.MsgDiZhu.ReqTuoGuan_8884;
import com.version.sdk.common.NetContext;
import com.version.service.api.IDiZhuService;

@IProcess(code = DiZhuCode.REQ_TUOGUAN_8884_VALUE)
public class ReqTuoGuanProcess extends ReqRoomProcess<ReqTuoGuan_8884> {
	private static final long serialVersionUID = -7907778644338070744L;

	@Override
	public Builder<?> build() {
		return ReqTuoGuan_8884.newBuilder();
	}

	@Override
	public void process(Room room, LogicController controller, ReqTuoGuan_8884 message) throws Exception {
		IDiZhuService service = NetContext.getInstance(IDiZhuService.class);
		// 1表示 托管 2表示 取消托管
		int tuoGuanCtrl = message.getTuoGuanCtrl();
		service.reqTuoGuan(room, controller, tuoGuanCtrl);
	}

}
