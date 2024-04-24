package org.motadata.practice.web.differentHandlers;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.ResponseTimeHandler;
import io.vertx.ext.web.handler.TimeoutHandler;

public class TimeoutHandlers
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

        router.route("/timeout").handler(TimeoutHandler.create(5000));
//see cors handler in cors package for further info and program demo
        router.route("/timeout").handler(ctx->{
            ctx.response().setStatusCode(500).end("Timeout over");
        });

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }
}
