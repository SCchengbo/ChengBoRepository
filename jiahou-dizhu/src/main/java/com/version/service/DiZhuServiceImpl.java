package com.version.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.version.NetSdkApi;
import com.version.common.entity.Room;
import com.version.common.util.LoggerUtil;
import com.version.common.util.RandomUtil;
import com.version.data.Card;
import com.version.data.DiZhuGameBase;
import com.version.data.DiZhuRoomStatus;
import com.version.data.cal.SmallCalPanel;
import com.version.data.cal.SmallCalRole;
import com.version.data.vo.CalVO;
import com.version.data.vo.PlayerTmpVO;
import com.version.entity.enums.AType;
import com.version.entity.enums.RoomType;
import com.version.entity.log.HistoryLog;
import com.version.entity.log.PersonScore;
import com.version.entity.logic.LogicController;
import com.version.protobuf.pb.MsgCode.ResponseResult;
import com.version.protobuf.pb.MsgDiZhu.CardInfo_8884;
import com.version.protobuf.pb.MsgDiZhu.DiZhuCode;
import com.version.protobuf.pb.MsgDiZhu.DiZhuRoomInfo_8884;
import com.version.protobuf.pb.MsgDiZhu.OutCardInfo_8884;
import com.version.protobuf.pb.MsgDiZhu.ResChooseMultiple_8884;
import com.version.protobuf.pb.MsgDiZhu.ResFaPai_8884;
import com.version.protobuf.pb.MsgDiZhu.ResNotifyChooseMultiple_8884;
import com.version.protobuf.pb.MsgDiZhu.ResNotifyCurrentCtrlPos_8884;
import com.version.protobuf.pb.MsgDiZhu.ResNotifyGameStart_8884;
import com.version.protobuf.pb.MsgDiZhu.ResNotifyLandlord_8884;
import com.version.protobuf.pb.MsgDiZhu.ResNotifyOutCards_8884;
import com.version.protobuf.pb.MsgDiZhu.ResNotifyTuoGuan_8884;
import com.version.protobuf.pb.MsgDiZhu.ResOutCards_8884;
import com.version.protobuf.pb.MsgDiZhu.ResRecoverGameSceen_8884;
import com.version.protobuf.pb.MsgDiZhu.ResSmallCalculatePanel_8884;
import com.version.protobuf.pb.MsgDiZhu.ResTuoGuan_8884;
import com.version.protobuf.pb.MsgDiZhu.SmallCalController_8884;
import com.version.protobuf.pb.MsgDiZhu.SmallCalculatePanel_8884;
import com.version.protobuf.pb.MsgDiZhu.TuoGuan_8884;
import com.version.protobuf.pb.MsgDiZhu.players_8884;
import com.version.service.api.IDiZhuService;
import com.version.service.api.ILogicService;
import com.version.util.CardTypes;
import com.version.util.DiZhuCardType;
import com.version.util.DiZhuUtil;

@Service
public class DiZhuServiceImpl implements IDiZhuService {
	@Autowired
	private ILogicService logicService;
	@Value("${daishan.gameid}")
	private int gameId;

	@Override
	public void someOneReqGameReady(Room room, LogicController logicController) throws Exception {
		// 全部玩家都已經準備完成
		if (room.getRoomStatus() == DiZhuRoomStatus.READY.getRoomStatus()) {
			if (room.getControllers().size() >= 3 && isAllReady(room)) {
				gameStart(room);
			}
		}
	}

	/**
	 * 游戏开始
	 * 
	 * @param room
	 */
	private void gameStart(Room room) {
		try {
			logicService.updateRoomStatusToBegin(room);
			room.setTimes(room.getTimes() + 1);
			ResNotifyGameStart_8884.Builder gameStartBuilder = ResNotifyGameStart_8884.newBuilder();
			DiZhuGameBase gameBase = new DiZhuGameBase(room);
			room.setGameBase(gameBase);
			gameStartBuilder.setDiZhuRoomInfo(packageGameRoom(room));
			NetSdkApi.broadRoom(room, DiZhuCode.RES_NOTIFY_GAMESTART_8884_VALUE, gameStartBuilder);
			room.setRoomStatus(DiZhuRoomStatus.CHOOSE_DIZHU.getRoomStatus());
			ResNotifyFaiPai(room);
			// 随机生成第一个抢庄的位置
			int pos = RandomUtil.randInt(3) + 1;
			ResNotifyCurrentCtrl(room, pos, CardTypes.CHOOSE_MULTIPLE);
		} catch (Exception e) {
			LoggerUtil.error(e);
		}
	}

