package org.motadata.practice.json;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.parsetools.JsonParser;

public class JsonParserDemo
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        JsonParser parser = JsonParser.newParser();

        parser.handler(event ->
        {
            switch (event.type())
            {
                case START_ARRAY:
                    System.out.println("Start Array");
                    parser.objectValueMode();
                    break;
                case VALUE:
                    String id = event.fieldName();
                    if (id != null) System.out.println("id: " + id + " ,value: " + event.value());
                    else if (event.isString()) System.out.println("String value: " + event.value());
                    else System.out.println("Other value: " + event.value());
                    break;
                case END_OBJECT:
                    System.out.println("End Object");
                    parser.objectEventMode();
                    break;
                case END_ARRAY:
                    System.out.println("End Array");
                    parser.arrayEventMode();
                    break;
            }
        });

        parser.exceptionHandler(err -> System.out.println("Exception: " + err.getMessage()));

        //whole json will be printed in value
        parser.handle(Buffer.buffer("[{\"firstName\":\"Bob\","));
        parser.handle(Buffer.buffer("\"lastName\":\"Morane\"},"));
        parser.handle(Buffer.buffer("{\"firstName\":\"Luke\",\"lastName\":\"Lucky\"}"));
        parser.handle(Buffer.buffer("]"));

        System.out.println("\n");

        //individual values can be accesed.
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(true).add(456).add("Seven Eight Nine");
        parser.handle(jsonArray.toBuffer());

        System.out.println("\n");

        //no start and end array will be executed as it is only object
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("firstName", "Bob");
        jsonObject.put("lastName", "Morane");
        parser.handle(jsonObject.toBuffer());

        System.out.println("\n");

        //will parse this also
        parser.handle(Buffer.buffer("[\"Hi\",\"How\"]"));

        parser.end();
        vertx.close();
    }
}
