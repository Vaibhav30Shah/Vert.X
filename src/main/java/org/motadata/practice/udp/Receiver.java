package org.motadata.practice.udp;

import io.vertx.core.Vertx;
import io.vertx.core.datagram.DatagramSocket;
import io.vertx.core.datagram.DatagramSocketOptions;

public class Receiver
{
    public static void main(String[] args)
    {
        Vertx vertx= Vertx.vertx();

        DatagramSocket socket= vertx.createDatagramSocket(new DatagramSocketOptions());

        socket.handler(packet->{
            System.out.println("Packet received");
            System.out.println(packet.data());
        }).listen(1234, "localhost");
    }
}
