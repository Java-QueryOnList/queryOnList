package testHelpers.genericClasses.classObjects.car;

import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classDecleration.car.Engine;

import java.util.ArrayList;
import java.util.List;

public class CarObjects {
    public static final List<Car> TEST_OBJECTS = new ArrayList<>();

    static {
        prepTestCase01();
    }

    private static void prepTestCase01() {
        Engine engine1 = new Engine("Gasoline", 200);
        Car car1 = new Car("Toyota", 2020, engine1);

        Engine engine2 = new Engine("Diesel", 180);
        Car car2 = new Car("Ford", 2018, engine2);

        Engine engine3 = new Engine("Electric", 250);
        Car car3 = new Car("Tesla", 2022, engine3);

        Engine engine4 = new Engine("Gasoline", 150);
        Car car4 = new Car("Honda", 2019, engine4);

        Engine engine5 = new Engine("Diesel", 190);
        Car car5 = new Car("Volkswagen", 2017, engine5);

        Engine engine6 = new Engine("Gasoline", 170);
        Car car6 = new Car("Chevrolet", 2021, engine6);

        Engine engine7 = new Engine("Electric", 300);
        Car car7 = new Car("Nissan", 2023, engine7);

        Engine engine8 = new Engine("Gasoline", 220);
        Car car8 = new Car("BMW", 2016, engine8);

        Engine engine9 = new Engine("Diesel", 160);
        Car car9 = new Car("Mercedes-Benz", 2020, engine9);

        TEST_OBJECTS.add(car1);
        TEST_OBJECTS.add(car2);
        TEST_OBJECTS.add(car3);
        TEST_OBJECTS.add(car4);
        TEST_OBJECTS.add(car5);
        TEST_OBJECTS.add(car6);
        TEST_OBJECTS.add(car7);
        TEST_OBJECTS.add(car8);
        TEST_OBJECTS.add(car9);
    }
}