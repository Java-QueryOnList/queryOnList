package testHelpers.genericClasses.classTestCases.car;

import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classObjects.car.CarObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;

public final class CarTestCase {
    private static final List<Car> rawList01 = CarObjects.getRawList();

    public static PreparedCase<Car> case01() {
        // Create query
        String query = "$filter=engine.horsepower eq 180";
        // prepare the List which is expected after the query
        List<Car> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }
}
