package com.version;

import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.version.common.util.LoggerUtil;
import com.version.common.work.WorkManager;
import com.version.entity.ai.AiConfig;
import com.version.mq.conf.MqConfiguration;
import com.version.process.server.chat.ChatChannelManager;
import com.version.sdk.common.NetContext;
import com.version.sdk.server.TcpServer;
import com.version.service.api.ILogicService;
import com.version.timer.DiZhuTimerCheckWork;
import com.version.timer.LogicCommonAiTimerCheckWork;
import com.version.timer.LogicCommonTimerCheckWork;
import com.version.util.AiConfigUtil;

/**
 * 服务器主线程(继承tcpServer)
 * 
 * @author sunhua
 * @version 1.0版本<br>
 * @ProjectName:kypj-parent-douniu
 * @PackageName:com.version
 * @ClassName:DouniuServer
 * @Date:2017年7月21日 下午2:56:37
 */
@Import({ NetContext.class, MqConfiguration.class })
@EnableDubboConfiguration
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, RedissonAutoConfiguration.class })
public class DiZhuServer extends TcpServer implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ILogicService logicService;

	@Override
	public void beforeAccepter(String netIp, String netPort) {
		LoggerUtil.info("开始初始化###################");
	}

	@Override
	public void afterAccepter(String netIp, String netPort) {
		LoggerUtil.info("接受服务#########################");
	}

	public static void main(String[] args) {
		SpringApplication.run(DiZhuServer.class, args);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			logicService.initLogicServer();
			WorkManager.getManager().submit(DiZhuTimerCheckWork.class);
			WorkManager.getManager().submit(LogicCommonTimerCheckWork.class);
			ChatChannelManager.getManager().start();
			if (AiConfig.IS_OPEN) {
				AiConfigUtil.loadConfig();
				WorkManager.getManager().submit(LogicCommonAiTimerCheckWork.class);
			}

		} catch (Exception e) {
			LoggerUtil.error(e);
			System.exit(0);
		}
	}
}
