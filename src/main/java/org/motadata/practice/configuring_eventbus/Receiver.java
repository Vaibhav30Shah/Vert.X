package org.motadata.practice.configuring_eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.net.JksOptions;

public class Receiver extends AbstractVerticle
{
    public static void main(String[] args)
    {
        Launcher launcher = new Launcher()
        {
            @Override
            public void beforeStartingVertx(VertxOptions options)
            {
                options.setEventBusOptions(new EventBusOptions()
                        .setSsl(true));
            }
        };
        launcher.dispatch(new String[]{"run", Receiver.class.getName(), "-cluster"});
    }

    @Override
    public void start() throws Exception
    {
        EventBus eb=vertx.eventBus();

        eb.consumer("add", msg->{
            System.out.println("Received: " + msg.body());
        });

        System.out.println("Receiver started");
    }
}