	private void ResNotifyCurrentCtrl(Room room, int pos, int checkOutCard) throws Exception {
		DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
		gameBase.setCurrentCtrlPos(pos);
		// 发送当前的操作位置
		ResNotifyCurrentCtrlPos_8884.Builder builder = ResNotifyCurrentCtrlPos_8884.newBuilder();
		builder.setCheckOutCard(checkOutCard);
		builder.setCtrlTime(5);
		builder.setCurrentCtrlPos(pos);
		NetSdkApi.broadRoom(room, DiZhuCode.RES_NOTIFY_CURRENT_CTRLPOS_8884_VALUE, builder);
		LoggerUtil.info("当前的操作位置:{}", pos);

	}

	private ResNotifyCurrentCtrlPos_8884.Builder packageResNotifyCurrentCtrlPos(int pos, Room room) {
		ResNotifyCurrentCtrlPos_8884.Builder builder = ResNotifyCurrentCtrlPos_8884.newBuilder();
		builder.setCheckOutCard(room.getRoomStatus());
		builder.setCtrlTime(5);
		builder.setCurrentCtrlPos(pos);
		return builder;

	}

	private void ResNotifyFaiPai(Room room) throws Exception {
		DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
		Map<String, PlayerTmpVO> playerTmpMap = gameBase.getPlayerTmpMap();
		for (LogicController c : room.getControllers().values()) {
			CalVO calVO = playerTmpMap.get(c.getUniqueId()).getCalVO();
			ResFaPai_8884.Builder builder = ResFaPai_8884.newBuilder();
			builder.setLastNum(3);
			builder.setUid(c.getUniqueId());
			for (Card card : calVO.getCards()) {
				builder.addCard(packageCard(card));
			}
			NetSdkApi.sendMsg(c, DiZhuCode.RES_FAPAI_8884_VALUE, builder);
			builder.clear();
			NetSdkApi.broadRoomToWatcherList(room, DiZhuCode.RES_FAPAI_8884_VALUE, builder);
			NetSdkApi.broadRoomToWatcherListHasPos(room, DiZhuCode.RES_FAPAI_8884_VALUE, builder);
		}
	}

	private CardInfo_8884.Builder packageCard(Card card) {
		CardInfo_8884.Builder newBuilder = CardInfo_8884.newBuilder();
		newBuilder.setCardColor(card.getColor());
		newBuilder.setCardNum(card.getNum());
		newBuilder.setCardId(card.getCardId());
		return newBuilder;
	}

	private DiZhuRoomInfo_8884.Builder packageGameRoom(Room room) throws Exception {
		DiZhuRoomInfo_8884.Builder newBuilder = DiZhuRoomInfo_8884.newBuilder();
		newBuilder.setRoomInfo(logicService.packageRoomInfo(room));
		return newBuilder;
	}

