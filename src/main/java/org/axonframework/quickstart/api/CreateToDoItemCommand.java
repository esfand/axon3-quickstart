package org.axonframework.quickstart.api;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Command used to create a new ToDoItem
 *
 * @author Jettro Coenradie
 */
public class CreateToDoItemCommand {

    @TargetAggregateIdentifier
    private final String todoId;
    private final String description;

    public CreateToDoItemCommand(String todoId, String description) {
        this.todoId = todoId;
        this.description = description;
    }

    public String getTodoId() {
        return todoId;
    }

    public String getDescription() {
        return description;
    }
}
