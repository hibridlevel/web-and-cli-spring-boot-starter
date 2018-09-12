package org.hibridlevel.clicommand.autoconfigure;

import org.hibridlevel.clicommand.CliCommandApplicationRunner;
import org.hibridlevel.clicommand.ShellCommandExitCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CliCommandAutoConfiguration {

	@Bean
	public CliCommandApplicationRunner cliCommandApplicationRunner() {
		return new CliCommandApplicationRunner();
	}

	@Bean
	public ShellCommandExitCode shellCommandExitCode() {
		return new ShellCommandExitCode();
	}
}
