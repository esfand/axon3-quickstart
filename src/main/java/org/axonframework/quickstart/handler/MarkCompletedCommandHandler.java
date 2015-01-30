package org.axonframework.quickstart.handler;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.quickstart.api.MarkCompletedCommand;
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.UnitOfWork;

/**
 * @author Jettro Coenradie
 */
public class MarkCompletedCommandHandler implements CommandHandler<MarkCompletedCommand> {

    private Repository<ToDoItem> repository;

    public MarkCompletedCommandHandler(Repository<ToDoItem> repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(CommandMessage<MarkCompletedCommand> commandMessage, UnitOfWork unitOfWork) throws Throwable {
        MarkCompletedCommand command = commandMessage.getPayload();
        ToDoItem toDoItem = repository.load(command.getTodoId());
        toDoItem.markCompleted();
        return toDoItem;
    }
}
