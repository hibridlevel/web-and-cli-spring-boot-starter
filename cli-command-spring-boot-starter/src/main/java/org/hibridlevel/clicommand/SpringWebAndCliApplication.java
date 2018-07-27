package org.hibridlevel.clicommand;

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

			/*
				Returns custom error code to the shell which runs application (this jar).
				It is necessary when you use cli commands on a CI runner (especially on GitLab CI).

				Credit: http://www.baeldung.com/spring-boot-exit-codes
			*/
			System.exit(
					SpringApplication.exit(
							applicationBuilder.run(args)
					)
			);
		} else {
			// if there is no argument run in web app mode
			// in this case disable shell to avoid displaying shell prompt and reading commands from stdin
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
