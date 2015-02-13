package org.es4j.axon.quickstart.handler;

import org.es4j.axon.quickstart.handler.CreateToDoCommandHandler;
import org.es4j.axon.quickstart.handler.MarkCompletedCommandHandler;
import org.es4j.axon.quickstart.handler.ToDoItem;
import org.es4j.axon.quickstart.api.CreateToDoItemCommand;
import org.es4j.axon.quickstart.api.MarkCompletedCommand;
import org.es4j.axon.quickstart.api.ToDoItemCompletedEvent;
import org.es4j.axon.quickstart.api.ToDoItemCreatedEvent;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.*;

/**
 * Notice that we need to register the command handlers with the fixture.
 *
 * @author Jettro Coenradie
 */
public class ToDoItemTest {

    private FixtureConfiguration<ToDoItem> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(ToDoItem.class);
        fixture.registerCommandHandler(CreateToDoItemCommand.class,
                                       new CreateToDoCommandHandler(fixture.getRepository()));
        fixture.registerCommandHandler(MarkCompletedCommand.class,
                                       new MarkCompletedCommandHandler(fixture.getRepository()));
    }

    @Test
    public void testCreateToDoItem() throws Exception {
        fixture.given()
               .when(new CreateToDoItemCommand("todo1", "Need to implement the aggregate"))
               .expectEvents(new ToDoItemCreatedEvent("todo1", "Need to implement the aggregate"));
    }

    @Test
    public void testMarkToDoItemAsCompleted() throws Exception {
        fixture.given(new ToDoItemCreatedEvent("todo1", "Need to implement the aggregate"))
               .when(new MarkCompletedCommand("todo1"))
               .expectEvents(new ToDoItemCompletedEvent("todo1"));
    }
}
