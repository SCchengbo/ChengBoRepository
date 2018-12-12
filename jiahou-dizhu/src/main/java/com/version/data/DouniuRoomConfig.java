package com.version.data;

import java.util.HashMap;
import java.util.Map;

import com.version.common.entity.RoomConfig;
import com.version.common.util.StringHelper;

public class DouniuRoomConfig extends RoomConfig {
	private static final long serialVersionUID = -3800797779374246494L;
	private int maxPlayerNum = 5;// 最大玩家数量
	private int model = 1;// 1抢庄模式 2轮庄模式 3牛牛坐庄 4 牌大坐庄 5霸王庄,6通比模式,7上庄斗牛
	private int openCardNum = 4;// 开牌数量(少于等于5)
	private int tuizhuMulty = 6;// 推注倍数 0 无
	private String conf;
	private transient final Map<Integer, Integer> confMap = new HashMap<Integer, Integer>();
	private String specialCardType;
	private int yaZhuMultiple = 1;// 压注的倍数;
	// {"tuizhuMulty":1,"model":1,"openCardNum":4,"maxPlayerNum":8,"conf":"1:1,2:2,3:3"}

	public int getYaZhuMultiple() {
		return yaZhuMultiple;
	}

	public void setYaZhuMultiple(int yaZhuMultiple) {
		this.yaZhuMultiple = yaZhuMultiple;
	}

	public DouniuRoomConfig() {
		super();
	}
    
	public String getSpecialCardType() {
		return specialCardType;
	}

	public void setSpecialCardType(String specialCardType) {
		this.specialCardType = specialCardType;
	}

	public int getMaxPlayerNum() {
		return maxPlayerNum;
	}

	public void setMaxPlayerNum(int maxPlayerNum) {
		this.maxPlayerNum = maxPlayerNum;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getOpenCardNum() {
		return openCardNum;
	}

	public void setOpenCardNum(int openCardNum) {
		this.openCardNum = openCardNum;
	}

	public int getTuizhuMulty() {
		return tuizhuMulty;
	}

	public String getConf() {
		return conf;
	}

	public void setConf(String conf) {
		this.conf = conf;
		if (StringHelper.isNotEmpty(conf)) {
			String[] strs = this.conf.split(",");
			for (String str : strs) {
				String[] strs1 = str.split(":");
				;
				this.confMap.put(Integer.valueOf(strs1[0]), Integer.valueOf(strs1[1]));
			}
		}
	}

	public Map<Integer, Integer> getConfMap() {
		return confMap;
	}

	public void setTuizhuMulty(int tuizhuMulty) {
		this.tuizhuMulty = tuizhuMulty;
	}

}
