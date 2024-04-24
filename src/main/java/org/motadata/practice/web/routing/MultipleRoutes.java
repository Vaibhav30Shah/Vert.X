package org.motadata.practice.web.routing;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ErrorHandler;

public class MultipleRoutes
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        Route route = router.route("/");

        Route route2 = router.route().pathRegex(".*vaibhav/:shah");
        
        route.blockingHandler(routingContext ->
        {
            routingContext.response().setChunked(true);

            routingContext.response().write("Hello World");
            System.out.println("Hello World");
            routingContext.next();
        });

        route.handler(ctx->{
            ErrorHandler.create(vertx);
        });

        route2.handler(routingContext ->
        {
            String str=routingContext.pathParam("shah");
            System.out.println(str);
            routingContext.response().end("Hello 2");
            System.out.println("Hello 2");
        });

        server.requestHandler(router).listen(8080);

//        vertx.close();
    }
}
