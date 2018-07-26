package org.hibridlevel.clicommand.testapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	private static Logger LOG = LoggerFactory.getLogger(PingController.class);

	@RequestMapping(method = RequestMethod.GET, value = "/ping")
	public String ping() {

		LOG.info("Ping");

		return "Pong";
	}
}
