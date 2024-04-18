package org.motadata.practice.http;

import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class HtmlForms
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request ->
        {
            if (request.uri().equals("/"))
            {
                request.response().end("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Basic Form</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h2>Basic Form</h2>\n" +
                        "    <form action=\"/form\" ENCTYPE=\"multipart/form-data\" method=\"POST\" name=\"wibble\">\n" +
                        "        <label for=\"name\">Name:</label><br>\n" +
                        "        <input type=\"text\" id=\"name\" name=\"name\"><br>\n" +
                        "        <label for=\"email\">Email:</label><br>\n" +
                        "        <input type=\"email\" id=\"email\" name=\"email\"><br><br>\n" +
                        "        <input type=\"submit\" value=\"Submit\">\n" +
                        "    </form>\n" +
                        "</body>\n" +
                        "</html>\n");
            }
            else if (request.uri().equals("/form"))
            {
                request.setExpectMultipart(true);
                request.endHandler(v ->
                {
                    MultiMap formAttributes = request.formAttributes();
                    request.response().end(formAttributes.toString());
                });
            }
            else
            {
                request.response().setStatusCode(404).end("Not Found");
            }
        });

        server.listen(1234, "localhost").onComplete(res ->
        {
            if (res.succeeded())
                System.out.println("Server listening on port 1234");
            else
                System.out.println("Server listening on port 1234 failed");
        });
    }
}
