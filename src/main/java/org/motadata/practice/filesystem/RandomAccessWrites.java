package org.motadata.practice.filesystem;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.FileSystem;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.file.OpenOptions;

public class RandomAccessWrites
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        OpenOptions openOptions = new OpenOptions();

        FileSystem fileSystem = vertx.fileSystem();

        fileSystem.open("demo1.txt", openOptions).onComplete(res ->
        {
            if (res.succeeded())
            {
                AsyncFile asyncFile = res.result();
                Buffer buffer = Buffer.buffer(1000);

                //writing in file
                for (int i = 0; i < 10; i++)
                {
                    asyncFile.write(Buffer.buffer("Demo 1 file"), buffer.length() * i).onComplete(comp ->
                    {
                        if (comp.succeeded()) System.out.println(comp.result());
                        else System.out.println(comp.cause());
                    });
                }

                //reading from file
                for (int i = 0; i < 10; i++)
                {
                    asyncFile.read(buffer, i * 100, i * 100, 100).onComplete(comp ->
                    {
                        if (comp.succeeded())
                        {
                            System.out.println(comp.result());
                        }
                        else
                        {
                            comp.cause().printStackTrace();
                        }
                    });
                }
            }
            else
            {
                System.out.println("Error opening the file");
            }
        });
    }
}
