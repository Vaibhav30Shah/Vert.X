package org.motadata.practice.filesystem;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

import java.io.File;

public class FileDemo
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        FileSystem fileSystem = vertx.fileSystem();

        //creating file
        fileSystem.createFile("demo.txt");

        //writing to a file
        Buffer buffer = Buffer.buffer();
        buffer.appendString("Hello World");
        fileSystem.writeFile("demo.txt", buffer);

        //printing the contents of file on console
        fileSystem.readFile("demo1.txt").onComplete(res->{
           if(res.succeeded()) System.out.println(res.result());
           else System.out.println(res.cause());
        });

        fileSystem.copy("demo.txt","temp.txt");

        vertx.close();
    }
}
