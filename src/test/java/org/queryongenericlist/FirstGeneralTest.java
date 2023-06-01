package org.queryongenericlist;

import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classObjects.car.CarObjects;
import org.queryongenericlist.query.queryEngine.implementation.QueryEngineImpl;
import org.junit.jupiter.api.Test;
import testHelpers.genericClasses.utils.LoggingUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstGeneralTest {

    @Test
    public void testRunEngine() {
        // Prepare data
        List<Car> cars = CarObjects.TEST_OBJECTS;
        String query = "$filter=engine.horsepower eq 180";
        List<Car> expectedList = new ArrayList<>();
        expectedList.add(cars.get(1));

        // Query
        QueryEngineImpl engine = new QueryEngineImpl();
        List<Car> queriedList = engine.run(query, cars);
        System.out.println("Expected");
        System.out.println(LoggingUtils.genericListToString(expectedList));
        System.out.println("Queried");
        System.out.println(LoggingUtils.genericListToString(queriedList));

        assertEquals(expectedList, queriedList);
    }
}