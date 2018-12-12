package com.version.process;

import java.util.List;

import com.google.protobuf.GeneratedMessage.Builder;
import com.version.common.annotation.IProcess;
import com.version.common.entity.Room;
import com.version.data.Card;
import com.version.entity.logic.LogicController;
import com.version.process.server.ReqRoomProcess;
import com.version.protobuf.pb.MsgDiZhu.CardInfo_8884;
import com.version.protobuf.pb.MsgDiZhu.DiZhuCode;
import com.version.protobuf.pb.MsgDiZhu.ReqOutCards_8884;
import com.version.sdk.common.NetContext;
import com.version.service.api.IDiZhuService;
import com.version.util.DiZhuUtil;

@IProcess(code = DiZhuCode.REQ_OUTCARDS_8884_VALUE)
public class ReqOutProcess extends ReqRoomProcess<ReqOutCards_8884> {
	private static final long serialVersionUID = -7907778644338070744L;

	@Override
	public void process(Room room, LogicController controller, ReqOutCards_8884 message) throws Exception {
		IDiZhuService diService = NetContext.getInstance(IDiZhuService.class);
		List<CardInfo_8884> cardsList = message.getCardsList();
		List<Card> outObjectCard = DiZhuUtil.getOutObjectCard(cardsList);
		diService.reqOutCards(room, controller, outObjectCard);
	}

	@Override
	public Builder<?> build() {
		// TODO Auto-generated method stub
		return ReqOutCards_8884.newBuilder();
	}

}
