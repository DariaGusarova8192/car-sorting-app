package collection;

import model.Car;

public class CarListTest {
    public static void main(String[] args) {
        runAllCarListTests();
    }
    private static void runAllCarListTests() {
        defaultConstructorTest();
        constructorWithCapacityTest();
        copyConstructorTest();
        getTest();
        addTest();
        getSizeTest();
        addByIndexTest();
        removeTest();
        setTest();
        clearTest();
        System.out.println("ALL CARLIST TESTS PASSED");
    }
    private static void defaultConstructorTest() {
        CarList list = new CarList();
        if (list.size() != 0) {
            throw new RuntimeException("DEFAULT CONSTRUCTOR FAILED");
        }
        System.out.println("DEFAULT CONSTRUCTOR PASSED");
    }
    private static void constructorWithCapacityTest() {
        CarList list = new CarList(5);
        list.add(new Car(1,"A",1));
        list.add(new Car(2,"B",2));
        if (list.size() != 2) {
            throw new RuntimeException("CONSTRUCTOR THIS CAPACITY FAILED");
        }
        System.out.println("CONSTRUCTOR THIS CAPACITY PASSED");
    }
    private static void copyConstructorTest() {
        CarList list = new CarList(5);
        list.add(new Car(1,"A",1));
        list.add(new Car(2,"B",2));
        String expected = "[Car{power=1, model='A', year=1}, Car{power=2, model='B', year=2}]";
        String actual = new CarList(list).toString();
        if (!expected.equals(actual)) {
            throw new RuntimeException("TEST FAILED: expected " + expected + " but got " + actual);
        }
        System.out.println("COPY CONSTRUCTOR TEST PASSED");

    }
    private static void getTest() {
        CarList list = new CarList();
        list.add(new Car(1,"A",1));
        list.add(new Car(2, "B",2));
        String expected = "Car{power=2, model='B', year=2}";
        String actual = list.get(1).toString();
        if (!expected.equals(actual)) {
            throw new RuntimeException("TEST FAILED: expected " + expected + " but got " + actual);
        }
        System.out.println("GET TEST PASSED");
    }
    private static void getSizeTest() {
        CarList list = new CarList();
        list.add(new Car(1,"A",1));
        list.add(new Car(2, "B",2));
        int expected = 2;
        int actual = list.size();
        if(expected!=actual) {
            throw new RuntimeException("TEST FAILED: expected " + expected + " but got " + actual);
        }
        System.out.println("SIZE TEST PASSED");
    }
    private static void addTest() {
        CarList list = new CarList();
        list.add(new Car(1,"A",1));
        list.add(new Car(2, "B",2));
        String expected = "[Car{power=1, model='A', year=1}, Car{power=2, model='B', year=2}]";
        String actual = list.toString();
        if (!expected.equals(actual)) {
            throw new RuntimeException("TEST FAILED: expected " + expected + " but got " + actual);
        }
        System.out.println("ADD TEST PASSED");
    }
    private static void addByIndexTest() {
        CarList list = new CarList();
        list.add(new Car(1,"A",1));
        list.add(new Car(2, "B",2));
        list.add(1, new Car(3, "C",3));
        String expected = "[Car{power=1, model='A', year=1}, Car{power=3, model='C', year=3}, Car{power=2, model='B', year=2}]";
        String actual = list.toString();
        if (!expected.equals(actual)) {
            throw new RuntimeException("TEST FAILED: expected " + expected + " but got " + actual);
        }
        System.out.println("ADD BY INDEX TEST PASSED");
    }
    private static void removeTest() {
        CarList list = new CarList();
        list.add(new Car(1,"A",1));
        list.add(new Car(2, "B",2));
        list.remove(1);
        String expected = "[Car{power=1, model='A', year=1}]";
        String actual = list.toString();
        if (!expected.equals(actual)) {
            throw new RuntimeException("TEST FAILED: expected " + expected + " but got " + actual);
        }
        System.out.println("REMOVE TEST PASSED");
    }
    private static void setTest() {
        CarList list = new CarList();
        list.add(new Car(1,"A",1));
        list.add(new Car(2,"B",2));
        list.set(1, new Car(3,"C",3));
        String expected = "[Car{power=1, model='A', year=1}, Car{power=3, model='C', year=3}]";
        String actual = list.toString();
        if (!expected.equals(actual)) {
            throw new RuntimeException("SET TEST FAILED");
        }
        System.out.println("SET TEST PASSED");
    }
    private static void clearTest() {
        CarList list = new CarList();
        list.add(new Car(1,"A",1));
        list.add(new Car(2, "B",2));
        list.clear();
        String expected = "[]";
        String actual = list.toString();
        if (!expected.equals(actual)) {
            throw new RuntimeException("TEST FAILED: expected " + expected + " but got " + actual);
        }
        System.out.println("CLEAR TEST PASSED");
    }
}
