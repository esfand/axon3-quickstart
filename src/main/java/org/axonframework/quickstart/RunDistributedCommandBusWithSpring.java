package org.axonframework.quickstart;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.jgroups.stack.GossipRouter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.BindException;

/**
 * See {@link RunDistributedCommandBus}, only difference is that we use spring to wire all the beans.
 *
 * @author Jettro Coenradie
 */
public class RunDistributedCommandBusWithSpring {
    public static void main(String[] args) throws Exception {
        // Load the Load factor from the command line or use default 100
        Integer loadFactor = RunDistributedCommandBus.determineLoadFactor();

        System.setProperty("loadFactor", loadFactor.toString());

        // Start the GossipRouter if it is not already running
        GossipRouter gossipRouter = new GossipRouter();
        try {
            gossipRouter.start();
        } catch (BindException e) {
            System.out.println("Gossip router is already started in another JVM instance.");
        }

        // Load the spring beans from the xml configuration file.
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("distributed-config.xml");

        // Obtain the gateway from the context to send commands.
        CommandGateway commandGateway = applicationContext.getBean("commandGateway", CommandGateway.class);

        // Load the amount of times to send the commands from the command line or use default 1
        Integer numberOfCommandLoops = RunDistributedCommandBus.determineNumberOfCommandLoops();

        for (int i = 0; i < numberOfCommandLoops; i++) {
            CommandGenerator.sendCommands(commandGateway);
        }
    }

}
