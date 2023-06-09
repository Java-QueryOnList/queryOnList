package testHelpers.genericClasses.classDecleration.car;

public class Car {
    final private String brand;
    final private int year;
    final private Engine engine;

    public Car(String brand, int year, Engine engine) {
        this.brand = brand;
        this.year = year;
        this.engine = engine;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public Engine getEngine() {
        return engine;
    }
}

