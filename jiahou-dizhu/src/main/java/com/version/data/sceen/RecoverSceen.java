package com.version.data.sceen;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class RecoverSceen implements Serializable {
	private static final long serialVersionUID = -8952613149557532247L;
	private final Map<String, QiangzhuangOperate> qiangzhaungData = new HashMap<String, QiangzhuangOperate>();
	private final Map<String, YazhuOperate> yazhuData = new HashMap<String, YazhuOperate>();
	private final Map<String, LiangPaiOperate> liangPaiData = new HashMap<String, LiangPaiOperate>();

	public Map<String, QiangzhuangOperate> getQiangzhaungData() {
		return qiangzhaungData;
	}

	public Map<String, YazhuOperate> getYazhuData() {
		return yazhuData;
	}

	public Map<String, LiangPaiOperate> getLiangPaiData() {
		return liangPaiData;
	}
}
