package com.jerry.string1;

/*
 * String 字符串，使用一堆"" 引起来表示。
 * 1.String 类是final的，表示不可被继承。
 * 2.String 实现了Serializable 接口，表示字符串是支持序列化的。
 *   String 实现了Comparable 接口：表示String 是可以比大小的。
 * 3.String 内部定义了 final cha[] value 用于存储字符串数据，即String 底层都是以cha[] 型数组存储的。
 * 4.String 代表不可变的字符序列。简称：不可变性
 *       体现1：当对字符串重新复制时，需要重写指定内存区域赋值，不能使用原有的value 上赋值。
 *       体现2：当对字符串进行连接操作时，也需要重新指定内存区域赋值，不能再原有value上赋值。
 * */

public class StringTest1 {
    public static void main(String[] args) {
        String s1 = "abc";//通过字面量方式定义字符串
        String s2 = "abc";

        s1 = "hello";
        System.out.println(s1 == s2);//比较s1 和s2 的地址值
        System.out.println(s1);//hello
        System.out.println(s2);//abc

        System.out.println("*************");
        String s3 = "abc";
        s3 += "def";
        System.out.println(s3);//abcdef

        System.out.println("*************");
        String s4 = "abc";
        String s5 = s4.replace('a', 'm');
        System.out.println(s5);
    }
}
