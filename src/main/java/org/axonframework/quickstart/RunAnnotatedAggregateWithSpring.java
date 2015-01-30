package org.axonframework.quickstart;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Setting up the basic ToDoItem sample with a simple command and event bus and a file based event store. The
 * configuration takes place using spring. We use annotations to find the command and event handlers.
 *
 * @author Jettro Coenradie
 */
public class RunAnnotatedAggregateWithSpring {

    public static void main(String[] args) {
        // Load the spring beans from the xml configuration file.
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("basic-config.xml");

        // Obtain the gateway from the context to send commands.
        CommandGateway commandGateway = applicationContext.getBean("commandGateway", CommandGateway.class);

        // and let's send some Commands on the CommandBus.
        CommandGenerator.sendCommands(commandGateway);
    }
}
