package org.motadata.practice.core.virtual_thread;

import io.vertx.core.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;

public class VirtualThreadDemo
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        AbstractVerticle abstractVerticle = new AbstractVerticle()
        {
            @Override
            public void start() throws Exception
            {
                HttpClient client = vertx.createHttpClient();
                HttpClientRequest req = Future.await(client.request(HttpMethod.GET, 8080, "localhost", "/"));
                HttpClientResponse resp = Future.await(req.send());
                int status = resp.statusCode();
                Buffer body = Future.await(resp.body());
            }
        };

        vertx.deployVerticle(abstractVerticle, new DeploymentOptions().setThreadingModel(ThreadingModel.VIRTUAL_THREAD));
    }
}
