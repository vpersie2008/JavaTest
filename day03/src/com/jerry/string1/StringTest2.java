package com.jerry.string1;

/*
* 字符串对象是如何存储的？
*
* */

public class StringTest2 {
    public static void main(String[] args) {

        //通过字面量方式声明字符串
        String s1 = "javaEE";
        String s2 = "javaEE";

        //通过new对象方式声明
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");
        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//false
        System.out.println(s1 == s4);//false
        System.out.println(s3 == s4);//false
    }
}


