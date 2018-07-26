package org.hibridlevel.clicommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.shell.Input;
import org.springframework.shell.InputProvider;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Credit: https://github.com/spring-cloud/spring-cloud-skipper/blob/master/spring-cloud-skipper-shell-commands/src/main/java/org/springframework/cloud/skipper/shell/command/support/InteractiveModeApplicationRunner.java
 */
@Order(InteractiveShellApplicationRunner.PRECEDENCE - 5)
public class CliCommandApplicationRunner implements ApplicationRunner {

	@Autowired
	private Shell shell;

	@Autowired
	private ConfigurableEnvironment environment;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ArrayList<String> argsToShellCommand = new ArrayList<>(
			Arrays.asList(args.getSourceArgs())
		);

		if (!argsToShellCommand.isEmpty()) {
			InteractiveShellApplicationRunner.disable(environment);
			shell.run(new StringInputProvider(argsToShellCommand));
		}
	}

	/**
	 * {@link InputProvider} which gives a single input to shell.
	 */
	private class StringInputProvider implements InputProvider {

		private final List<String> commands;

		private boolean done;

		public StringInputProvider(List<String> words) {
			this.commands = words;
		}

		@Override
		public Input readInput() {
			if (!done) {
				done = true;
				return new Input() {
					@Override
					public List<String> words() {
						return commands;
					}

					@Override
					public String rawText() {
						return StringUtils.collectionToDelimitedString(commands, " ");
					}
				};
			}
			else {
				return null;
			}
		}
	}
}
