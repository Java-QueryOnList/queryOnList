package org.queryongenericlist;

import org.junit.jupiter.api.Test;
import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classDecleration.datatypeholder.DataTypeHolder;
import testHelpers.genericClasses.classDecleration.developer.Developer;
import testHelpers.genericClasses.classDecleration.dog.Dog;
import testHelpers.genericClasses.classDecleration.pokemon.PokemonTrainer;
import testHelpers.genericClasses.classDecleration.shape.Circle;
import testHelpers.genericClasses.classDecleration.shape.Shape;
import testHelpers.genericClasses.classDecleration.shoppingList.ShoppingList;
import testHelpers.genericClasses.classTestCases.CaseTester;
import testHelpers.genericClasses.classTestCases.car.CarTestCases;
import testHelpers.genericClasses.classTestCases.datatypeholder.DataTypeHolderTestCases;
import testHelpers.genericClasses.classTestCases.developer.DeveloperTestCases;
import testHelpers.genericClasses.classTestCases.dog.DogTestCases;
import testHelpers.genericClasses.classTestCases.pokemon.PokemonTrainerTestCases;
import testHelpers.genericClasses.classTestCases.shape.CircleTestCases;
import testHelpers.genericClasses.classTestCases.shape.ShapeTestCases;
import testHelpers.genericClasses.classTestCases.shoppingList.ShoppingListTestCases;

public class GenericClassTests {

    @Test
    public void testOnCarClass() {
        CaseTester<Car> carCaseTester = new CaseTester<>();

        carCaseTester
                .addCase(CarTestCases.filterFieldExtraction) // Add as many cases as you like
                .addCase(CarTestCases.filterTrivialOr)
                .addCase(CarTestCases.filterTrivialNot)
                .addCase(CarTestCases.filterNotThenBrackets)
                .addCase(CarTestCases.filterNotNot)
                .addCase(CarTestCases.orderByEnginetypeThenHorsepowerDesc)
                .testCases();
    }

    @Test
    public void testOnDogClass() {
        CaseTester<Dog> dogCaseTester = new CaseTester<>();

        dogCaseTester
                .addCase(DogTestCases.filterTrivialEqualString)
                .addCase(DogTestCases.filterTrivialListField)
                .addCase(DogTestCases.orderByNameDesc)
                .addCase(DogTestCases.pagination10skip10top)
                .testCases();
    }

    @Test
    public void testOnShapeClass() {
        CaseTester<Shape> shapeCaseTester = new CaseTester<>();

        shapeCaseTester
                .addCase(ShapeTestCases.filterSuperClassField)
                .addCase(ShapeTestCases.OrderBySuperClassField)
                .addCase(ShapeTestCases.filterAndOrderByEmptyQuery)
                .addCase(ShapeTestCases.emptyQuery)
                .testCases();
    }

    @Test
    public void testOnCircleClass() {
        CaseTester<Circle> circleCaseTester = new CaseTester<>();

        circleCaseTester
                .addCase(CircleTestCases.filterComplexQueryPrecedence)
                .addCase(CircleTestCases.orderByRadius)
                .addCase(CircleTestCases.filterAndOrderByComplexQuery)
                .addCase(CircleTestCases.orderByAndFilterComplexQuery)
                .addCase(CircleTestCases.filterAndOrderByComplexFullURL)
                .testCases();
    }

    @Test
    public void testOnShoppingListClass() {
        CaseTester<ShoppingList> shoppingListCaseTester = new CaseTester<>();

        shoppingListCaseTester
                .addCase(ShoppingListTestCases.filterTrivialBoolean)
                .addCase(ShoppingListTestCases.filterExtractFieldInList)
                .testCases();
    }

    @Test
    public void testOnPokemonTrainerClass() {
        CaseTester<PokemonTrainer> pokemonTrainerCaseTester = new CaseTester<>();

        pokemonTrainerCaseTester
                .addCase(PokemonTrainerTestCases.filterOnIntArray)
                .addCase(PokemonTrainerTestCases.orderByOnIntArray)
                .addCase(PokemonTrainerTestCases.filterOnObjectArray)
                .addCase(PokemonTrainerTestCases.orderByOnObjectArray)
                .testCases();
    }

    @Test
    public void testOnDeveloperClass() {
        CaseTester<Developer> developerCaseTester = new CaseTester<>();

        developerCaseTester
                .addCase(DeveloperTestCases.filterWithNullAgeBetween30And40)
                .addCase(DeveloperTestCases.filterWithNullOnSubclass)
                .addCase(DeveloperTestCases.orderByLanguagesWithNullOnList)
                .addCase(DeveloperTestCases.orderByLanguagesThenProjects)
                .addCase(DeveloperTestCases.filterRoleEnum)
                .testCases();
    }

    @Test
    public void testOnDataTypeHolderClass() {
        CaseTester<DataTypeHolder> dataTypeHolderCaseTester = new CaseTester<>();

        dataTypeHolderCaseTester
                // usual data types:
                .addCase(DataTypeHolderTestCases.filterBooleanField)
                .addCase(DataTypeHolderTestCases.filterStringField)
                .addCase(DataTypeHolderTestCases.orderByIntegerFieldDesc)
                // not so usual data types:
                .addCase(DataTypeHolderTestCases.filterCharField)
                .testCases();
    }
}