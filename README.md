Web and CLI Spring Boot Starter
==

Summary
--
This Spring Boot Starter helps create an application which can run as a 'standard' web application or as a cli command 
based on command line arguments. 

It integrates `Spring Shell` into your application, but you can use `Spring Shell Command`
as a standalone command. If you pass command line arguments to your applications we will find the appropriated command and run it.
After it is finished spring boot application will closed. It is usefull if you sometimes want to run any on-demand command.
E.g. create a new token for your REST service, setup development database or directory structure, etc.

Also you can configure exit code of Spring Boot application with the `ShellCommandExitCode` bean. 
It helps a lot when you want to use your command in a CI job, and if the command failed you want to abort the job. 
(Most CI server checks the job exit code to determine it was successfully finished or failed.)

Usage
--
1. Add dependency
    ```
    allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
    }
       
    dependencies {
        // ...
        compile "com.github.hibridlevel:web-and-cli-spring-boot-starter"
        // ...
    }
    ```
2. Create a command, e.g.:
    ```java
    package org.hibridlevel.clicommand.testapp;
    
    import org.hibridlevel.clicommand.ShellCommandExitCode;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
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
    }
    ```
3. Create a controller, e.g.:
    ```java
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
    ```
4. Start your application a little bit different way:
    ```java
    package org.hibridlevel.clicommand.testapp;
    
    import org.hibridlevel.clicommand.SpringWebAndCliApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    
    @SpringBootApplication
    public class Application {
    
        public static void main(String[] args) {
    
            SpringWebAndCliApplication.run(Application.class, args);
        }
    }
    ```
5. Test it
    - Now, when you start your application 
        - without any command line arguments: your web application will run
        - with `hello --name SpringWebAndCliApplication` arguments: your command will run

Credits
--
- https://stackoverflow.com/questions/45615729/how-to-run-spring-boot-application-both-as-a-web-application-as-well-as-a-comman
- https://stackoverflow.com/questions/14236424/how-can-i-find-all-beans-with-the-custom-annotation-foo
- http://www.baeldung.com/spring-boot-console-app
- http://www.baeldung.com/spring-boot-exit-codes
-  "If your other packages hierarchies are below your main app with the @SpringBootApplication annotation, you’re covered by implicit components scan."
http://www.springboottutorial.com/spring-boot-and-component-scan
- http://www.baeldung.com/spring-shell-cli
- [Upload to maven central](https://medium.com/@scottyab/how-to-publish-your-open-source-library-to-maven-central-5178d9579c5)
    - Jitpack.io is usefull for us