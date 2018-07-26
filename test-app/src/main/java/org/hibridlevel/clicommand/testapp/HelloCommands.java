package org.hibridlevel.clicommand.testapp;

import org.hibridlevel.clicommand.ShellCommandExitCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class HelloCommands {

	private static Logger LOG = LoggerFactory
			.getLogger(HelloCommands.class);

	@Autowired
	private ShellCommandExitCode exitCode;

	@ShellMethod("Says hello to given name")
	public void hello(
			@ShellOption() String name
	) {
		LOG.info("HelloCommands is running...");

		LOG.info("Hello " + name + "!");

		LOG.info("HelloCommands is finished...");

		exitCode.setExitCode(42);
	}

	@ShellMethod("Throws an exception")
	public void error() {
		LOG.error("RuntimeException is coming...");
		throw new RuntimeException("?");
	}
}