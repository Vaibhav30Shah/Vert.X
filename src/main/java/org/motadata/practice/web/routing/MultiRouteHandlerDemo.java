package org.motadata.practice.web.routing;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class MultiRouteHandlerDemo extends AbstractVerticle
{
    public static void main(String[] args)
    {
        Vertx vertx=Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        Route route=router.route("/");

        route.handler(routingContext -> {
            routingContext.response().setChunked(true);
            routingContext.response().write("Route 1");
            System.out.println("Route 1");
            routingContext.vertx().setTimer(1000, handler-> routingContext.next());
        });

        route.handler(routingContext -> {
            routingContext.response().write("Route 2");
            System.out.println("Route 2");
            routingContext.vertx().setTimer(1000, handler-> routingContext.next());
        });

        route.handler(routingContext -> {
            routingContext.response().write("Route 3");
            System.out.println("Route 3");
            routingContext.vertx().setTimer(1000, handler-> routingContext.end());
        });

        server.requestHandler(router).listen(4444,"localhost").onComplete(msg->{
            if(msg.succeeded()) System.out.println("Server started");
            else System.out.println("Staring failed");
        });

        router.get("/").respond(routingContext -> Future.succeededFuture(new JsonObject().put("Hello","World")));
    }
}
