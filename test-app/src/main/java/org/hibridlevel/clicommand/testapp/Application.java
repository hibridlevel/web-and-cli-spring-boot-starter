package org.hibridlevel.clicommand.testapp;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {

		SpringWebAndCliApplication.run(Application.class, args);
	}
}
