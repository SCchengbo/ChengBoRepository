
package com.version.util;

/**
 * @author 作者:cb
 * @version 创建时间：2018年12月6日 上午9:55:52
 * 
 */
public enum DiZhuCardType {
	DAN_ZHANG(1), DUI_ZI(2), SHUN_ZI(3), LIAN_DUI(4), SAN_DAI_YI(5), SAN_BU_DAI(6), SAN_DAI_ER(7), FEI_JI(8), SI_DAI_ER(
			9), ZHA_DAN(10), HUO_JIAN(11);
	public int cardType;

	private DiZhuCardType(int cardType) {
		this.cardType = cardType;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public int getCardType(int cardType) {
		return this.cardType;

	}
}
