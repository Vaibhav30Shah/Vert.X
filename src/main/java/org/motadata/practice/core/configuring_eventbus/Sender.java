package org.motadata.practice.core.configuring_eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;

public class Sender extends AbstractVerticle
{
    public static void main(String[] args)
    {
        Launcher launcher = new Launcher(){
            @Override
            public void beforeStartingVertx(VertxOptions options)
            {
                options.setEventBusOptions(new EventBusOptions()
                        .setSsl(true));
            }
        };
        launcher.dispatch(new String[] { "run",Sender.class.getName(),"-cluster" });
    }

    @Override
    public void start() throws Exception
    {
        EventBus eb=vertx.eventBus();

        vertx.setPeriodic(1000, v->{
            eb.request("add","ping", ar->{
               if(ar.succeeded()){
                   System.out.println("Received "+ar.result().body());
               }
               else{
                   System.out.println("Error: "+ar.cause());
               }
            });
        });
    }
}
