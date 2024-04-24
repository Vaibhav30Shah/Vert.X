package org.motadata.practice.web.reroute;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class Rerouting extends AbstractVerticle
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.get("/p1/abc").handler(routingContext -> {
            routingContext.put("Name","Vaibhav");
            System.out.println("Name is " + routingContext.get("Name"));
            routingContext.next();
        });

        router.route("/p2/def").handler(routingContext -> {
            routingContext.put("Age","21");
            System.out.println("Age is " + routingContext.get("Age"));
            routingContext.end("Over");
        });

        router.route("/p1/abc").handler(routingContext -> {
            System.out.println("City is " + routingContext.get("City"));
            routingContext.put("City","Ahmedabad");
            routingContext.reroute("/p2/def");
        });

        server.requestHandler(router).listen(8080);
    }
}
