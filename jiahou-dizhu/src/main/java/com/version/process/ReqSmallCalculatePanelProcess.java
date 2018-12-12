package com.version.process;

import com.google.protobuf.GeneratedMessage.Builder;
import com.version.common.annotation.IProcess;
import com.version.entity.logic.LogicController;
import com.version.process.server.ReqLoginedProcess;
import com.version.protobuf.pb.MsgDiZhu.DiZhuCode;
import com.version.protobuf.pb.MsgDiZhu.ReqSmallCalculatePanel_8884;
import com.version.sdk.common.NetContext;
import com.version.service.api.IDiZhuService;

@IProcess(code = DiZhuCode.REQ_SMALLCALCULATEPANEL_8884_VALUE)
public class ReqSmallCalculatePanelProcess extends ReqLoginedProcess<ReqSmallCalculatePanel_8884> {
	private static final long serialVersionUID = -4298274722287377693L;

	@Override
	public void process(LogicController controller, ReqSmallCalculatePanel_8884 message) throws Exception {
		IDiZhuService service = NetContext.getInstance(IDiZhuService.class);
		String roomId = message.getRoomId();
		int times = message.getTimes();
		// service.reqSmallCalulatePanel(controller, roomId, times);
	}

	@Override
	public Builder<?> build() {
		return ReqSmallCalculatePanel_8884.newBuilder();
	}

}
