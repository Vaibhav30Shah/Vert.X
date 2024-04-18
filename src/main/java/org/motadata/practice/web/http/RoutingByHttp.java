package org.motadata.practice.web.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class RoutingByHttp extends AbstractVerticle
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new RoutingByHttp());
    }

    @Override
    public void start() throws Exception
    {
        HttpServer server = vertx.createHttpServer();


    }
}
