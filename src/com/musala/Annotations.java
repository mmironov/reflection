package com.musala;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Annotations {

    public static void main(String[] args) {

        Simulator.runSimulations("SimulationSet");
    }
}

class Simulator {

    public static void runSimulations(String set) {
        try {
            Class<?> simulationsClass = Class.forName("com.musala." + set);

            Method[] methods = simulationsClass.getMethods();

            Arrays.asList(methods).forEach(method -> {

                if (method.isAnnotationPresent(Simulation.class)) {
                    try {
                        method.invoke(null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class SimulationSet {

    public static void setUp() {
        System.out.println("Setting up....");
    }

    @Simulation
    public static void shortSimulation() {
        System.out.println("Running short simulation...");
    }

    @Simulation
    public static void longSimulation() {
        System.out.println("Running long simulation...");
    }
}