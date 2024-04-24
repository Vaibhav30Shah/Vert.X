package org.motadata.practice.web.subroute;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class SubRouting
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router mainR = Router.router(vertx);

        Router subR = Router.router(vertx);

        mainR.route().handler(BodyHandler.create());


        mainR.route("/productsAPI/*").subRouter(subR);

        subR.get("/products/:productId").handler(ctx->{
            ctx.response().setChunked(true);
            ctx.response().write("Getting Product ID.");
            ctx.end("Product ID: " + ctx.pathParam("productId"));
        });

        subR.put("/products/:productId").handler(ctx->{
           ctx.response().end("Product put");
        });

        subR.delete("/products/:productId").handler(ctx->{
           ctx.response().end("Product "+ctx.pathParam("productId")+" deleted");
        });

        mainR.route("/home").handler(ctx->{
            ctx.response().end("Welcome to home");
        });

        server.requestHandler(mainR).listen(8080).onComplete(res->{
            if(res.succeeded()) System.out.println("Server started on port 8080");
            else System.out.println("Server failed to start on port 8080");
        });


    }
}
