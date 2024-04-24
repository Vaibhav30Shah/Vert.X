package org.motadata.practice.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class ContextDataDemo extends AbstractVerticle
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new ContextDataDemo());
    }

    @Override
    public void start() throws Exception
    {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.get("/").blockingHandler(ctx ->
        {
            ctx.response().setChunked(true);
            ctx.put("Hello", "World");
            ctx.response().write("Hello");
            ctx.next();
        });

        router.get("/abc").handler(ctx ->
        {
            String str = ctx.get("Hello");
            if (str != null)
            {
                ctx.response().end(str);
            }
            else
            {
                ctx.response().end("Null");
            }
        });

        server.requestHandler(router).listen(8080);
    }
}
