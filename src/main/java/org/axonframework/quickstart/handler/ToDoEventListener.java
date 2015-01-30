package org.axonframework.quickstart.handler;

import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.EventListener;
import org.axonframework.quickstart.api.ToDoItemCompletedEvent;
import org.axonframework.quickstart.api.ToDoItemCreatedEvent;

/**
 * Event listener for all events, results are printed to system out.
 *
 * @author Jettro Coenradie
 */
public class ToDoEventListener implements EventListener {

    @Override
    public void handle(EventMessage eventMessage) {
        if (eventMessage.getPayloadType().equals(ToDoItemCreatedEvent.class)) {
            ToDoItemCreatedEvent event = (ToDoItemCreatedEvent) eventMessage.getPayload();
            System.out.println(String.format("We've got something to do: %s (%s)",
                                             event.getDescription(),
                                             event.getTodoId()));
        } else if (eventMessage.getPayloadType().equals(ToDoItemCompletedEvent.class)) {
            ToDoItemCompletedEvent event = (ToDoItemCompletedEvent) eventMessage.getPayload();
            System.out.println(String.format("We've completed the task with id %s", event.getTodoId()));
        }
    }
}
