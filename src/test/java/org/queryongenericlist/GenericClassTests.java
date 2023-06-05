package org.queryongenericlist;

import org.junit.jupiter.api.Test;
import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classDecleration.dog.Dog;
import testHelpers.genericClasses.classDecleration.shape.Shape;
import testHelpers.genericClasses.classDecleration.shoppingList.ShoppingList;
import testHelpers.genericClasses.classTestCases.CaseTester;
import testHelpers.genericClasses.classTestCases.car.CarTestCases;
import testHelpers.genericClasses.classTestCases.dog.DogTestCases;
import testHelpers.genericClasses.classTestCases.shape.ShapeTestCases;
import testHelpers.genericClasses.classTestCases.shoppingList.ShoppingListTestCases;

public class GenericClassTests {

    @Test
    public void testOnCarClass() {
        CaseTester<Car> carCaseTester = new CaseTester<>();

        carCaseTester
                .addCase(CarTestCases.case01)
                .addCase(CarTestCases.case02)
                //.addCase(CarTestCases.case03) // add test cases as you wish
                .testCases();
    }

    @Test
    public void testOnDogClass() {
        CaseTester<Dog> dogCaseTester = new CaseTester<>();

        dogCaseTester
                .addCase(DogTestCases.case01)
                .testCases();
    }

    @Test
    public void testOnShapeClass() {
        CaseTester<Shape> shapeCaseTester = new CaseTester<>();

        shapeCaseTester
                .addCase(ShapeTestCases.case01)
                .testCases();
    }

    @Test
    public void testOnShoppingListClass() {
        CaseTester<ShoppingList> shoppingListCaseTester = new CaseTester<>();

        shoppingListCaseTester
                .addCase(ShoppingListTestCases.case01)
                .testCases();
    }
}