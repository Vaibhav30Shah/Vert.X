package org.motadata.practice.json;

import io.vertx.core.json.JsonArray;

public class demo_json_array
{
    public static void main(String[] args)
    {
        String array="[\"one\",\"two\",\"three\",\"four\",\"five\"]";
        JsonArray jsonArray=new JsonArray(array);
        jsonArray.add(1,"six").add("seven");
        System.out.println(jsonArray);
    }
}
