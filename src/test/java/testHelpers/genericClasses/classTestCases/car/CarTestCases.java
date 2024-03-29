package testHelpers.genericClasses.classTestCases.car;

import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classDecleration.car.Engine;
import testHelpers.genericClasses.classObjects.car.CarObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class CarTestCases {
    private static final List<Car> rawList01 = CarObjects.getRawList();
    public static final PreparedCase<Car> filterFieldExtraction;
    public static final PreparedCase<Car> filterTrivialOr;
    public static final PreparedCase<Car> filterTrivialNot;
    public static final PreparedCase<Car> filterNotThenBrackets;
    public static final PreparedCase<Car> filterNotNot;
    public static final PreparedCase<Car> orderByEnginetypeThenHorsepowerDesc;

    static {
        filterFieldExtraction = createCase01();
        filterTrivialOr = createCase02();
        filterTrivialNot = createCase03();
        filterNotThenBrackets = createCase04();
        filterNotNot = createCase05();
        orderByEnginetypeThenHorsepowerDesc = createCase06();
    }

    private static PreparedCase<Car> createCase01() {
        // Create query
        String query = "$filter=engine.horsepower eq 180";

        // prepare the List which is expected after the query
        List<Car> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Car> createCase02() {
        // Create query
        String query = "$filter=engine.type eq 'Diesel' or year gt 2020";

        // prepare the List which is expected after the query
        List<Car> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(2));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(5));
        expectedList.add(rawList01.get(6));
        expectedList.add(rawList01.get(8));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Car> createCase03() {
        // Create query
        String query = "$filter=not engine.type eq 'Gasoline'";

        // prepare the List which is expected after the query
        List<Car> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(2));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(6));
        expectedList.add(rawList01.get(8));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Car> createCase04() {
        // Create query
        String query = "$filter=not (engine.type eq 'Gasoline')";

        // prepare the List which is expected after the query
        List<Car> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(2));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(6));
        expectedList.add(rawList01.get(8));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Car> createCase05() {
        // Create query
        String query = "$filter=not not engine.type eq 'Gasoline'";

        // prepare the List which is expected after the query
        List<Car> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(0));
        expectedList.add(rawList01.get(3));
        expectedList.add(rawList01.get(5));
        expectedList.add(rawList01.get(7));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Car> createCase06() {
        // Create query
        String query = "$orderBy=engine.type, engine.horsepower desc";

        // prepare the List which is expected after the query
        List<Car> expectedList = new ArrayList<>();
        // Diesel
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(8));

        // Electric
        expectedList.add(rawList01.get(6));
        expectedList.add(rawList01.get(2));

        // Gasoline
        expectedList.add(rawList01.get(7));
        expectedList.add(rawList01.get(0));
        expectedList.add(rawList01.get(5));
        expectedList.add(rawList01.get(3));

        // Prepare getters for order check
        Function<Car, Engine> getEngine = Car::getEngine;
        Function<Engine, String> getType = Engine::getType;
        Function<Engine, Integer> getHorsepower = Engine::getHorsepower;
        Function<Car, String> typeGetter = getEngine.andThen(getType);
        Function<Car, Integer> horsepowerGetter = getEngine.andThen(getHorsepower);
        List<Function<Car, ?>> gettersForOrderBy = List.of(typeGetter, horsepowerGetter);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }
}