	/**
	 * 检测所有玩家是不是都已经准备完成
	 * 
	 * @param room
	 * @return
	 */
	public boolean isAllReady(Room room) {
		Map<String, LogicController> controllers = room.getControllers();
		for (LogicController logicController : controllers.values()) {
			if (!room.getReadyMap().containsKey(logicController.getUniqueId())) {
				return false;
			}
			if (!room.getReadyMap().get(logicController.getUniqueId())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void reqChooseMultiple(Room room, LogicController controller, int multiple) throws Exception {
		// 0 不抢 1 -3 倍 如果直接抢3倍 则当前玩家是地主
		DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
		CalVO calVO = gameBase.getPlayerTmpMap().get(controller.getUniqueId()).getCalVO();
		if (calVO.isCtrlChooseMutiple()) {
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_CHOOSE_MULTIPLE_8884_VALUE, ResChooseMultiple_8884.newBuilder()
					.setResult(ResponseResult.newBuilder().setCode(404).setSuccess(false).setMsg("您已经抢过地主，不能重复抢地主")));
			return;
		}
		if (room.getRoomStatus() != DiZhuRoomStatus.CHOOSE_DIZHU.getRoomStatus()) {
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_CHOOSE_MULTIPLE_8884_VALUE, ResChooseMultiple_8884.newBuilder()
					.setResult(ResponseResult.newBuilder().setCode(404).setSuccess(false).setMsg("不在抢庄阶段")));
			return;
		}
		// 检测 叫分合理性
		boolean checkChooseBetValidity = DiZhuUtil.checkChooseBetValidity(multiple);
		if (multiple > 0 && multiple < gameBase.getCurrentChooseMultiple()) {
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_CHOOSE_MULTIPLE_8884_VALUE, ResChooseMultiple_8884.newBuilder()
					.setResult(ResponseResult.newBuilder().setCode(404).setSuccess(false).setMsg("叫分倍数不合理")));
			return;
		}
		if (!checkChooseBetValidity) {
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_CHOOSE_MULTIPLE_8884_VALUE, ResChooseMultiple_8884.newBuilder()
					.setResult(ResponseResult.newBuilder().setCode(404).setSuccess(false).setMsg("叫分倍数不合理")));
			return;
		}
		if (multiple > 0 && multiple <= gameBase.getCurrentChooseMultiple()) {
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_CHOOSE_MULTIPLE_8884_VALUE, ResChooseMultiple_8884.newBuilder()
					.setResult(ResponseResult.newBuilder().setCode(404).setSuccess(false).setMsg("必须高于其他玩家的叫分倍数")));
			return;
		}
		calVO.setMultiple(multiple);
		calVO.setCtrlChooseMutiple(true);
		NetSdkApi.sendMsg(controller, DiZhuCode.RES_CHOOSE_MULTIPLE_8884_VALUE, ResChooseMultiple_8884.newBuilder()
				.setResult(ResponseResult.newBuilder().setCode(200).setSuccess(true)));
		// 广播 该玩家 抢地主消息
		ResNotifyChooseMultiple_8884.Builder builder = ResNotifyChooseMultiple_8884.newBuilder();
		builder.setMultiple(calVO.getMultiple());
		builder.setUId(calVO.getUniqueId());
		NetSdkApi.broadRoom(room, DiZhuCode.RES_NOTIFY_CHOOSE_MULTIPLE_8884_VALUE, builder);
		// 抢庄人数+1
		gameBase.setQiangZhuangCount(gameBase.getQiangZhuangCount() + 1);
		ResNotifyLandlord_8884.Builder landLordBuilder = ResNotifyLandlord_8884.newBuilder();
		gameBase.getChooseMultiple().add(multiple);
		DiZhuUtil.getListMultiple(gameBase.getChooseMultiple());
		// 从所有的叫分中 选出最大的那个作为当前 叫分的最大值
		gameBase.setCurrentChooseMultiple(gameBase.getChooseMultiple().get(0));
		// 检测当前已经抢庄的分数中有没有已经达到最大 如果有则 直接 当地主 ，不然则从所有选择叫分的玩家生成
		if (gameBase.getQiangZhuangCount() != room.getMaxPlayerNum()) {
			// 检测是不是有玩家已经叫了三分
			for (LogicController logicController : room.getControllers().values()) {
				CalVO checkVo = gameBase.getPlayerTmpMap().get(logicController.getUniqueId()).getCalVO();
				// 如果有玩家已经抢庄三分 则 该玩家直接是地主
				if (checkVo.getMultiple() == 3) {
					landLordBuilder.setLandLordId(checkVo.getUniqueId());
					LogicController landLordC = room.getControllers().get(checkVo.getUniqueId());
					List<Card> cards = gameBase.getLastCards();
					for (Card card : cards) {
						landLordBuilder.addCard(packageCard(card));
					}
					room.setRoomStatus(DiZhuRoomStatus.GAMING.getRoomStatus());
					gameBase.setLandlordId(checkVo.getUniqueId());
					checkVo.setLandlord(true);
					NetSdkApi.broadRoom(room, DiZhuCode.RES_NOTIFY_LANDLORD_8884_VALUE, landLordBuilder);
					checkVo.getCards().addAll(gameBase.getLastCards());
					// 通知当前玩家操作
					ResNotifyCurrentCtrl(room, room.findSelfPos(landLordC), 2);
					return;
				}
			}
		} else {
			int count = 0;
			// 如果所有玩家都不抢地主 则 本局结束重新发牌
			for (LogicController logicController : room.getControllers().values()) {
				CalVO checkVo = gameBase.getPlayerTmpMap().get(logicController.getUniqueId()).getCalVO();
				if (checkVo.getMultiple() == 0) {
					count++;
				}
			}
			// 所有玩家都不抢庄 则重新开始游戏
			if (count == room.getMaxPlayerNum()) {
				gameStart(room);
				return;
			}
			List<CalVO> vos = new ArrayList<>();
			for (LogicController logicController : room.getControllers().values()) {
				CalVO checkVo = gameBase.getPlayerTmpMap().get(logicController.getUniqueId()).getCalVO();
				vos.add(checkVo);
			}
			CalVO maxMultipleVo = DiZhuUtil.getMaxMultiple(vos);
			// 从三个玩家中 选择 抢庄倍数最大的玩家当做地主
			landLordBuilder.setLandLordId(maxMultipleVo.getUniqueId());
			List<Card> cards = gameBase.getLastCards();
			for (Card card : cards) {
				landLordBuilder.addCard(packageCard(card));
			}
			room.setRoomStatus(DiZhuRoomStatus.GAMING.getRoomStatus());
			gameBase.setLandlordId(maxMultipleVo.getUniqueId());
			maxMultipleVo.setLandlord(true);
			NetSdkApi.broadRoom(room, DiZhuCode.RES_NOTIFY_LANDLORD_8884_VALUE, landLordBuilder);
			// 将地主牌添加到地主手上
			maxMultipleVo.getCards().addAll(gameBase.getLastCards());
			// 通知当前玩家操作
			ResNotifyCurrentCtrl(room, maxMultipleVo.getPos(), 2);
			return;
		}
		// 通知下一个人叫地主
		changeCtrlPos(room, CardTypes.CHOOSE_MULTIPLE);
	}

	@Override
	public void reqOutCards(Room room, LogicController controller, List<Card> outObjectCard) throws Exception {
		if (room.getRoomStatus() == DiZhuRoomStatus.GAMING.getRoomStatus()) {
			// 打出来的手牌
			// List<Card> outObjectCard = DiZhuUtil.getOutObjectCard(cardsList);
			DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
			CalVO calVO = gameBase.getPlayerTmpMap().get(controller.getUniqueId()).getCalVO();
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE,
					ResOutCards_8884.newBuilder().setResult(ResponseResult.newBuilder().setCode(200).setSuccess(true)));
			ResNotifyOutCards_8884.Builder builder = ResNotifyOutCards_8884.newBuilder();
			if (outObjectCard.size() == 0 && gameBase.getCurrentOutCards().isEmpty()) {
				NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE, ResOutCards_8884.newBuilder()
						.setResult(ResponseResult.newBuilder().setCode(404).setMsg("第一个玩家必须出牌").setSuccess(false)));
				return;
			}
			if (outObjectCard.size() == 0 && !gameBase.getCurrentOutCards().isEmpty()) {
				gameBase.setPassCount(gameBase.getPassCount() + 1);
				if (gameBase.getPassCount() == room.getMaxPlayerNum() - 1) {
					gameBase.getCurrentOutCards().clear();
					gameBase.setCurrentOutCardType(0);
					gameBase.setPassCount(0);
					gameBase.setCurrentCtrlCardUid("");
					builder.setRoundOver(1);
					gameBase.setRoudOver(1);
				}

				builder.setAllMultiple(gameBase.getCardMultiple());
				builder.setLastCardNum(calVO.getCards().size());
				builder.setUId(controller.getUniqueId());
				builder.setOutCardsType(gameBase.getCurrentOutCardType());
				for (Card card : outObjectCard) {
					builder.addOutCards(packageCard(card));
				}
				gameBase.getOutCardMap().put(controller.getUniqueId(), outObjectCard);
				NetSdkApi.broadRoom(room, DiZhuCode.RES_NOTIFY_OUT_CARDS_8884_VALUE, builder);
				// 跳转轮次
				changeCtrlPos(room, CardTypes.OUT_CARD);
				return;
			}
			// 检测出牌合理性
			boolean checKOutCardValidity = DiZhuUtil.checKOutCardValidity(controller, outObjectCard, gameBase);
			if (checKOutCardValidity) {
				gameBase.getOutCardMap().put(controller.getUniqueId(), outObjectCard);
				// 本轮出牌过程
				gameBase.setRoudOver(0);
				if (gameBase.getCurrentOutCards().size() > 0) {
					gameBase.setRoudOver(2);
				}
				gameBase.getPlayerTmpMap().get(controller.getUniqueId()).getCalVO();
				calVO.setOutCardCount(calVO.getOutCardCount() + 1);
				// 移除手牌
				DiZhuUtil.removeOutCards(calVO.getCards(), outObjectCard);
				// 保存当前操作的牌
				gameBase.setCurrentOutCards(outObjectCard);
				gameBase.setCurrentCtrlCardUid(controller.getUniqueId());
				int checkcardType = DiZhuUtil.checkcardType(outObjectCard);
				if (checkcardType == DiZhuCardType.ZHA_DAN.getCardType()
						|| checkcardType == DiZhuCardType.HUO_JIAN.getCardType()) {
					gameBase.setCardMultiple(gameBase.getCardMultiple() * 2);
				}
				// 当前操作的牌型
				gameBase.setCurrentOutCardType(checkcardType);
				NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE, ResOutCards_8884.newBuilder()
						.setResult(ResponseResult.newBuilder().setCode(200).setSuccess(true)));
				builder.setAllMultiple(gameBase.getCardMultiple());
				builder.setLastCardNum(calVO.getCards().size());
				builder.setUId(controller.getUniqueId());
				builder.setOutCardsType(gameBase.getCurrentOutCardType());
				for (Card card : outObjectCard) {
					builder.addOutCards(packageCard(card));
				}
				// 目前 桌面 打出最大牌的玩家的位置
				gameBase.setCurrentMaxCardPos(calVO.getPos());
				// 重新更新 过的次数
				gameBase.setPassCount(0);
				NetSdkApi.broadRoom(room, DiZhuCode.RES_NOTIFY_OUT_CARDS_8884_VALUE, builder);
				// 当前操作的最大的牌的玩家 比如 一个玩家 出王 在没有 炸弹的情况下 没有人要的起
				LoggerUtil.info("当前玩家的剩余牌数量:{}", calVO.getCards().size());
				if (calVO.getCards().size() <= 0) {
					// 游戏结束
					room.setRoomStatus(DiZhuRoomStatus.READY.getRoomStatus());
					gameOver(room);
				} else {
					// 更换位置
					changeCtrlPos(room, CardTypes.OUT_CARD);
				}
			}
		}
	}

	/**
	 * 
	 * @param room
	 * @param winType
	 *            输赢类型:1是 地主赢 2是地主输
	 * @throws Exception
	 */
	public void gameOver(Room room) throws Exception {
		try {
			DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
			// 检测是地主赢还是 地主输 (地主的牌 没有了 则 是 地主赢 )
			CalVO landLordVo = gameBase.getPlayerTmpMap().get(gameBase.getLandlordId()).getCalVO();
			int size = landLordVo.getCards().size();
			int checkChunTian = DiZhuUtil.checkChunTian(room);
			SmallCalPanel smallCalPanel = gameBase.getSmallCalPanel();
			smallCalPanel.setDiFen((int) room.getYazhu());
			smallCalPanel.setLandLordId(gameBase.getLandlordId());
			smallCalPanel.setWinType(checkChunTian);
			smallCalPanel.setPublicMultiple(gameBase.getCurrentChooseMultiple());
			smallCalPanel.setZhaDanMultiple(gameBase.getCardMultiple());
			smallCalPanel.setChunTianMultiple(0);
			if (checkChunTian > 1) {
				gameBase.setCardMultiple(gameBase.getCardMultiple() * 2);
				smallCalPanel.setChunTianMultiple(2);
			}
			smallCalPanel.setCardMultiple(gameBase.getCardMultiple() * gameBase.getCurrentChooseMultiple());
			int score = (int) room.getYazhu() * gameBase.getCardMultiple() * landLordVo.getMultiple();
			// 输赢的最大真实金币
			int maxLoseWinScore = 0;
			// 地主赢的钱
			int landLordWinScore = score * 2;
			// 地主实际金币
			int realityGolod = room.getControllers().get(landLordVo.getUniqueId()).getPlayerData().getCurrentWealth()
					.get(AType.GOLD.getAtype()).intValue();
			// 如果地主赢的钱 比他自己本局携带的金边多 则 用携带金币 为准
			if (landLordWinScore > realityGolod) {

				score = landLordWinScore / 2;
			}
			// 地主赢
			if (size == 0) {
				for (LogicController c : room.getControllers().values()) {
					CalVO calVO = gameBase.getPlayerTmpMap().get(c.getUniqueId()).getCalVO();
					if (calVO.getPos() != landLordVo.getPos()) {
						// 检测当前玩家输的分数 和自己携带的金币数量
						BigDecimal ownGold = c.getPlayerData().getCurrentWealth().get(AType.GOLD.getAtype());
						int currentGold = ownGold.intValue();
						if (currentGold >= score) {
							calVO.setScore(-score);
							maxLoseWinScore += score;
						} else {
							calVO.setScore(-currentGold);
							maxLoseWinScore += currentGold;
						}
						LoggerUtil.info("地主赢的时候农民输的分数:{}", calVO.getScore());
					}
				}
			} else {
				// 地主输
				for (LogicController c : room.getControllers().values()) {
					CalVO calVO = gameBase.getPlayerTmpMap().get(c.getUniqueId()).getCalVO();
					BigDecimal ownGold = c.getPlayerData().getCurrentWealth().get(AType.GOLD.getAtype());
					int currentGold = ownGold.intValue();
					if (calVO.getPos() != landLordVo.getPos()) {
						if (currentGold >= score) {
							calVO.setScore(score);
							maxLoseWinScore += -score;
						} else {
							calVO.setScore(currentGold);
							maxLoseWinScore += -currentGold;
						}
						LoggerUtil.info("地主输的时候农民赢的分数:{}", calVO.getScore());
					}
				}
			}
			landLordVo.setScore(maxLoseWinScore);
			LoggerUtil.info("地主赢的时候地主输赢的分数:{}", landLordVo.getScore());
			for (LogicController c : room.getControllers().values()) {
				CalVO calVO = gameBase.getPlayerTmpMap().get(c.getUniqueId()).getCalVO();
				c.setScore(calVO.getScore());
			}
			// 将临时计分换算成金币变化
			for (LogicController c : room.getControllers().values()) {
				if (room.getRoomType() == RoomType.GOLD_ROOM_VALUE) {
					logicService.addGold(c, c.getScore());
				}
			}
			for (LogicController c : room.getControllers().values()) {
				CalVO calVO = gameBase.getPlayerTmpMap().get(c.getUniqueId()).getCalVO();
				BigDecimal ownGold = c.getPlayerData().getCurrentWealth().get(AType.GOLD.getAtype());
				SmallCalRole role = new SmallCalRole(ownGold.intValue(), calVO.getScore(), calVO.getUniqueId(),
						c.getPlayerData().getHeadUrl(), c.getPlayerData().getNick());
				smallCalPanel.getRoles().put(calVO.getUniqueId(), role);
			}

			logicService.jinbiChoushui(room);
			logicService.updateDataToServer(room.getControllers().values(), room);// 结算货币数据到大厅
			// CODE HERE(包装小结算面板数据到缓存)
			smallCalculatePanel(room, smallCalPanel);
			reqSmallCalulatePanel(room, smallCalPanel);

			logicService.afterSmallCal(room);// 调用框架小结算之后执行的代码
		} catch (Exception e) {
			LoggerUtil.error(e);
			throw e;
		}
	}

	private void smallCalculatePanel(Room room, SmallCalPanel smallCalPanel) throws Exception {
		int times = room.getTimes();
		HistoryLog historyLog = new HistoryLog();
		historyLog.setGameId(gameId);
		historyLog.setPlayerMaxNum(room.getMaxPlayerNum());
		historyLog.setRecordTime(new Date());
		historyLog.setRoomerUniqueId(room.getRoomerUniqueId());
		historyLog.setRoomExstr(room.getJsonConfig());
		historyLog.setRoomId(room.getRoomId());
		historyLog.setRoomName(room.getRoomName());
		historyLog.setTimes(times);
		// 包装小结算数据
		for (LogicController c : room.getControllers().values()) {
			// CalVO calVO = gameBase.getPlayerTmpMap().get(c.getUniqueId()).getCalVO();
			historyLog.getPersonScores().add(new PersonScore(c.getScore(), c.getUniqueId(), c.getPlayerData().getNick(),
					c.getPlayerData().getPin(), c.getPlayerData().getProxyId()));
		}
		// 保存战绩和小结算面板
		logicService.saveSecondHistoryToDB(room, historyLog);
	}

	public void reqSmallCalulatePanel(Room room, SmallCalPanel smallCalPanel) throws Exception {
		try {
			ResSmallCalculatePanel_8884.Builder newBuilder = ResSmallCalculatePanel_8884.newBuilder();
			// 当前请求小结算的玩家
			SmallCalculatePanel_8884.Builder builder = SmallCalculatePanel_8884.newBuilder();
			builder.setCardMultiple(smallCalPanel.getCardMultiple());
			builder.setDiFen(smallCalPanel.getDiFen());
			builder.setLandLordId(smallCalPanel.getLandLordId());
			builder.setWinType(smallCalPanel.getWinType());
			builder.setZhaDanMultiple(smallCalPanel.getZhaDanMultiple());
			builder.setChunTianMultiple(smallCalPanel.getChunTianMultiple());
			builder.setPublicMultiple(smallCalPanel.getPublicMultiple());
			Map<String, SmallCalRole> roles = smallCalPanel.getRoles();
			for (SmallCalRole role : roles.values()) {
				builder.addSmallCalControllers(packageRole(role));
			}
			newBuilder.setSmallCalculatePanel(builder);
			newBuilder.setResult(ResponseResult.newBuilder().setCode(200).setSuccess(true).setMsg("小结算成功"));
			for (LogicController controller : room.getControllers().values()) {
				NetSdkApi.sendMsg(controller, DiZhuCode.RES_SMALLCALCULATEPANEL_8884_VALUE, newBuilder);
			}

		} catch (Exception e) {
			LoggerUtil.error(e);
			throw e;
		}
	}

	private SmallCalController_8884.Builder packageRole(SmallCalRole role) {
		SmallCalController_8884.Builder newBuilder = SmallCalController_8884.newBuilder();
		newBuilder.setAllScore(role.getCurrentScore());
		newBuilder.setNick(role.getNick());
		newBuilder.setHeadImg(role.getHeadImg());
		newBuilder.setWinScore(role.getWin());
		newBuilder.setUniqueId(role.getuId());
		return newBuilder;
	}

	@Override
	public void reqRecoverGameSceen(Room room, LogicController controller) throws Exception {
		try {
			ResRecoverGameSceen_8884.Builder builder = ResRecoverGameSceen_8884.newBuilder();
			// 当前房间状态
			builder.setRoomStatus(room.getRoomStatus());
			builder.setResult(ResponseResult.newBuilder().setCode(200).setSuccess(true).setMsg("获取数据成功"));
			if (room.getRoomStatus() == DiZhuRoomStatus.READY.getRoomStatus()) {
				// 准备状态
				builder.setRoomInfo(logicService.packageRoomInfo(room));
			} else {
				// 当前庄家id
				DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
				if (gameBase != null) {
					CalVO calVO = gameBase.getPlayerTmpMap().get(controller.getUniqueId()).getCalVO();
					builder.setGameId(gameId);
					builder.setRoomInfo((logicService.packageRoomInfo(room)));
					builder.setRoomStatus(room.getRoomStatus());
					builder.setCurrentCtrlPos(packageResNotifyCurrentCtrlPos(gameBase.getCurrentCtrlPos(), room));
					if (room.getRoomStatus() == DiZhuRoomStatus.GAMING.getRoomStatus()) {
						builder.setLandlordId(gameBase.getLandlordId());
						List<Card> currentOutCards = gameBase.getCurrentOutCards();
						List<Card> lastCards = gameBase.getLastCards();
						if (currentOutCards != null) {
							for (Card card : currentOutCards) {
								builder.addCurrentCtrlCard(packageCard(card));
							}
						}
						if (lastCards != null) {
							for (Card card : lastCards) {
								builder.addLastCard((packageCard(card)));
							}
						}
						builder.setRoundOver(gameBase.getRoudOver());
						for (Entry<String, List<Card>> entry : gameBase.getOutCardMap().entrySet()) {
							List<Card> cards = entry.getValue();
							String uId = entry.getKey();
							builder.addOutCardInfo(packageOutCardInfo(uId, cards));
						}
					}
					builder.setAllMultiple(gameBase.getCardMultiple());
					for (LogicController c : room.getControllers().values()) {
						CalVO vo = gameBase.getPlayerTmpMap().get(c.getUniqueId()).getCalVO();
						builder.addPlayersMsg(packageRecoverGame(vo));
						builder.addTuoGuanCtrl(packageTuoGuan(vo));
					}
					for (Card card : calVO.getCards()) {
						builder.addSelfCards(packageSelfCards(card));
					}

				}
			}
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_RECOVER_GAMESCEEN_8884_VALUE, builder);
		} catch (Exception e) {
			LoggerUtil.error(e);
			throw e;
		}
	}

	private OutCardInfo_8884.Builder packageOutCardInfo(String uId, List<Card> cards) {
		OutCardInfo_8884.Builder newBuilder = OutCardInfo_8884.newBuilder();
		newBuilder.setOutCardId(uId);
		for (Card card : cards) {
			com.version.protobuf.pb.MsgDiZhu.CardInfo_8884.Builder cardInfo = CardInfo_8884.newBuilder();
			cardInfo.setCardColor(card.getColor());
			cardInfo.setCardId(card.getCardId());
			cardInfo.setCardNum(card.getNum());
			newBuilder.addCardInfo(cardInfo);
		}
		return newBuilder;
	}

	private TuoGuan_8884.Builder packageTuoGuan(CalVO vo) {
		TuoGuan_8884.Builder newBuilder = TuoGuan_8884.newBuilder();
		newBuilder.setTuoGuanCtrl(vo.isTuoGuanType() ? 2 : 1);
		newBuilder.setUId(vo.getUniqueId());

		return newBuilder;
	}

	private CardInfo_8884.Builder packageSelfCards(Card card) {
		CardInfo_8884.Builder newBuilder = CardInfo_8884.newBuilder();
		newBuilder.setCardColor(card.getColor());
		newBuilder.setCardId(card.getCardId());
		newBuilder.setCardNum(card.getNum());
		return newBuilder;
	}

	private players_8884.Builder packageRecoverGame(CalVO vo) {
		players_8884.Builder newBuilder = players_8884.newBuilder();
		newBuilder.setLastCardNum(vo.getCards().size());
		newBuilder.setAllScore(vo.getScore());
		newBuilder.setUId(vo.getUniqueId());
		return newBuilder;
	}

	/**
	 * 
	 * @param room
	 * @param ctrlType
	 *            1叫地主 2 打牌
	 * @throws Exception
	 */
	public void changeCtrlPos(Room room, int ctrlType) throws Exception {
		DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
		// 通知下一个人叫地主
		int ctrlPos = DiZhuUtil.getCtrlPos(room, gameBase.getCurrentCtrlPos());
		ResNotifyCurrentCtrl(room, ctrlPos, ctrlType);
	}

	@Override
	public void reqTuoGuan(Room room, LogicController controller, int tuoGuanCtrl) throws Exception {
		// 1表示 托管 2表示 取消托管
		DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
		CalVO calVO = gameBase.getPlayerTmpMap().get(controller.getUniqueId()).getCalVO();
		boolean tuoGuanType = calVO.isTuoGuanType();
		if (tuoGuanType) {
			calVO.setTuoGuanType(false);
		} else {
			calVO.setTuoGuanType(true);
		}
		tuoGuanCtrl = calVO.isTuoGuanType() ? 2 : 1;
		NetSdkApi.sendMsg(controller, DiZhuCode.RES_TUOGUAN_8884_VALUE, ResTuoGuan_8884.newBuilder()
				.setResult(ResponseResult.newBuilder().setCode(200).setMsg("托管成功").setSuccess(true)));
		ResNotifyTuoGuan_8884.Builder newBuilder = ResNotifyTuoGuan_8884.newBuilder();
		newBuilder.setTuoGuanCtrl(tuoGuanCtrl);
		newBuilder.setTuoGuanId(controller.getUniqueId());
		NetSdkApi.broadRoom(room, DiZhuCode.RES_NOTIFY_TUOGUAN_8884_VALUE, newBuilder);
	}

}
