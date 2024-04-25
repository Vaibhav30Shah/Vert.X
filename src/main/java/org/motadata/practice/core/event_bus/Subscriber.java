package org.motadata.practice.core.event_bus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

public class Subscriber extends AbstractVerticle
{
    public static void main(String[] args)
    {
//        Launcher.executeCommand("run", Subscriber.class.getName(), "-cluster");

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new Subscriber());

        vertx.close();
    }

    @Override
    public void start() throws Exception
    {
        EventBus eventBus = vertx.eventBus();

        eventBus.consumer("event_bus",msg-> System.out.println("Consumer 1: "+msg.body()));
        eventBus.consumer("event_bus",msg-> System.out.println("Consumer 2: "+msg.body()));
        eventBus.consumer("events",msg-> System.out.println("Consumer 3: "+msg.body()));

        System.out.println("Ready");
    }
}
