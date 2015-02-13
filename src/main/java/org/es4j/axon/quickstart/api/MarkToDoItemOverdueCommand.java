package org.es4j.axon.quickstart.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * @author Allard Buijze
 */
public class MarkToDoItemOverdueCommand {
    @TargetAggregateIdentifier
    private final String todoId;

    public MarkToDoItemOverdueCommand(String todoId) {
        this.todoId = todoId;
    }

    public String getTodoId() {
        return todoId;
    }
}
