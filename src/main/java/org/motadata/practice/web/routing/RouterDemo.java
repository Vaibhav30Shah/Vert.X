package org.motadata.practice.web.routing;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class RouterDemo
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

        HttpServer server = vertx.createHttpServer();

        router.route("/").handler(routingContext -> {
            routingContext.response().putHeader("content-type", "text/plain");

            routingContext.response().end("Hello World");
        });

        server.requestHandler(router).listen(8080).onComplete(msg->{
           if(msg.succeeded()){
               System.out.println("HTTP server started on port 8080");
           }
           else {
               System.out.println("HTTP server failed to start: "+msg.cause());
           }
        });
    }
}
