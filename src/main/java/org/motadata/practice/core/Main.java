package org.motadata.practice.core;
import io.vertx.core.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class Main
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(3));

        vertx.deployVerticle(new Abc(), new DeploymentOptions().setInstances(2), handler->{
            if(handler.succeeded()) System.out.println("Suceess");
            else System.out.println("Failed");
        });


//        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
//
//        operatingSystemMXBean.getTotalPhysicalMemorySize();
    }
}

