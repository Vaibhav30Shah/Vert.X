package org.motadata.practice.udp;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;

public class DataGramSocketSender
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());

        Buffer buffer = Buffer.buffer("sending datagram packet");

        socket.send(buffer,1234, "localhost").onComplete(asyncResult -> {
            System.out.println("Send succeded? "+asyncResult.succeeded());
        });


    }
}
