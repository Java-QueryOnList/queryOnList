package testHelpers.genericClasses.classObjects.dog;

import testHelpers.genericClasses.classDecleration.dog.Dog;

import java.util.ArrayList;
import java.util.List;

public final class DogObjects {
    private static final List<Dog> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<Dog> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
        // Create 30 Dog objects
        for (int i = 0; i < 30; i++) {
            String name = "Dog" + (i + 1);
            int age = i + 1;
            List<String> favoriteToys = new ArrayList<>();
            favoriteToys.add("Toy1");
            favoriteToys.add("Toy2");
            favoriteToys.add("Toy3");

            Dog dog = new Dog(name, age, favoriteToys);
            RAW_LIST.add(dog);
        }
    }
}
