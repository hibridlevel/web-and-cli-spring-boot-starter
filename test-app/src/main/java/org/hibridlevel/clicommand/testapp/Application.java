package org.hibridlevel.clicommand.testapp;

import org.hibridlevel.clicommand.ShellCommandExitCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ExitCodeGenerator {

	private static Logger LOG = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ShellCommandExitCode shellCommandExitCode;

	public static void main(String[] args) throws Exception {

		SpringWebAndCliApplication.run(Application.class, args);
	}

	@Override
	public int getExitCode() {

		final int exitCode = shellCommandExitCode.getExitCode();
		if(exitCode != 0) {
			LOG.info("Exit code: " + exitCode);
		}

		return exitCode;
	}
}
