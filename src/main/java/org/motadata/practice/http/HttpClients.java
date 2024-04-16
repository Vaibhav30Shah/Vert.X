package org.motadata.practice.http;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;

public class HttpClients
{
    public static void main(String[] args)
    {
        Vertx vertx=Vertx.vertx();

        HttpClient client=vertx.createHttpClient();

        client.request(HttpMethod.GET, 8080, "localhost", "/")
                .compose(req->req.send()
                        .compose(resp->{
                            System.out.println("response: "+resp);
                            System.out.println("status: "+resp.statusCode());
                            return resp.body();
                        })).onSuccess(resp->{
                            System.out.println("Got data: "+resp.toString("ISO-8859-1"));
                }).onFailure(Throwable::printStackTrace);
    }
}
