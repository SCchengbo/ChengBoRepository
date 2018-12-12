package com.version.service.api;

import java.util.List;

import com.version.common.entity.Room;
import com.version.data.Card;
import com.version.entity.logic.LogicController;

public interface IDiZhuService {

	void someOneReqGameReady(Room room, LogicController logicController) throws Exception;

	void reqOutCards(Room room, LogicController controller, List<Card> outObjectCard) throws Exception;

	void reqChooseMultiple(Room room, LogicController controller, int multiple) throws Exception;

	void reqRecoverGameSceen(Room room, LogicController controller) throws Exception;

	void reqTuoGuan(Room room, LogicController controller, int tuoGuanCtrl) throws Exception;

}
