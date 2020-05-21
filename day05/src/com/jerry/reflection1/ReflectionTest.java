package com.jerry.reflection1;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Random;

/*
 * 关于java.lang.Class 的理解
 * 1.类的加载过程：
 * 程序通过javac.exe  命令以后，会生成一个或多个字节码文件(.class结尾的文件)，
 * 接着我们使用java.exe命令，对某个字节码文件进行解释运行，相当于将某个字节码文件加载到内存中，此过程称为类的加载。
 * 加载到内存类中，我们就称为运行时类，此时运行时类，就作为Class 的一个实例。
 *
 * 简单来说：大Class 类其实就是对应运行时类的一个实例。
 * 体现出：万事万物皆对象
 *
 * */

public class ReflectionTest {

    //反射演示
    @Test
    public void test1() throws Exception {
        Class clazz = Person.class;
        //1.通过反射，创建Person 类的对象
        Constructor cons = clazz.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Tom", 22);
        Person p = (Person) obj;
        System.out.println(p.toString());

        //2.通过反射，调用对象指定的属性、方法
        //调用属性，这里可以调用和设置私有属性
        Field age = clazz.getDeclaredField("age");
        //通过反射设置当前实例Person的age属性
        age.setAccessible(true);
        age.set(p, 10);

        //3.调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        //4.调用私有方法,指定一个参数：
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);//调用私有方法，必须去指定它的访问权限，权限为true，才可以调用该方法
        showNation.invoke(p, "China");

        //5.调用方法，带返回值的
        Method getName = clazz.getDeclaredMethod("getName");
        String name1 = (String) getName.invoke(p);//这里需要强转下返回值
        System.out.println(name1);

        //6.调用静态方法
        Method showDesc = clazz.getDeclaredMethod("showDesc", String.class);
        showDesc.setAccessible(true);
        Object returnVal = showDesc.invoke(Person.class, "Jerry description");//这里是void方法，所以返回值是null
        //Object returnVal = showDesc.invoke(null,"Jerry description");//或者填null也是可以的
        System.out.println("Return value is: " + returnVal);
    }

    //获取Class 实例的实现方式
    @Test
    public void test2() throws Exception {
        //方式一：调用运行时的类的属性： .class
        Class clazz1 = Person.class;
        System.out.println(clazz1);

        //方式二：通过运行时类的对象，调用getClass()
        Person p2 = new Person();
        Class clazz2 = p2.getClass();
        System.out.println(clazz2);

        //方式三：调用Class 的静态方法，forName(String classPath)
        Class clazz3 = Class.forName("com.jerry.reflection1.Person");
        System.out.println(clazz3);

        //方式四：使用ClassLoader（作为了解）
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> clazz4 = classLoader.loadClass("com.jerry.reflection1.Person");
        System.out.println(clazz4);

        //四者创建的实例对象，指向同一个实例
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);
        System.out.println(clazz1 == clazz4);
    }

    //可以获取Class 实例的场景如下
    @Test
    public void test3() throws Exception {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();

        //只要元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }

    //反射通过文件获取内容
    @Test
    public void test4() throws Exception {
        Properties props = new Properties();
        //通过读取文件方式，获取配置文件中的内容，使用这种方式配置文件前面加src\\
        FileInputStream fis = new FileInputStream("src\\jdbc.properties");
        props.load(fis);

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        System.out.println("User: " + user + " Password: " + password);

        //通过类的加载器获取资源文件中的内容,配置文件是在当前文件目录下
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
        props.load(is);
        String user1 = props.getProperty("user");
        String password1 = props.getProperty("password");
        System.out.println("User1: " + user1 + " Password1: " + password1);
    }

    //创建运行时类的对象
    @Test
    public void test5() throws Exception {
        Class<Person> clazz = Person.class;
        //newInstance() 方法用于创建对应的运行时类的对象。这里会调用Person 类的空参数构造器,此时没有空参构造器，就会报错
        Person person = clazz.newInstance();
        System.out.println(person);

        //这里是新创建的对象，所以不相等
        Person person1 = clazz.newInstance();
        Person person2 = clazz.newInstance();
        System.out.println(person1 == person2);//false

        /*
         * 要想此方法正常的创建运行时类的对象，要求：
         * 1.运行时类必须提供空参的构造器
         * 2.空参的构造器访问权限得够，通常为public
         * 在javabean 中通常要求提供一个public 的空参构造器
         * 1.便于通过反射，创建运行时类的对象
         * 2.便于子类继承此运行类时，默认调用super() 时，保证父类有此构造器
         * */
    }

    //体会反射的动态性
    @Test
    public void test6() {

        for (int i = 0; i < 50; i++) {
            int num = new Random().nextInt(3);
            String classPath = "";
            switch (num) {
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "com.jerry.reflection1.Person";
                    break;
            }

            try {
                Object obj = getInstance(classPath);
                System.out.println(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object getInstance(String classPath) throws Exception {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }

}
