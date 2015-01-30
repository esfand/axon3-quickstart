package org.axonframework.quickstart;

import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.quickstart.api.ToDoItemCompletedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.axonframework.domain.GenericEventMessage.asEventMessage;

/**
 * @author Allard Buijze
 */
public class RunClusteringEventBusWithSpring {

    public static void main(String[] args) throws InterruptedException {
        // We start up a Spring context. In web applications, this is normally done through a context listener
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("cluster-config.xml");

        // we get the event bus from the application context
        EventBus eventBus = applicationContext.getBean(EventBus.class);
        eventBus.publish(asEventMessage(new ToDoItemCompletedEvent("todo1")));

        // we close the application context to make sure everything shuts down properly
        applicationContext.stop();
        applicationContext.close();
    }

    public static class ThreadPrintingEventListener {

        @EventHandler
        public void onEvent(EventMessage event) {
            System.out.println("Received " + event.getPayload().toString() + " on thread named "
                                       + Thread.currentThread().getName());
        }
    }

    public static class AnotherThreadPrintingEventListener extends ThreadPrintingEventListener {

    }
}
