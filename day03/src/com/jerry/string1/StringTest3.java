package com.jerry.string1;

/*
 * String,StringBuffer,StringBuilder 三者的效率对比
 * 效率上 StringBuilder > StringBuffer > String
 * 说明：
 * 1.String的效率是最低的，因为每次操作，都会重新在方法区的字符串常量池中创建新字符串
 * 2.StringBuilder和StringBuffer 都是在原有底层的char[] 型数组上面扩容，所以效率高很多。
 * 3.StringBuffer比StringBuilder 效率低，是因为StringBuffer 是线程安全的,效率较StringBuilder要低
 * */
public class StringTest3 {
    public static void main(String[] args) {

        long startTime = 0L;
        long endTime = 0L;

        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");

        //开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer 消耗的时间为：" + (endTime - startTime));

        //开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder 消耗的时间为：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text += String.valueOf(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("String 消耗的时间为：" + (endTime - startTime));
    }
}
