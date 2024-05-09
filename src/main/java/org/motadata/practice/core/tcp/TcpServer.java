package org.motadata.practice.core.tcp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class TcpServer extends AbstractVerticle
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        NetServerOptions options = new NetServerOptions().setPort(1234).setLogActivity(true);

        NetServer server = vertx.createNetServer(options);

        server.connectHandler(socket ->
        {
            System.out.println(socket.localAddress() + " connected");

            //receive data from client
            socket.handler(buf ->
            {
                System.out.println("Received data: " + buf.toString());
            });

            //write to client
            socket.write("Server received");

            //close client
//            socket.closeHandler(close ->
//            {
//                System.out.println("Socket closed");
//            });
//
//            //close server
//            server.close(res ->
//            {
//                System.out.println("Server closed");
//            });
        });

        //server listening
        server.listen(1234).onComplete(res ->
        {
            if (res.succeeded())
                System.out.println("Server listening on port " + server.actualPort());
            else
                System.out.println("Server listening on port " + server.actualPort() + " failed");
        });
    }
}
