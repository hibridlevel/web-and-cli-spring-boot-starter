package org.hibridlevel.clicommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class ShellCommandExitCode implements ExitCodeGenerator {

	private static Logger LOG = LoggerFactory.getLogger(ShellCommandExitCode.class);

	// if command is not set exit code: returns 0 (everything was fine)
	private int exitCode = 0;

	@Override
	public int getExitCode() {

		if(exitCode != 0) {
			LOG.info("Exit code: " + exitCode);
		}

		return exitCode;
	}

	public void setExitCode(int exitCode) {
		if(exitCode < 0) {
			throw new IllegalArgumentException(
				"'exitCode' must be grater than or equals 0. Otherwise Spring Boot will replace it with 1."
			);
		}

		this.exitCode = exitCode;
	}
}
