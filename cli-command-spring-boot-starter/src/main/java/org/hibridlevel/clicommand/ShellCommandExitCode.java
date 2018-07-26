package org.hibridlevel.clicommand;

import org.springframework.stereotype.Component;

@Component
public class ShellCommandExitCode {

	// if command is not set exit code: returns 0 (everything was fine)
	private int exitCode = 0;

	public int getExitCode() {
		return exitCode;
	}

	public ShellCommandExitCode setExitCode(int exitCode) {
		if(exitCode < 0) {
			throw new IllegalArgumentException(
				"'exitCode' must be grater than or equals 0. Otherwise Spring Boot will replace it with 1."
			);
		}

		this.exitCode = exitCode;
		return this;
	}
}
