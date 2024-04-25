package org.motadata.practice.core.event_bus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class Publisher extends AbstractVerticle
{
    public static void main(String[] args)
    {
//        Launcher.executeCommand("run", Publisher.class.getName(), "-cluster");

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new Publisher());

        vertx.close();

//        vertx.deployVerticle(new Subscriber());
    }

    @Override
    public void start() throws Exception
    {
        vertx.eventBus().request("test", new JsonObject(), ar -> {});
        EventBus eventBus = vertx.eventBus();
        vertx.setPeriodic(1000, l -> {
            eventBus.publish("event_bus", "Data is shared");
            System.out.println("Event bus published");
        });
    }
}
