package org.es4j.axon.quickstart;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.es4j.axon.quickstart.api.CreateToDoItemCommand;
import org.es4j.axon.quickstart.api.MarkCompletedCommand;

import java.util.UUID;

/**
 * Runner that uses the provided CommandGateway to send some commands to our application.
 *
 * @author Jettro Coenradie
 */
public class CommandGenerator {

    public static void sendCommands(CommandGateway commandGateway) {
        final String itemId1 = UUID.randomUUID().toString();
        final String itemId2 = UUID.randomUUID().toString();
        commandGateway.sendAndWait(new CreateToDoItemCommand(itemId1, "Check if it really works!"));
        commandGateway.sendAndWait(new CreateToDoItemCommand(itemId2, "Think about the next steps!"));
        commandGateway.sendAndWait(new MarkCompletedCommand(itemId1));
    }
}
