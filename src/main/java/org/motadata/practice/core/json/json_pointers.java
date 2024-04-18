package org.motadata.practice.json;

import io.vertx.core.json.pointer.JsonPointer;

public class json_pointers
{
    public static void main(String[] args)
    {
        JsonPointer pointer=JsonPointer.from("/hello/world");
        System.out.println(pointer);

        JsonPointer pointer2 = JsonPointer.create()
                .append("kem")
                .append("chho");
        System.out.println(pointer2);
    }
}
