package org.queryongenericlist;

import org.junit.jupiter.api.Test;
import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classDecleration.dog.Dog;
import testHelpers.genericClasses.classDecleration.shape.Circle;
import testHelpers.genericClasses.classDecleration.shape.Shape;
import testHelpers.genericClasses.classDecleration.shoppingList.ShoppingList;
import testHelpers.genericClasses.classTestCases.CaseTester;
import testHelpers.genericClasses.classTestCases.car.CarTestCases;
import testHelpers.genericClasses.classTestCases.dog.DogTestCases;
import testHelpers.genericClasses.classTestCases.shape.CircleTestCases;
import testHelpers.genericClasses.classTestCases.shape.ShapeTestCases;
import testHelpers.genericClasses.classTestCases.shoppingList.ShoppingListTestCases;

public class GenericClassTests {

    @Test
    public void testOnCarClass() {
        CaseTester<Car> carCaseTester = new CaseTester<>();

        carCaseTester
                .addCase(CarTestCases.caseFieldExtraction)
                .addCase(CarTestCases.caseTrivialOr)
                .addCase(CarTestCases.caseTrivialNot)
                .addCase(CarTestCases.caseNotThenBrackets)
                .addCase(CarTestCases.caseNotNot) // Add as many cases as you like
                .testCases();
    }

    @Test
    public void testOnDogClass() {
        CaseTester<Dog> dogCaseTester = new CaseTester<>();

        dogCaseTester
                .addCase(DogTestCases.caseTrivialEqualString)
                .addCase(DogTestCases.caseTrivialListField)
                .testCases();
    }

    @Test
    public void testOnShapeClass() {
        CaseTester<Shape> shapeCaseTester = new CaseTester<>();

        shapeCaseTester
                .addCase(ShapeTestCases.caseSuperClassField)
                .testCases();
    }

    @Test
    public void testOnCircleClass() {
        CaseTester<Circle> circleCaseTester = new CaseTester<>();

        circleCaseTester
                .addCase(CircleTestCases.caseComplexQueryPrecedence)
                .testCases();
    }

    @Test
    public void testOnShoppingListClass() {
        CaseTester<ShoppingList> shoppingListCaseTester = new CaseTester<>();

        shoppingListCaseTester
                .addCase(ShoppingListTestCases.caseTrivialBoolean)
                .addCase(ShoppingListTestCases.caseExtractFieldInList)
                .testCases();
    }
}