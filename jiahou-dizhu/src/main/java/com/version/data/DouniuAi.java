
package com.version.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 作者:cb
 * @version 创建时间：2018年10月16日 上午9:37:00
 * 
 */
public class DouniuAi {
	private final static Map<Integer, String> qiangZhuangConfig = new HashMap<>();
	private final static Map<Integer, String> xiaZhuConfig = new HashMap<>();
	private final static Map<Integer, String> kaiPaiConfig = new HashMap<>();

	public static Map<Integer, String> getQiangzhuangconfig() {
		return qiangZhuangConfig;
	}

	public static Map<Integer, String> getXiazhuconfig() {
		return xiaZhuConfig;
	}

	public static Map<Integer, String> getKaipaiconfig() {
		return kaiPaiConfig;
	}

}
