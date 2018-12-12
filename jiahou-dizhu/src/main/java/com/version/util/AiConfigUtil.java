
package com.version.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import com.version.common.util.FileUitl;
import com.version.data.DouniuAi;

/**
 * @author 作者:cb
 * @version 创建时间：2018年10月15日 下午4:31:10
 * 
 */
public final class AiConfigUtil {
	public static void loadConfig() throws IOException {
		String filePath = FileUitl.class.getClassLoader().getResource("aiconfig").getPath();
		InputStream inputStream = null;
		String configType = "qiangZhuangMultipleConfig";
		try {
			inputStream = new FileInputStream(new File(filePath));
			Properties properties = new Properties();
			properties.load(inputStream);
			configType = "liangPaiConfig";
			for (int i = 1; i <= 4; i++) {
				String property = properties.getProperty(configType + i);
				DouniuAi.getKaipaiconfig().put(i, property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

	}

	public static Map<Integer, String> getQiangZhuangConfig() {

		return DouniuAi.getQiangzhuangconfig();

	}

	public static Map<Integer, String> getKanPaiConfig() {

		return DouniuAi.getKaipaiconfig();

	}

	public static Map<Integer, String> getXiaZhuConfig() {

		return DouniuAi.getXiazhuconfig();

	}
}
