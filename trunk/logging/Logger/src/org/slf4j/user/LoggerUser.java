package org.slf4j.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUser {
	private static final Logger logger = LoggerFactory.getLogger(LoggerUser.class.getName());
	public static void main(String[] args) {
		logger.info("info msg");
		logger.info("debug ", new Object());
		logger.debug("debug msg");
		logger.debug("debug ", new Object());
		logger.error("error msg");
		logger.error("error", new Object());

	}

}
