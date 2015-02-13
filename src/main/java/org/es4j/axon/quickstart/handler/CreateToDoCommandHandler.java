package org.es4j.axon.quickstart.handler;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.CommandMessage;
import org.es4j.axon.quickstart.api.CreateToDoItemCommand;
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.UnitOfWork;

/**
 * @author Jettro Coenradie
 */
public class CreateToDoCommandHandler implements CommandHandler<CreateToDoItemCommand> {

    private final Repository<ToDoItem> repository;

    public CreateToDoCommandHandler(Repository<ToDoItem> repository) {
        this.repository = repository;
    }

    @Override
    public Object handle(CommandMessage<CreateToDoItemCommand> commandMessage, UnitOfWork unitOfWork) throws Throwable {
        CreateToDoItemCommand command = commandMessage.getPayload();
        ToDoItem toDoItem = new ToDoItem(command.getTodoId(), command.getDescription());
        repository.add(toDoItem);
        return toDoItem;
    }
}
