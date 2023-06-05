package testHelpers.genericClasses.classObjects.car;

import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classDecleration.car.Engine;

import java.util.ArrayList;
import java.util.List;

public final class CarObjects {
    private static final List<Car> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<Car> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
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

        RAW_LIST.add(car1);
        RAW_LIST.add(car2);
        RAW_LIST.add(car3);
        RAW_LIST.add(car4);
        RAW_LIST.add(car5);
        RAW_LIST.add(car6);
        RAW_LIST.add(car7);
        RAW_LIST.add(car8);
        RAW_LIST.add(car9);
    }
}