package com.musala;

import java.lang.reflect.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

//        classNames();
//        packages();
//        superClass();
//        creatingAnObject();
//        gettingModifiers();
//        implementedInterfaces();
//        gettingConstructors();
//        gettingFields();
//        gettingMethods();
//        creatingInstances();
//        gettingFieldValues();
    }

    private static void classNames() {

        Object person = new Person();

        Class<?> clazz = person.getClass();

        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getName());
        System.out.println(clazz.getCanonicalName());
    }

    private static void packages() {
        Object person = new Person();

        Class<?> clazz = person.getClass();

        final Package classPackage = clazz.getPackage();
        System.out.println(classPackage);
    }

    private static void superClass() {
        Object person = new Person();

        Class<?> clazz = person.getClass();
        Class<?> superClass = clazz.getSuperclass();

        System.out.println("Super class: " + superClass);
    }

    private static void creatingAnObject() {

        try {
            Class<?> clazz = Class.forName("com.musala.Person");

            System.out.println(clazz);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private static void implementedInterfaces() {
        try {
            Class<?> clazz = Class.forName("com.musala.Person");

            Class<?>[] interfaces = clazz.getInterfaces();

            System.out.println(Arrays.toString(interfaces));
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private static void gettingModifiers() {
        try {
            Class<?> clazz = Class.forName("com.musala.Person");

            final int mods = clazz.getModifiers();

            System.out.println("Is public: " + Modifier.isPublic(mods));
            System.out.println("Is abstract: " + Modifier.isAbstract(mods));
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private static void gettingConstructors() {
        try {
            Class<?> clazz = Class.forName("com.musala.Person");

            Constructor<?>[] constructors = clazz.getConstructors();

            System.out.println(Arrays.toString(constructors));

            if (constructors.length > 0) {
                final int count = constructors[0].getParameterCount();
                final Class<?>[] types = constructors[0].getParameterTypes();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private static void gettingFields() {
        try {
            Class<?> clazz = Class.forName("com.musala.Person");

            Field[] fields = clazz.getDeclaredFields();

            System.out.println(Arrays.toString(fields));
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private static void gettingMethods() {
        try {
            Class<?> clazz = Class.forName("com.musala.Person");

            Method[] methods = clazz.getMethods();

            Arrays.asList(methods).forEach(method -> {
                System.out.println(method);

                if ("func".equals(method.getName())) {
                    try {
                        System.out.println(method.invoke(null, 5));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private static void creatingInstances() {
        try {
            Class<?> clazz = Class.forName("com.musala.Person");

            try {
                Constructor<?> constructor = clazz.getConstructor(String.class);

                Person person = (Person) constructor.newInstance("Martin");

                System.out.println(person);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    private static void gettingFieldValues() {
        try {
            Class<?> clazz = Class.forName("com.musala.Person");
            Class<?> clazz2 = Class.forName("com.musala.Person");
            Person person = new Person("Harry");
            person.setAge(20);

            Field[] fields = clazz.getDeclaredFields();

            Arrays.asList(fields).forEach(field -> {
                if (field.getType().getSimpleName().equals(int.class.getSimpleName())) {
                    try {
                        field.setAccessible(true);
                        int value = (int) field.get(person);
                        System.out.println("int value: " + value);
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}

