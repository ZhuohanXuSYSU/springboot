package com.xu;

import java.util.Optional;

public record Student(Integer id, String name, String email, Integer age) {


    // 紧凑型构造方法
    public Student {
        System.out.println("id = " + id);
        if (id < 1) {
            throw new RuntimeException("id 小于 1");
        }
    }

    // 定制构造方法
    public Student(Integer id, String name) {
        this(id, name, null, null);
    }

    // 实例方法，拼接 name 和 age
    public String concat() {
        return String.format("name = %s, age = %d", this.name, this.age);
    }

    // 静态方法：返回字符串大写
    public static String emailToUpperCase(String email) {
        return Optional.ofNullable(email).orElse("no email").toUpperCase();
    }

}
