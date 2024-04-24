package org.motadata.practice.web.cors;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.ResponseTimeHandler;
import io.vertx.ext.web.handler.TimeoutHandler;

public class CORSHandling
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

        router.route().handler(ResponseTimeHandler.create()); //kai thatu nathi time pass che

        router.route().handler(TimeoutHandler.create(5000));

        router.route().handler(ctx->{
            CorsHandler.create();
        });

        router.route("/").handler(ctx->{
           ctx.response().end("Hello World");
        });

        vertx.createHttpServer().requestHandler(router).listen(8080).onComplete(req->{
            if(req.succeeded()) System.out.println("HTTP server started on port 8080");
        });
    }
}
