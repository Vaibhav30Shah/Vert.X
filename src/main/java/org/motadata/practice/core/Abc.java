package org.motadata.practice.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class Abc extends AbstractVerticle
{
    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {
        vertx.setPeriodic(1000, l->{
            System.out.println(Thread.currentThread().getName());
        });

        startPromise.complete();
    }
}
