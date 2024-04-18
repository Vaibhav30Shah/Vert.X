package org.motadata.practice.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpServer;

public class Test extends AbstractVerticle
{
    private HttpServer server;

    @Override
    public void start(Promise<Void> startPromise) throws Exception
    {
        server = vertx.createHttpServer();

        server.requestHandler(req ->
        {
            req.response().putHeader("content-type", "text/plain").end("Hello");
        });

        //binding the server
        server.listen(8080, res ->
        {
            if (res.succeeded())
            {
                startPromise.complete();
            }
            else
            {
                startPromise.fail(res.cause());
            }
        });
    }

    @Override
    public void stop(Promise<Void> stopPromise)
    {
        System.out.println("Closing server");
    }

    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new Test());

//        vertx.setPeriodic(1000, id->
//        {
//            System.out.println("Hello World");
//        });
//

        FileSystem fs = vertx.fileSystem();

        Future<Void> future = fs.createFile("~/Desktop/temp").compose(v ->
        {
            return fs.writeFile("~/Desktop/temp", Buffer.buffer());
        }).compose(v ->
        {
            return fs.move("~/Desktop/temp", "~/Desktop/demo");
        });

    }
}
