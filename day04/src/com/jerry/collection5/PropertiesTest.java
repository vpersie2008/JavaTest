package com.jerry.collection5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/*
* Properties用于读取配置文件
* */
public class PropertiesTest {

    public static void main(String[] args) {

        Properties props = new Properties();
        try {
            FileInputStream fs = new FileInputStream("jdbc.properties");

            try {
                props.load(fs);
                String name = props.getProperty("name");
                String password = props.getProperty("password");
                System.out.println("name is : " + name + " password : " + password);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
