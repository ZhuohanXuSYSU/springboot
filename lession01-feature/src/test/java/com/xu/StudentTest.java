package com.xu;

import org.junit.Test;

public class StudentTest {
    @Test
    public void test01() {
        // 实例化 Record 对象
        Student student = new Student(1001, "Jack", "jack@sysu.edu.cn", 20);
        System.out.println(student);

        // public 访问器，获取 Record 对象的属性，注意：该对象的所有属性都是只读的
        Integer id = student.id();
        String name = student.name();

        System.out.println(id);
        System.out.println(name);

        //
        Student s2 = new Student(1001, "Amy", "jack@sysu.edu.cn", 20);
        System.out.println(student.equals(s2));
    }

    @Test
    public void test02() {
        Student student = new Student(1001, "Jack", "jack.qq.com", 20);

        String concat = student.concat();

        System.out.println(concat);
    }

    @Test
    public void test03() {
        String email = Student.emailToUpperCase("jack@qq.com");
        System.out.println(email);
    }

    @Test
    public void test04() {
        Student student = new Student(-1, "John");
    }
}