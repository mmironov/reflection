package com.musala;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionAndGenerics {

    public static void main(String[] args) {

        withGenerics();
        withReflection();
    }

    private static void withGenerics() {

        final Person person = new Person();
        Decorator<Person> personDecorator = new Decorator<>(person);
        personDecorator.get().setAge(20);
    }

    private static void withReflection() {
        final ReflectDecorator personDecorator =
                new ReflectDecorator("Person");
        personDecorator.adjust("Age", 25);

        Person decorated = (Person) personDecorator.get();
        System.out.println(decorated);
    }
}

class Decorator<T> {

    private T number;

    public Decorator(T number) {
        this.number = number;
    }

    public T get() {
        return number;
    }

    public T decorate() {
        return number;
    }
};

class ReflectDecorator {

    private Object object;
    private String type;

    public ReflectDecorator(String type) {
        try {
            this.type = "com.musala." + type;
            object = Class.forName(this.type).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void adjust(String property, Object value) {

        final String methodName = "set" + property;

        try {
            final Method setMethod = Class.forName(type).getMethod(methodName, value.getClass());

            try {
                setMethod.invoke(object, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object get() {
        return object;
    }
}