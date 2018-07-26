package org.hibridlevel.clicommand.testapp;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.HashMap;

public class SpringWebAndCliApplication {

	public static void run(Class<?> primarySource, String... args) {

		SpringApplicationBuilder applicationBuilder = new SpringApplicationBuilder()
				.sources(primarySource)
			;
		if(args.length > 0) {
			// if there is any argument run in cli command mode
			applicationBuilder
					.web(WebApplicationType.NONE)
					.bannerMode(Banner.Mode.OFF)
			;

			// http://www.baeldung.com/spring-boot-exit-codes
			System.exit(
					SpringApplication.exit(
							applicationBuilder.run(args)
					)
			);
		} else {
			applicationBuilder
					.properties(
							new HashMap<String, Object>()
							{{
								put("spring.shell.interactive.enabled", false);
							}}
					)
			;
			applicationBuilder.run(args);
		}
	}
}
