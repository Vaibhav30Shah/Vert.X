package org.motadata.practice.web.requestBodyHandle;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class RequestBidyHandling
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        Router router= Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.post("/v1/api").handler(req->{
            req.body().asString();
        });


    }
}
