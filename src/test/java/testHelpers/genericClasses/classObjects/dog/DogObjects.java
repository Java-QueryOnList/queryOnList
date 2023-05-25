package testHelpers.genericClasses.classObjects.dog;

import testHelpers.genericClasses.classDecleration.dog.Dog;

import java.util.ArrayList;
import java.util.List;

public class DogObjects {
    public static final List<Dog> TEST_CASE_1 = new ArrayList<>();

    static {
        prepTestCase01();
    }

    private static void prepTestCase01() {
        // Create 30 Dog objects
        for (int i = 0; i < 30; i++) {
            String name = "Dog" + (i + 1);
            int age = i + 1;
            List<String> favoriteToys = new ArrayList<>();
            favoriteToys.add("Toy1");
            favoriteToys.add("Toy2");
            favoriteToys.add("Toy3");

            Dog dog = new Dog(name, age, favoriteToys);
            TEST_CASE_1.add(dog);
        }
    }
}
