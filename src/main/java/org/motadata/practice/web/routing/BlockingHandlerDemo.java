package org.motadata.practice.web.routing;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class BlockingHandlerDemo
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        Route route = router.route("/");

        route.blockingHandler(routingContext ->
        {
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {
            }
            routingContext.response().setChunked(true);

            routingContext.response().write("Hello World");
            System.out.println("Hello World");
            routingContext.next();
        });

        route.handler(routingContext ->
        {
            routingContext.response().end("Hello 2");
            System.out.println("Hello 2");
        });

        server.requestHandler(router).listen(8080);

        vertx.close();
    }
}
