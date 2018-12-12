
package com.version.util;

import java.io.Serializable;

/**
 * @author 作者:cb
 * @version 创建时间：2018年11月7日 下午5:44:46
 * 
 */
public interface CardTypes extends Serializable {

	int DAN_ZHANG = 1, DUI_ZI = 2, SHUN_ZI = 3, LIAN_DUI = 4, SAN_DAI_YI = 5, SAN_BU_DAI = 6, SAN_DAI_ER = 7,
			FEI_JI = 8, SI_DAI_ER = 9, ZHA_DAN = 10, HUO_JIAN = 11;
	int CHOOSE_MULTIPLE = 10;
	int OUT_CARD = 11;

}
