package com.version.process;

import com.google.protobuf.GeneratedMessage.Builder;
import com.version.common.annotation.IProcess;
import com.version.common.entity.Room;
import com.version.entity.logic.LogicController;
import com.version.process.server.ReqRoomProcess;
import com.version.protobuf.pb.MsgDiZhu.DiZhuCode;
import com.version.protobuf.pb.MsgDiZhu.ReqChooseMultiple_8884;
import com.version.protobuf.pb.MsgDiZhu.ReqOutCards_8884;
import com.version.sdk.common.NetContext;
import com.version.service.api.IDiZhuService;

@IProcess(code = DiZhuCode.REQ_CHOOSE_MULTIPLE_8884_VALUE)
public class ReqChooseMultipleProcess extends ReqRoomProcess<ReqChooseMultiple_8884> {
	private static final long serialVersionUID = -7907778644338070744L;

	@Override
	public void process(Room room, LogicController controller, ReqChooseMultiple_8884 message) throws Exception {
		IDiZhuService diService = NetContext.getInstance(IDiZhuService.class);
		int multiple = message.getMultiple();
		diService.reqChooseMultiple(room, controller, multiple);
	}

	@Override
	public Builder<?> build() {
		// TODO Auto-generated method stub
		return ReqChooseMultiple_8884.newBuilder();
	}
}
