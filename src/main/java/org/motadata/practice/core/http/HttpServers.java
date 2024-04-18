package org.motadata.practice.http;

import io.vertx.core.*;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpVersion;

public class HttpServers
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        HttpServerOptions options = new HttpServerOptions().setUseAlpn(true);

        HttpServer server = vertx.createHttpServer(options);

        server.requestHandler(req ->
        {
            System.out.println("Request received: " + req.uri());
            System.out.println(req.version());
            System.out.println(req.method());
//            System.out.println(req.headers());
            System.out.println(req.path());
            System.out.println(req.body());
            System.out.println(req.query());
            System.out.println(req.authority());
            System.out.println(req.params());
            System.out.println(req.remoteAddress());

            req.response().putHeader("content-type", "text/html").end("<html><body><h1>Hello from vert.x!</h1></body></html>");
        });

        server.listen(8080, "localhost").onComplete(res ->
        {
            if (res.succeeded())
            {
//                System.out.println(HttpVersion.valueOf(HttpVersion.HTTP_1_1.name()));
                System.out.println("HTTP server started on port " + res.result().actualPort());
            }else
                System.out.println("HTTP server failed to start on port " + res.result().actualPort());
        });

    }
}
