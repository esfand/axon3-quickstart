package org.axonframework.quickstart.annotated;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.axonframework.quickstart.api.ToDoItemCompletedEvent;
import org.axonframework.quickstart.api.ToDoItemCreatedEvent;
import org.joda.time.DateTime;

/**
 * Event handler that listens to both events and prints a message to the system output stream.
 *
 * @author Jettro Coenradie
 */
public class ToDoEventHandler {

    @EventHandler
    public void handle(ToDoItemCreatedEvent event, @Timestamp DateTime time) {
        System.out.println(String.format("We've got something to do: %s (%s, created at %s)",
                                         event.getDescription(),
                                         event.getTodoId(),
                                         time.toString("d-M-y H:m")));
    }

    @EventHandler
    public void handle(ToDoItemCompletedEvent event) {
        System.out.println(String.format("We've completed the task with id %s", event.getTodoId()));
    }
}
