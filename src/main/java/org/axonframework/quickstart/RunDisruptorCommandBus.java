package org.axonframework.quickstart;

import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.quickstart.annotated.ToDoEventHandler;
import org.axonframework.quickstart.annotated.ToDoItem;
import org.axonframework.repository.Repository;

import java.io.File;

/**
 * Setting up the basic ToDoItem sample with a disruptor command and event bus and a file based event store. The
 * configuration takes place using spring. We use annotations to find the command and event handlers.
 *
 * @author Allard Buijze
 */
public class RunDisruptorCommandBus {

    public static void main(String[] args) throws InterruptedException {
        // we'll store Events on the FileSystem, in the "events" folder
        EventStore eventStore = new FileSystemEventStore(new SimpleEventFileResolver(new File("./events")));

        // a Simple Event Bus will do
        EventBus eventBus = new SimpleEventBus();

        // we register the event handlers
        AnnotationEventListenerAdapter.subscribe(new ToDoEventHandler(), eventBus);

        // we use default settings for the disruptor command bus
        DisruptorCommandBus commandBus = new DisruptorCommandBus(eventStore, eventBus);

        // now, we obtain a repository from the command bus
        Repository<ToDoItem> repository = commandBus.createRepository(new GenericAggregateFactory<>(ToDoItem.class));

        // we use the repository to register the command handler
        AggregateAnnotationCommandHandler.subscribe(ToDoItem.class, repository, commandBus);

        // the CommandGateway provides a friendlier API to send commands
        CommandGateway commandGateway = new DefaultCommandGateway(commandBus);

        // and let's send some Commands on the CommandBus.
        CommandGenerator.sendCommands(commandGateway);

        // we need to stop the disruptor command bus, to make sure we release all resources
        commandBus.stop();
    }
}
