package com.jerry.string1;

/*
 * 一、实现comparable 进行对象间的排序,
 * 可以通过让类实现Comparable 接口的方式
 * 二、可以使用Comparator 进行定制排序
 *
 * 二者之间的区别：
 * 1.Comparable 让被排序的类去实现接口，一劳永逸型。
 * 2.Comparator 需要的时候，再去指定排序，相当于临时性的比较。
 *
 * 三、重写CompareTo(obj) 的规则（默认排序规则就是从小打到，即升序）
 * 如果当前对象this 大于 形参对象obj，则返回正整数
 * 如果当前对象this 小于 形参对象obj，返回负整数
 * 如果当前对象this 等于 形参对象obj，则返回零。
 * */

import java.util.Arrays;
import java.util.Comparator;

public class StringTest4 {
    public static void main(String[] args) {
        Goods[] arrs = new Goods[5];
        arrs[0] = new Goods("华为", 34);
        arrs[1] = new Goods("小米", 10);
        arrs[2] = new Goods("中兴", 9.99);
        arrs[3] = new Goods("金立", 88);
        arrs[4] = new Goods("苹果", 58);

        //1.从小到大排列,使用实现Comparable接口方式
        Arrays.sort(arrs);
        System.out.println("从小到大排列顺序为: " + Arrays.toString(arrs));

        //1.从大到小排列,使用Comparator，指定排序方法
        Arrays.sort(arrs, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if (o1.getPrice() < o2.getPrice()) {
                    return 1;
                } else if (o1.getPrice() > o2.getPrice()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        System.out.println("从大到小排列顺序为: " + Arrays.toString(arrs));
    }
}

class Goods implements Comparable<Goods> {
    private String name;
    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

//按照升序排列
//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof Goods) {
//            Goods goods = (Goods) o;
//            if (this.price > goods.price) {
//                return 1;
//            } else if (this.price < goods.price) {
//                return -1;
//            } else {
//                return 0;
//            }
//        }
//
//        throw new RuntimeException("传入的数据类型不一致");
//    }

    //升序排列
    @Override
    public int compareTo(Goods goods) {
        if (this.price > goods.price) {
            return 1;
        } else if (this.price < goods.price) {
            return -1;
        } else {
            return 0;
        }
    }
}