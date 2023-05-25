package testHelpers.genericClasses.classDecleration.dog;

import java.util.List;

public class Dog {
    private String name;
    private int age;
    private List<String> favoriteToys;

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