package com.version.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.version.common.entity.Room;
import com.version.common.entity.game.GameBase;
import com.version.data.cal.SmallCalPanel;
import com.version.data.sceen.RecoverSceen;
import com.version.data.vo.CalVO;
import com.version.data.vo.PlayerTmpVO;
import com.version.entity.logic.LogicController;
import com.version.util.DiZhuUtil;

public class DiZhuGameBase extends GameBase {
	private static final long serialVersionUID = 4332711719655033166L;
	private final SmallCalPanel smallCalPanel = new SmallCalPanel();// 小结算面板
	private final RecoverSceen sceen = new RecoverSceen();// 场景恢复数据使用
	private final Map<String, PlayerTmpVO> playerTmpMap = new HashMap<String, PlayerTmpVO>();
	private Room room;// 房间引用
	private String landlordId;// 地主id
	private List<Card> lastCards = new ArrayList<>();// 桌面上剩余的三张牌
	private List<Card> currentOutCards = new ArrayList<>();// 当前 桌面上玩家打出来的牌
	private int currentOutCardType;// 当前桌面打出来的牌型
	private List<Card> cards = new ArrayList<>();// 牌库牌
	private int currentCtrlPos;// 当前操作位置
	private int qiangZhuangCount;// 抢庄人数
	private int currentChooseMultiple;// 当前叫分的倍数
	private int cardMultiple = 1;// 本局牌型分数
	private int currentMaxCardPos;// 本轮 最大牌玩家的位置
	private int passCount;// 过的次数 累计2次过 表示 本轮决出 最大的玩家
	private long lastTimer;// 倒计时检测
	private String currentCtrlCardUid;// 当期操作牌的玩家id
	private List<Integer> chooseMultiple = new ArrayList<>();// 每个玩家 叫地主的分数集合
	private int roudOver;// 本轮是否结束 0 默认值 表示 没有结束 1 表示 本轮结束
	private final Map<String, List<Card>> outCardMap = new HashMap<>();

	public DiZhuGameBase() {

	}

	public DiZhuGameBase(Room room) {
		List<Card> creatCards = DiZhuUtil.getCreatCards();
		cards = creatCards;
		for (int i = 0; i < 3; i++) {
			lastCards.add(cards.remove(0));
		}
		for (LogicController c : room.getControllers().values()) {
			List<Card> cardList = new ArrayList<>();
			for (int i = 0; i < 17; i++) {
				cardList.add(cards.remove(0));
			}
			int pos = room.findSelfPos(c);
			playerTmpMap.put(c.getUniqueId(),
					new PlayerTmpVO(c.getUniqueId(), new CalVO(cardList, c.getUniqueId(), pos)));
		}
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SmallCalPanel getSmallCalPanel() {
		return smallCalPanel;
	}

	public RecoverSceen getSceen() {
		return sceen;
	}

	public Map<String, PlayerTmpVO> getPlayerTmpMap() {
		return playerTmpMap;
	}

	public String getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(String landlordId) {
		this.landlordId = landlordId;
	}

	public List<Card> getLastCards() {
		return lastCards;
	}

	public void setLastCards(List<Card> lastCards) {
		this.lastCards = lastCards;
	}

	public List<Card> getCurrentOutCards() {
		return currentOutCards;
	}

	public void setCurrentOutCards(List<Card> currentOutCards) {
		this.currentOutCards = currentOutCards;
	}

	public int getCurrentOutCardType() {
		return currentOutCardType;
	}

	public void setCurrentOutCardType(int currentOutCardType) {
		this.currentOutCardType = currentOutCardType;
	}

	public int getCurrentCtrlPos() {
		return currentCtrlPos;
	}

	public void setCurrentCtrlPos(int currentCtrlPos) {
		this.currentCtrlPos = currentCtrlPos;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public int getQiangZhuangCount() {
		return qiangZhuangCount;
	}

	public void setQiangZhuangCount(int qiangZhuangCount) {
		this.qiangZhuangCount = qiangZhuangCount;
	}

	public int getCurrentChooseMultiple() {
		return currentChooseMultiple;
	}

	public void setCurrentChooseMultiple(int currentChooseMultiple) {
		this.currentChooseMultiple = currentChooseMultiple;
	}

	public int getCardMultiple() {
		return cardMultiple;
	}

	public void setCardMultiple(int cardMultiple) {
		this.cardMultiple = cardMultiple;
	}

	public int getCurrentMaxCardPos() {
		return currentMaxCardPos;
	}

	public void setCurrentMaxCardPos(int currentMaxCardPos) {
		this.currentMaxCardPos = currentMaxCardPos;
	}

	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	public long getLastTimer() {
		return lastTimer;
	}

	public void setLastTimer(long lastTimer) {
		this.lastTimer = lastTimer;
	}

	public String getCurrentCtrlCardUid() {
		return currentCtrlCardUid;
	}

	public void setCurrentCtrlCardUid(String currentCtrlCardUid) {
		this.currentCtrlCardUid = currentCtrlCardUid;
	}

	public List<Integer> getChooseMultiple() {
		return chooseMultiple;
	}

	public void setChooseMultiple(List<Integer> chooseMultiple) {
		this.chooseMultiple = chooseMultiple;
	}

	public int getRoudOver() {
		return roudOver;
	}

	public void setRoudOver(int roudOver) {
		this.roudOver = roudOver;
	}

	public Map<String, List<Card>> getOutCardMap() {
		return outCardMap;
	}

}
