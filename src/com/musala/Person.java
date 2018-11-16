package com.musala;

public class Person {

    private String name;
    private int age;

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s, %d", name, age);
    }

    public static String func(int x) {
        return "hello";
    }
}
