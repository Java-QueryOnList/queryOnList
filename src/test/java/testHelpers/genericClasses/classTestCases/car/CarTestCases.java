package testHelpers.genericClasses.classTestCases.car;

import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classObjects.car.CarObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;

public final class CarTestCases {
    private static final List<Car> rawList01 = CarObjects.getRawList();
    public static final PreparedCase<Car> case01;
    public static final PreparedCase<Car> case02;
    public static final PreparedCase<Car> case03;
    public static final PreparedCase<Car> case04;
    public static final PreparedCase<Car> case05;

    static {
        case01 = createCase01();
        case02 = createCase02();
        case03 = createCase03();
        case04 = createCase04();
        case05 = createCase05();
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
}
