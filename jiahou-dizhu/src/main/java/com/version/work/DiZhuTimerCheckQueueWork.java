package com.version.work;

import java.util.ArrayList;
import java.util.List;

import com.version.common.entity.Room;
import com.version.common.util.LoggerUtil;
import com.version.common.work.AynWork;
import com.version.data.Card;
import com.version.data.DiZhuGameBase;
import com.version.data.DiZhuRoomStatus;
import com.version.data.vo.CalVO;
import com.version.entity.logic.LogicController;
import com.version.sdk.common.NetContext;
import com.version.service.api.IDiZhuService;
import com.version.util.CardTypes;
import com.version.util.DiZhuUtil;

public class DiZhuTimerCheckQueueWork extends AynWork {
	private static final long serialVersionUID = 511351192010691585L;
	private Room room;

	@Override
	public void init(Object... objs) throws Exception {
		this.room = (Room) objs[0];
	}

	@Override
	public void run() {
		long now = System.currentTimeMillis();
		IDiZhuService diZhuService = NetContext.getInstance(IDiZhuService.class);
		synchronized (room.getRoomLock()) {
			if (room.getGameBase() != null && room.getGameBase().isGaming()) {
				DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
				// 获取当前操作的玩家
				int currentCtrlPos = gameBase.getCurrentCtrlPos();
				String controllerUniqueId = room.getPosEntitys().get(currentCtrlPos).getControllerUniqueId();
				LogicController controller = room.getControllers().get(controllerUniqueId);
				CalVO calVO = gameBase.getPlayerTmpMap().get(controller.getUniqueId()).getCalVO();
				boolean tuoGuan = calVO.isTuoGuanType();
				// 亮牌状态
				long lastTimer = gameBase.getLastTimer();
				try {
					int roomStatus = room.getRoomStatus();
					if (roomStatus == DiZhuRoomStatus.CHOOSE_DIZHU.getRoomStatus()) {
						if (!calVO.isCtrlChooseMutiple()) {
							if (!tuoGuan) {
								if (now - lastTimer >= 1500) {
									diZhuService.reqTuoGuan(room, controller, 2);
									diZhuService.reqChooseMultiple(room, controller, 0);
								}
							} else {
								diZhuService.reqChooseMultiple(room, controller, 0);
							}
						}
					} else if (roomStatus == DiZhuRoomStatus.GAMING.getRoomStatus()) {
						if (!tuoGuan) {
							if (now - lastTimer >= 1500) {
								diZhuService.reqTuoGuan(room, controller, 2);
								// 表示 桌面上没有人打牌
								if (gameBase.getCurrentOutCards().size() == 0) {
									// 检测该玩家 能不能一手 打完 如果能一手打完 则 直接全部打出去
									if (DiZhuUtil.checkcardType(calVO.getCards()) != -1) {
										diZhuService.reqOutCards(room, controller, calVO.getCards());
										return;
									}
									// 当前 自己 应该打牌 随机打一张
									List<Card> randomOutCard = DiZhuUtil.randomOutCard(calVO.getCards());
									diZhuService.reqOutCards(room, controller, randomOutCard);
								} else {
									// 出牌玩家的id
									boolean landlord = gameBase.getPlayerTmpMap().get(gameBase.getCurrentCtrlCardUid())
											.getCalVO().isLandlord();
									List<Card> arrayList = new ArrayList<Card>();
									// 出牌玩家 和 当前操作玩家属于同一阵营 则不接 牌
									int currentOutCardType = gameBase.getCurrentOutCardType();
									if (landlord == calVO.isLandlord()) {
										if (calVO.getCards().size() == 1) {
											if (currentOutCardType == CardTypes.DAN_ZHANG) {
												Card card = calVO.getCards().get(0);
												Card card2 = gameBase.getCurrentOutCards().get(0);
												arrayList.add(card);
												if (card.getNum() > card2.getNum()) {
													diZhuService.reqOutCards(room, controller, arrayList);
												}
											}
										} else {
											diZhuService.reqOutCards(room, controller, arrayList);
										}
									} else {
										if (calVO.getCards().size() == 2 || calVO.getCards().size() == 4) {
											int checkcardType = DiZhuUtil.checkcardType(calVO.getCards());
											if (checkcardType == CardTypes.ZHA_DAN) {
												diZhuService.reqOutCards(room, controller, calVO.getCards());
											} else if (checkcardType == CardTypes.HUO_JIAN) {
												if (currentOutCardType == CardTypes.HUO_JIAN) {
													if (calVO.getCards().get(0).getNum() > gameBase.getCurrentOutCards()
															.get(0).getNum()) {
														diZhuService.reqOutCards(room, controller, calVO.getCards());
													} else {
														diZhuService.reqOutCards(room, controller, new ArrayList<>());
													}
												}
											}
										}
										// 阵营不相同 则 出牌 接牌
										switch (currentOutCardType) {
										case CardTypes.DAN_ZHANG:
											List<Card> randomOutCard = DiZhuUtil.randomOutCard(calVO.getCards(),
													gameBase.getCurrentOutCards().get(0));
											if (randomOutCard == null) {
												diZhuService.reqOutCards(room, controller, new ArrayList<Card>());
											} else {
												diZhuService.reqOutCards(room, controller, randomOutCard);
											}
											break;

										case CardTypes.DUI_ZI:
											List<Card> duiZiOutCard = DiZhuUtil.randomOutCard(calVO.getCards(),
													gameBase.getCurrentOutCards());
											if (duiZiOutCard == null) {
												diZhuService.reqOutCards(room, controller, new ArrayList<Card>());
											} else {
												diZhuService.reqOutCards(room, controller, duiZiOutCard);
											}
											break;
										}
									}
								}
							}
						}
					} else {
						// 表示 桌面上没有人打牌
						if (gameBase.getCurrentOutCards().size() == 0) {
							// 检测该玩家 能不能一手 打完 如果能一手打完 则 直接全部打出去
							if (DiZhuUtil.checkcardType(calVO.getCards()) != -1) {
								diZhuService.reqOutCards(room, controller, calVO.getCards());
								return;
							}
							// 当前 自己 应该打牌 随机打一张
							List<Card> randomOutCard = DiZhuUtil.randomOutCard(calVO.getCards());
							diZhuService.reqOutCards(room, controller, randomOutCard);
						} else {
							// 出牌玩家的id
							boolean landlord = gameBase.getPlayerTmpMap().get(gameBase.getCurrentCtrlCardUid())
									.getCalVO().isLandlord();
							List<Card> arrayList = new ArrayList<Card>();
							// 出牌玩家 和 当前操作玩家属于同一阵营 则不接 牌
							int currentOutCardType = gameBase.getCurrentOutCardType();
							if (landlord == calVO.isLandlord()) {
								if (calVO.getCards().size() == 1) {
									if (currentOutCardType == CardTypes.DAN_ZHANG) {
										Card card = calVO.getCards().get(0);
										Card card2 = gameBase.getCurrentOutCards().get(0);
										arrayList.add(card);
										if (card.getNum() > card2.getNum()) {
											diZhuService.reqOutCards(room, controller, arrayList);
										}
									}
								} else {
									diZhuService.reqOutCards(room, controller, arrayList);
								}
							} else {
								if (calVO.getCards().size() == 2 || calVO.getCards().size() == 4) {
									int checkcardType = DiZhuUtil.checkcardType(calVO.getCards());
									if (checkcardType == CardTypes.ZHA_DAN) {
										diZhuService.reqOutCards(room, controller, calVO.getCards());
									} else if (checkcardType == CardTypes.HUO_JIAN) {
										if (currentOutCardType == CardTypes.HUO_JIAN) {
											if (calVO.getCards().get(0).getNum() > gameBase.getCurrentOutCards().get(0)
													.getNum()) {
												diZhuService.reqOutCards(room, controller, calVO.getCards());
											} else {
												diZhuService.reqOutCards(room, controller, new ArrayList<>());
											}
										}
									}
								}
								// 阵营不相同 则 出牌 接牌
								switch (currentOutCardType) {
								case CardTypes.DAN_ZHANG:
									List<Card> randomOutCard = DiZhuUtil.randomOutCard(calVO.getCards(),
											gameBase.getCurrentOutCards().get(0));
									if (randomOutCard == null) {
										diZhuService.reqOutCards(room, controller, new ArrayList<Card>());
									} else {
										diZhuService.reqOutCards(room, controller, randomOutCard);
									}
									break;

								case CardTypes.DUI_ZI:
									List<Card> duiZiOutCard = DiZhuUtil.randomOutCard(calVO.getCards(),
											gameBase.getCurrentOutCards());
									if (duiZiOutCard == null) {
										diZhuService.reqOutCards(room, controller, new ArrayList<Card>());
									} else {
										diZhuService.reqOutCards(room, controller, duiZiOutCard);
									}
									break;
								}
							}
						}
					}
				} catch (Exception e) {
					LoggerUtil.error(e);
					gameBase.setLastTimer(System.currentTimeMillis());// 防止持续报错
				}

			}

		}
	}
}