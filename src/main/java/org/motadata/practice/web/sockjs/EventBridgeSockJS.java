package org.motadata.practice.web.sockjs;

import io.vertx.core.Vertx;
import io.vertx.ext.bridge.BridgeOptions;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class EventBridgeSockJS
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        Router router = Router.router(vertx);

        SockJSBridgeOptions options = new SockJSBridgeOptions()
                .addInboundPermitted(new PermittedOptions().setAddressRegex(".*"))
                .addOutboundPermitted(new PermittedOptions().setAddressRegex(".*"));

        SockJSHandler sockJSHandler = SockJSHandler.create(vertx);

        router.route("/app/*").subRouter(sockJSHandler.bridge(options));

        vertx.createHttpServer().requestHandler(router).listen(8080, "localhost");


    }
}
