package org.motadata.practice.buffers;

import io.vertx.core.buffer.Buffer;

import java.util.Arrays;

public class Buffers
{
    public static void main(String[] args)
    {
        Buffer buffer = Buffer.buffer(2);
        System.out.println(buffer.length());
        buffer.appendString("Hello");
        System.out.println(buffer.length());
        buffer.appendString("World");
        System.out.println(buffer.length());
        buffer.appendInt(2);
        System.out.println(buffer.length());
        buffer.setInt(1,2);
        System.out.println(buffer.length());
        System.out.println(buffer.getInt(0));
        System.out.println(buffer.getInt(1));
        System.out.println(buffer.getInt(2));

        int[] a=new int[]{1,2,3,4,5,6,7,8,9};
        System.out.println(a.length);
        Buffer buffer1=Buffer.buffer(Arrays.toString(a));
        System.out.println(buffer1.length());
        for (int i = 0; i <= buffer1.length(); i=i+4){
            System.out.println("int value at " + i + " is " + buffer1.getInt(i));
        }

        Buffer buff = Buffer.buffer(128);
        int pos = 15;
        buff.setUnsignedInt(16,  200);
        System.out.println(buff.getUnsignedInt(pos));
    }
}
