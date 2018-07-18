package com.chaofan.websocket;

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
		LOGGER.debug("WebsocketApplication is starting...");
		LOGGER.info("WebsocketApplication is starting...");
		LOGGER.warn("WebsocketApplication is starting...");
		LOGGER.error("WebsocketApplication is starting...");
	}
}
