package org.motadata.practice.web.sockjs;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions;

public class SockJSDemo
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

        EventBus eventBus = vertx.eventBus();

        SockJSHandlerOptions options = new SockJSHandlerOptions().setRegisterWriteHandler(true);
        SockJSHandler sockJSHandler = SockJSHandler.create(vertx, options);

        router.route("/app/*").subRouter(sockJSHandler.socketHandler(sockJSSocket ->{
            String writeHandlerId= sockJSSocket.writeHandlerID();
            eventBus.send(writeHandlerId, Buffer.buffer("Hello World"));

            sockJSSocket.handler(buffer -> {
               sockJSSocket.write(buffer.toString());
            });

//            sockJSSocket.handler(sockJSSocket::write);
        }));

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(router).listen(8080);

    }
}
