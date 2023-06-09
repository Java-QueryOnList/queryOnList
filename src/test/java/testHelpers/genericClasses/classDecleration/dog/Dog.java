package testHelpers.genericClasses.classDecleration.dog;

import java.util.List;

public class Dog {
    private final String name;
    private final int age;
    private final List<String> favoriteToys;

    public Dog(String name, int age, List<String> favoriteToys) {
        this.name = name;
        this.age = age;
        this.favoriteToys = favoriteToys;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getFavoriteToys() {
        return favoriteToys;
    }
}