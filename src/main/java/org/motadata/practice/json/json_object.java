package org.motadata.practice.json;

import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class json_object
{
    public static void main(String[] args)
    {
        String jsonString="{\"1\":\"One\",\"2\":\"Two\"}";
        JsonObject jsonObject = new JsonObject(jsonString);
        System.out.println(jsonObject);

        Map map = new HashMap<>();
        map.put("1", "One");
        map.put("2", "Two");
        JsonObject jsonObject1=new JsonObject(map);
        System.out.println(jsonObject1);

        jsonObject1.put("3", "Three");
        System.out.println(jsonObject1);

        String string = jsonObject1.encode();
        System.out.println(string);

        int str=jsonObject1.getInteger("1");
        System.out.println(str);
    }
}
