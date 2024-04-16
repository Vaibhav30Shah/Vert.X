package org.motadata.practice.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;

public class TcpClient
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        NetClientOptions options = new NetClientOptions().setLogActivity(true);

        NetClient client = vertx.createNetClient(options);

        client.connect(1234, "localhost", res ->
        {
            if (res.succeeded())
            {
                NetSocket socket = res.result();
                System.out.println("Connected to server");

                //cleint receiving
                socket.handler(buffer ->
                {
                    System.out.println("Client received: " + buffer.toString("UTF-8"));
                });

                //send data to server
                for (int i = 0; i < 10; i++)
                {
                    String str = "hello " + i + "\n";
                    System.out.println("Net client sending: " + str);
                    socket.write(str);
                }
            }
            else
            {
                System.out.println("Failed to connect to server");
            }
        });

    }
}
