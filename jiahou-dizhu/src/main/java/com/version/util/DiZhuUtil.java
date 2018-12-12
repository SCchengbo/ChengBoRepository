
package com.version.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.aspectj.weaver.ast.Literal;

import com.version.NetSdkApi;
import com.version.common.entity.Room;
import com.version.common.util.LoggerUtil;
import com.version.data.Card;
import com.version.data.DiZhuGameBase;
import com.version.data.vo.CalVO;
import com.version.entity.logic.LogicController;
import com.version.protobuf.pb.MsgCode.ResponseResult;
import com.version.protobuf.pb.MsgDiZhu.CardInfo_8884;
import com.version.protobuf.pb.MsgDiZhu.DiZhuCode;
import com.version.protobuf.pb.MsgDiZhu.ResOutCards_8884;

/**
 * @author 作者:cb
 * @version 创建时间：2018年11月7日 下午4:48:39
 * 
 */
public class DiZhuUtil {
	// 创建手牌
	public static List<Card> getCreatCards() {
		List<Card> cards = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			for (int j = 2; j <= 14; j++) {
				Card card = new Card();
				card.setNum(j);
				card.setColor(i);
				card.setCardId(i * 100 + j);
				cards.add(card);
			}
		}
		for (int i = 16; i <= 17; i++) {
			Card card = new Card();
			card.setNum(i);
			card.setColor(5);
			card.setCardId(5 * 100 + i);
			cards.add(card);
		}
		Collections.shuffle(cards);
		return cards;
	}

	/**
	 * 检测当前玩家打出来的牌型
	 * 
	 * @param cards
	 * @return
	 */
	public static int checkcardType(List<Card> cards) {
		List<Integer> changeCard = changeCardType(cards);
		if (changeCard.size() == 1) {
			return DiZhuCardType.DAN_ZHANG.getCardType();
		}
		// 2张牌的时候 检测是不是对子 或者对王
		else if (changeCard.size() == 2) {
			boolean checkWangZha = checkWangZha(changeCard);
			if (checkWangZha) {
				return DiZhuCardType.HUO_JIAN.getCardType();
			}
			boolean checkDuiZi = checkEqualityCard(changeCard);
			if (checkDuiZi) {
				return DiZhuCardType.DUI_ZI.getCardType();

			}
		} else if (changeCard.size() == 3) {
			boolean checkFeiJi = checkEqualityCard(changeCard);
			if (checkFeiJi) {
				return DiZhuCardType.SAN_BU_DAI.getCardType();
			}
		} else if (changeCard.size() == 4) {
			boolean checkZhaDan = checkEqualityCard(changeCard);
			if (checkZhaDan) {
				return DiZhuCardType.ZHA_DAN.getCardType();
			}
			boolean checkSanDaiYi = checkSanDaiYi(changeCard);
			if (checkSanDaiYi) {
				return DiZhuCardType.SAN_DAI_YI.getCardType();
			}
		} else if (changeCard.size() == 5) {
			// 检测 是不是连子或者 是不是 三带二
			boolean checkSanDaiEr = checkSanDaiEr(changeCard);
			if (checkSanDaiEr) {
				return DiZhuCardType.SAN_DAI_ER.getCardType();
			}
			boolean checkShunZi = checkShunZi(changeCard);
			if (checkShunZi) {
				return DiZhuCardType.SHUN_ZI.getCardType();
			}
		} else {
			// 检测四代二
			if (changeCard.size() == 6) {
				boolean checkSiDaiEr = checkSiDaiEr(changeCard);
				if (checkSiDaiEr) {
					return DiZhuCardType.SI_DAI_ER.getCardType();
				}
			}
			// 检测顺子
			boolean checkShunZi = checkShunZi(changeCard);
			if (checkShunZi) {
				return DiZhuCardType.SHUN_ZI.getCardType();
			}
			boolean checkLianDui = checkLianDui(changeCard);
			if (checkLianDui) {
				return DiZhuCardType.LIAN_DUI.getCardType();
			}
			boolean checkFeiJi = checkFeiJi(changeCard);
			if (checkFeiJi) {
				return DiZhuCardType.FEI_JI.getCardType();
			}
		}
		return -1;
	}

	private static boolean checkSiDaiEr(List<Integer> changeCard) {
		Map<Integer, Integer> cards = new HashMap<>();
		for (int card : changeCard) {
			if (cards.containsKey(card)) {
				cards.put(card, cards.get(card) + 1);
			} else {
				cards.put(card, 1);
			}
		}
		for (int value : cards.values()) {
			if (value == 4) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 检测飞机
	 * 
	 * @param changeCard
	 * @return
	 */
	private static boolean checkFeiJi(List<Integer> changeCard) {
		List<Integer> tempList = new ArrayList<>();
		tempList.addAll(changeCard);
		// 不是三张的牌
		List<Integer> aCountList = new ArrayList<>();
		// 三张的牌
		List<Integer> countList = new ArrayList<>();
		Map<Integer, Integer> cards = new HashMap<>();
		for (int card : changeCard) {
			if (cards.containsKey(card)) {
				cards.put(card, cards.get(card) + 1);
			} else {
				cards.put(card, 1);
			}
		}
		// 三张一样的牌
		for (Entry<Integer, Integer> keyValue : cards.entrySet()) {
			int key = keyValue.getKey();
			int value = keyValue.getValue();
			if (value >= 3) {
				countList.add(key);
			}
			value = value > 3 ? value - 3 : value;
			if (value != 3) {
				getTempCard(aCountList, value, key);
			}
		}
		boolean checkShunZi = checkShunZi(countList);
		if (checkShunZi) {
			if (aCountList.size() == countList.size() || aCountList.size() == 0) {
				return true;
			} else {
				// 检测其余的牌是不是 都是对子
				if (aCountList.size() == countList.size() * 2) {
					Map<Integer, Integer> temp = new HashMap<>();
					for (int num : aCountList) {
						if (temp.containsKey(num)) {
							temp.put(num, temp.get(num) + 1);
						} else {
							temp.put(num, 1);
						}
					}
					if (temp.size() == aCountList.size() / 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 根据key 获取到所有的card
	 * 
	 * @param cards
	 * @param count
	 * @param key
	 * @return
	 */
	public static void getTempCard(List<Integer> cards, int count, int key) {
		for (int i = 0; i < count; i++) {
			cards.add(key);
		}

	}

	public static void main(String[] args) {
		// Integer[] card = { 6, 4, 5, 4, 6, 6 };
		// for (;;) {
		// List<Card> creatCards = getCreatCards();
		// List<Card> subList = creatCards.subList(0, 12);
		// List<Integer> cards = Arrays.asList(card);
		// // boolean checkLianDui = checkLianDui(cards);
		// // if (checkLianDui) {
		// // return;
		// // }
		// String msg = "";
		// for (Card cardd : subList) {
		// msg += cardd.getNum() + ",";
		// }
		// int cardType = DiZhuUtil.checkcardType(subList);
		// String[] str = new String[] { "不合法", "单张", "对子", "顺子", "连队", "三带一", "飞机",
		// "炸弹", "王炸" };
		// if (cardType > 0) {
		// System.out.println("随机的牌:" + msg + "牌型:" + str[cardType]);
		// continue;
		// }
		//
		// }
	}

	/**
	 * 检测连对子
	 * 
	 * @param changeCard
	 * @return
	 */
	private static boolean checkLianDui(List<Integer> changeCard) {
		if (!changeCard.isEmpty()) {
			// 连子 +对子
			Map<Integer, Integer> cards = new HashMap<>();
			for (int card : changeCard) {
				if (cards.containsKey(card)) {
					cards.put(card, cards.get(card) + 1);
				} else {
					cards.put(card, 1);
				}
			}
			List<Integer> keys = new ArrayList<>();
			for (Entry<Integer, Integer> keyValue : cards.entrySet()) {
				int value = keyValue.getValue();
				if (value == 2) {
					keys.add(keyValue.getKey());
				}
			}
			if (changeCard.size() / 2 == keys.size()) {
				boolean checkShunZi = checkShunZi(keys);
				if (checkShunZi) {
					// 检测是不是连对
					for (Entry<Integer, Integer> keyValue : cards.entrySet()) {
						if (keyValue.getValue() != 2) {
							return false;
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 检测是不是有重复的牌
	 * 
	 * @return
	 */
	public static boolean checkRepetitionCard(List<Integer> changeCard) {
		if (!changeCard.isEmpty()) {
			Map<Integer, Integer> cards = new HashMap<>();
			for (int card : changeCard) {
				if (cards.containsKey(card)) {
					cards.put(card, cards.get(card) + 1);
				} else {
					cards.put(card, 1);
				}
			}
			if (cards.size() == changeCard.size()) {
				return true;
			}
		}
		return false;

	}

	/**
	 * 检测顺子
	 * 
	 * @param changeCard
	 * @return
	 */
	private static boolean checkShunZi(List<Integer> changeCard) {
		if (!changeCard.isEmpty()) {
			// 不能有2 有2则 false
			for (int num : changeCard) {
				if (num == 2) {
					return false;
				}
			}
			boolean checkRepetitionCard = checkRepetitionCard(changeCard);
			if (checkRepetitionCard) {
				descSort(changeCard);
				int maxCardNum = changeCard.get(0);
				int minCardNum = changeCard.get(changeCard.size() - 1);
				if (maxCardNum - minCardNum == changeCard.size() - 1) {
					return true;
				}
			}
		}
		return false;

	}

	public static void descSort(List<Integer> changeCard) {
		Collections.sort(changeCard, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 > o2) {
					return -1;
				} else {
					return 1;
				}
			}
		});

	}

	private static boolean checkSanDaiEr(List<Integer> changeCard) {
		Map<Integer, Integer> cards = new HashMap<>();
		for (int card : changeCard) {
			if (cards.containsKey(card)) {
				cards.put(card, cards.get(card) + 1);
			} else {
				cards.put(card, 1);
			}
		}
		if (cards.size() == 2) {
			for (Entry<Integer, Integer> keyValue : cards.entrySet()) {
				if (keyValue.getValue() == 3) {
					return true;
				}

			}
		}
		return false;

	}

	/**
	 * 检测三带一
	 * 
	 * @param changeCard
	 */
	private static boolean checkSanDaiYi(List<Integer> changeCard) {
		Map<Integer, Integer> cards = new HashMap<>();
		for (int card : changeCard) {
			if (cards.containsKey(card)) {
				cards.put(card, cards.get(card) + 1);
			} else {
				cards.put(card, 1);
			}
		}
		for (Entry<Integer, Integer> keyValue : cards.entrySet()) {
			if (keyValue.getValue() == 3) {
				return true;
			}

		}
		return false;
	}

	/**
	 * 检测是不是相等的牌
	 * 
	 * @return
	 */
	public static boolean checkEqualityCard(List<Integer> changeCard) {
		Map<Integer, Integer> cards = new HashMap<>();
		for (int card : changeCard) {
			if (cards.containsKey(card)) {
				cards.put(card, cards.get(card) + 1);
			} else {
				cards.put(card, 1);
			}
		}
		return cards.size() == 1 ? true : false;
	}

	/**
	 * 检查是不是王炸
	 * 
	 * @param changeCardType
	 */
	private static boolean checkWangZha(List<Integer> changeCardType) {
		int card1 = changeCardType.get(0);
		int card2 = changeCardType.get(1);
		if (card1 == 17 && card2 == 16 || card1 == 16 && card2 == 17) {
			return true;
		}
		return false;

	}

	private static List<Integer> changeCardType(List<Card> cards) {
		List<Integer> checkCard = new ArrayList<>();
		for (Card card : cards) {
			checkCard.add(card.getNum());
		}
		return checkCard;
	}

	/**
	 * 检测是不是相同的牌(对子 或者炸弹)
	 * 
	 * @param cards
	 * @return 相同则返回true
	 */
	public boolean checkSameCard(List<Integer> cards) {
		boolean flag = false;
		Set<Integer> set = new LinkedHashSet<>();
		for (Integer card : cards) {
			set.add(card);
		}
		flag = set.size() == 1 ? true : false;
		return flag;
	}

	/**
	 * 检测手牌中是否有 玩家打出来的牌
	 * 
	 * @param handCards
	 * @param outObjectCard
	 * @return
	 */
	public static boolean checkHandCardContainOutCard(List<Card> handCards, List<Card> outObjectCard) {
		for (Card outCard : outObjectCard) {
			boolean containCard = isContainCard(handCards, outCard);
			if (!containCard) {
				return false;
			}
		}
		return true;
	}

	public static boolean isContainCard(List<Card> handCards, Card outCard) {
		for (Card handCard : handCards) {
			if (outCard.getCardId() == handCard.getCardId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测出牌的合法性
	 * 
	 * @param gameBase
	 * @param controller
	 * @param outCards
	 * @param gameBase
	 * @return
	 * @throws E
	 * 
	 * 
	 * 
	 *             xception
	 */
	public static boolean checKOutCardValidity(LogicController controller, List<Card> outObjectCard,
			DiZhuGameBase gameBase) throws Exception {
		// 桌面已经打出的牌
		List<Card> currentOutCards = gameBase.getCurrentOutCards();
		// 桌面已经打出的牌型
		int currentOutCardType = gameBase.getCurrentOutCardType();
		CalVO vo = gameBase.getPlayerTmpMap().get(controller.getUniqueId()).getCalVO();
		List<Card> handCards = vo.getCards();
		boolean checkHandCardContainOutCard = DiZhuUtil.checkHandCardContainOutCard(handCards, outObjectCard);
		int checkcardType = checkcardType(outObjectCard);
		if (checkcardType == -1) {
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE, ResOutCards_8884.newBuilder()
					.setResult(ResponseResult.newBuilder().setCode(404).setMsg("出牌不符合规则").setSuccess(false)));
			return false;
		}
		// 当前操作的玩家
		if (vo.getPos() != gameBase.getCurrentCtrlPos()) {
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE, ResOutCards_8884.newBuilder()
					.setResult(ResponseResult.newBuilder().setCode(404).setMsg("当前操作位置不对").setSuccess(false)));
			return false;
		}
		if (!checkHandCardContainOutCard) {
			NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE, ResOutCards_8884.newBuilder()
					.setResult(ResponseResult.newBuilder().setCode(404).setMsg("出的牌 在手牌中不包含").setSuccess(false)));
			return false;
		}
		if (currentOutCardType != 0) {
			// 当前桌面操作的牌型 和该出牌玩家操作的牌型一样
			if (checkcardType == currentOutCardType) {
				int currentOutMaxCard = getMaxCard(currentOutCards);
				int outMaxCard = getMaxCard(outObjectCard);
				int card = currentOutCards.get(0).getNum();
				card = card == 2 ? 15 : card;
				int outCard = outObjectCard.get(0).getNum();
				outCard = outCard == 2 ? 15 : outCard;
				switch (checkcardType) {
				case CardTypes.ZHA_DAN:
					// 比较最大的那张牌
					if (outCard <= card) {
						NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE,
								ResOutCards_8884.newBuilder().setResult(ResponseResult.newBuilder().setCode(404)
										.setMsg("炸弹不符合当前出牌规则").setSuccess(false)));
						return false;
					}
					break;
				case CardTypes.SHUN_ZI:
					// 出牌的数量不相等 并且当前出的牌小于桌面的牌
					if (currentOutCards.size() != outObjectCard.size() || currentOutMaxCard >= outMaxCard) {
						NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE,
								ResOutCards_8884.newBuilder().setResult(ResponseResult.newBuilder().setCode(404)
										.setMsg("顺子不符合当前出牌规则").setSuccess(false)));
						return false;
					}
					break;
				case CardTypes.LIAN_DUI:
					// 出牌的数量不相等 并且当前出的牌小于桌面的牌
					if (currentOutCards.size() != outObjectCard.size() || currentOutMaxCard >= outMaxCard) {
						NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE,
								ResOutCards_8884.newBuilder().setResult(ResponseResult.newBuilder().setCode(404)
										.setMsg("连队不符合当前出牌规则").setSuccess(false)));
						return false;
					}
					break;
				case CardTypes.FEI_JI:
					if (currentOutCards.size() != outObjectCard.size()) {
						NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE,
								ResOutCards_8884.newBuilder().setResult(ResponseResult.newBuilder().setCode(404)
										.setMsg("飞机不符合当前出牌规则").setSuccess(false)));
						return false;
					} else {
						// 比较三张牌打大小
						int currentFeiJiCard = getFeiJiOrLianDuiMaxCard(currentOutCards, 3);
						currentFeiJiCard = currentFeiJiCard == 2 ? 15 : currentFeiJiCard;
						int outFeiJiCard = getFeiJiOrLianDuiMaxCard(outObjectCard, 3);
						outFeiJiCard = outFeiJiCard == 2 ? 15 : outFeiJiCard;
						if (outFeiJiCard <= currentFeiJiCard) {
							NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE,
									ResOutCards_8884.newBuilder().setResult(ResponseResult.newBuilder().setCode(404)
											.setMsg("不符合当前出牌规则").setSuccess(false)));
							return false;
						}
					}
					break;
				case CardTypes.DUI_ZI:
					if (card > outCard) {
						NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE,
								ResOutCards_8884.newBuilder().setResult(ResponseResult.newBuilder().setCode(404)
										.setMsg("对子不符合当前出牌规则").setSuccess(false)));
						return false;
					}
					break;
				case CardTypes.DAN_ZHANG:
					if (outCard <= card) {
						NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE,
								ResOutCards_8884.newBuilder().setResult(ResponseResult.newBuilder().setCode(404)
										.setMsg("单张不符合当前出牌规则").setSuccess(false)));
						return false;
					}
					break;
				}
			} else {
				if (checkcardType != CardTypes.ZHA_DAN && checkcardType != CardTypes.HUO_JIAN) {
					NetSdkApi.sendMsg(controller, DiZhuCode.RES_OUT_CARDS_8884_VALUE,
							ResOutCards_8884.newBuilder().setResult(ResponseResult.newBuilder().setCode(404)
									.setMsg("牌型不一致不符合当前出牌规则").setSuccess(false)));
					return false;
				}
			}
		} else {

		}
		return true;
	}

	/**
	 * 移除打出去的手牌(移除逻辑可能有问题)
	 * 
	 * @param outCards
	 * @param calVO
	 */
	public static void removeOutCards(List<Card> handCards, List<Card> outCards) {
		for (int i = 0; i < outCards.size(); i++) {
			for (int j = 0; j < handCards.size(); j++) {
				if (outCards.get(i).getCardId() == handCards.get(j).getCardId()) {
					handCards.remove(j);
				}
			}
		}
	}

	/**
	 * 操作牌中 最大的那张牌(获取到的是最大牌的点数)
	 * 
	 * @return
	 */
	public static int getMaxCard(List<Card> cards) {
		int maxTemp = 0;
		for (Card card : cards) {
			maxTemp = maxTemp > card.getNum() ? maxTemp : card.getNum() % 100;
		}
		return maxTemp;
	}

	/**
	 * 获取飞机中最大的那张牌
	 * 
	 * @param cards
	 * @param type
	 *            3表示飞机 2 表示对子
	 * @return
	 */
	public static int getFeiJiOrLianDuiMaxCard(List<Card> cards, int type) {
		Map<Integer, Integer> hashMap = new HashMap<>();
		for (Card card : cards) {
			if (hashMap.containsKey(card.getNum())) {
				hashMap.put(card.getNum(), hashMap.get(card.getNum()) + 1);
			} else {
				hashMap.put(card.getNum(), 1);
			}
		}
		for (Entry<Integer, Integer> keyValue : hashMap.entrySet()) {

			if (keyValue.getValue() == 3) {
				return keyValue.getKey();
			}

		}
		return 0;
	}

	/**
	 * 检测下注的合理性
	 * 
	 * @param multiple
	 * @return
	 */
	public static boolean checkChooseBetValidity(int multiple) {

		for (int i = 0; i <= 3; i++) {
			if (multiple == i) {
				return true;
			}
		}
		return false;
	}

	public static List<Card> getOutObjectCard(List<CardInfo_8884> cardsList) {
		List<Card> cards = new ArrayList<>();
		for (CardInfo_8884 infoCard : cardsList) {
			Card card = new Card();
			card.setCardId(infoCard.getCardId());
			card.setColor(infoCard.getCardColor());
			card.setNum(infoCard.getCardNum());
			cards.add(card);
		}
		return cards;
	}

	/**
	 * 
	 * @param room
	 * @param pos
	 *            当前的操作位置
	 * @return
	 */
	public static int getCtrlPos(Room room, int pos) {
		int ctrlPos = pos + 1 > room.getMaxPlayerNum() ? 1 : pos + 1;
		return ctrlPos;
	}

	/**
	 * 检测是不是春天或者反春 1正常输赢 2春天 3 是反春
	 * 
	 * @param room
	 * @return
	 */
	public static int checkChunTian(Room room) {
		int winType = 2;
		DiZhuGameBase gameBase = (DiZhuGameBase) room.getGameBase();
		String landlordId = gameBase.getLandlordId();
		// 地主 只出了一手的牌
		for (LogicController c : room.getControllers().values()) {
			CalVO calVO = gameBase.getPlayerTmpMap().get(c.getUniqueId()).getCalVO();
			if (calVO.getOutCardCount() == 1) {
				winType = 3;
				LoggerUtil.info("本局输赢类型是：{}", "反春");
				return winType;
			}
		}
		// 两个闲家一手的牌都没有出
		for (LogicController c : room.getControllers().values()) {
			CalVO calVO = gameBase.getPlayerTmpMap().get(c.getUniqueId()).getCalVO();
			if (!c.getUniqueId().equals(landlordId)) {
				if (calVO.getOutCardCount() != 0) {
					winType = 1;
					LoggerUtil.info("本局输赢类型是：{}", "正常输赢");
					return winType;
				}
			}
		}
		LoggerUtil.info("本局输赢类型是：{}", "春天");
		return winType;
	}

	/**
	 * 获取抢庄倍数 最大的玩家
	 * 
	 * @param vos
	 * @return
	 */
	public static CalVO getMaxMultiple(List<CalVO> vos) {
		vos.sort((CalVO h1, CalVO h2) -> h2.getMultiple().compareTo(h1.getMultiple()));
		return vos.get(0);
	}

	/**
	 * 所有牌中 随机找出一张 最小的牌
	 * 
	 * @param cards
	 * @return
	 */
	public static List<Card> randomOutCard(List<Card> cards) {
		cards.sort((Card card1, Card card2) -> card1.getNum().compareTo(card2.getNum()));
		List<Card> cardList = new ArrayList<>();
		Card card = cards.get(0);
		cardList.add(card);
		return cardList;
	}

	public static List<Card> randomOutCard(List<Card> cards, Card card) {
		int num = card.getNum();
		List<Card> outCards = new ArrayList<>();
		cards.sort((Card card1, Card card2) -> card1.getNum().compareTo(card2.getNum()));
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getNum() > num) {
				outCards.add(cards.get(i));
				return outCards;
			}
		}
		return null;
	}

	public static List<Card> randomOutCard(List<Card> cards, List<Card> card) {
		List<Card> outCards = new ArrayList<>();
		List<Card> backCards = new ArrayList<>();
		cards.sort((Card card1, Card card2) -> card1.getNum().compareTo(card2.getNum()));
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getNum() == cards.get(i + 1).getNum()) {
				outCards.add(cards.get(i));
				outCards.add(cards.get(i + 1));
				i++;
			}
		}
		for (int i = 0; i < outCards.size() - 1; i++) {
			if (outCards.get(i).getNum() > card.get(0).getNum()) {
				backCards.add(outCards.get(i));
				backCards.add(outCards.get(i + 1));
				return backCards;
			} else {
				i++;
			}
		}
		return null;
	}

	/**
	 * 从所有的抢庄倍数中选择 最大的倍数
	 * 
	 * @param multiples
	 * @return
	 */
	public static int getListMultiple(List<Integer> multiples) {
		multiples.sort((Integer multiple, Integer multiple1) -> multiple1.compareTo(multiple));
		return multiples.get(0);
	}
}
