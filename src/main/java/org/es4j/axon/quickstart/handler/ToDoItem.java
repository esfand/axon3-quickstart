package org.es4j.axon.quickstart.handler;

import org.axonframework.domain.DomainEventMessage;
import org.axonframework.eventsourcing.AbstractEventSourcedAggregateRoot;
import org.axonframework.eventsourcing.EventSourcedEntity;
import org.es4j.axon.quickstart.api.ToDoItemCompletedEvent;
import org.es4j.axon.quickstart.api.ToDoItemCreatedEvent;

/**
 * @author Jettro Coenradie
 */
public class ToDoItem extends AbstractEventSourcedAggregateRoot {

    private String id;

    public ToDoItem() {
    }

    public ToDoItem(String id, String description) {
        apply(new ToDoItemCreatedEvent(id, description));
    }

    public void markCompleted() {
        apply(new ToDoItemCompletedEvent(id));
    }

    @Override
    protected Iterable<? extends EventSourcedEntity> getChildEntities() {
        return null;
    }

    @Override
    protected void handle(DomainEventMessage eventMessage) {
        if (eventMessage.getPayloadType().equals(ToDoItemCreatedEvent.class)) {
            ToDoItemCreatedEvent event = (ToDoItemCreatedEvent) eventMessage.getPayload();
            this.id = event.getTodoId();
        }
    }

    @Override
    public String getIdentifier() {
        return id;
    }
}
