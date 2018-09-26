package com.chaofan.websocket;

import com.chaofan.websocket.Util.BingImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebsocketApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebsocketApplication.class, args);
		LOGGER.debug("本次同步了"+BingImageUtil.download(0,7)+"张壁纸！");
		LOGGER.debug("本次同步了"+BingImageUtil.download(7,7)+"张壁纸！");
		LOGGER.debug("WebsocketApplication is starting...");
	}
}
