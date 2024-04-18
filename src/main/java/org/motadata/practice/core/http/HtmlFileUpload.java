package org.motadata.practice.http;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

public class HtmlFileUpload
{
    public static void main(String[] args)
    {
        Vertx vertx= Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request -> {
           if(request.uri().equals("/")){
               request.response().sendFile("core/http/fileUpload/index.html");
           }
           else if(request.uri().startsWith("/form")){
               request.setExpectMultipart(true);
               request.uploadHandler(upload->{
                   upload.streamToFileSystem(upload.filename())
                           .onSuccess(v-> request.response().end("Successfully uploaded file: " + upload.filename()))
                           .onFailure(err->request.response().end(err.getMessage()));
               });
           }
           else{
               request.response().setStatusCode(404).end("File not found");
           }
        });

        server.listen(1428,"localhost");
    }
}
