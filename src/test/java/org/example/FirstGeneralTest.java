package org.example;

import testHelpers.genericClasses.classDecleration.shape.Circle;
import testHelpers.genericClasses.classObjects.shape.CircleObjects;
import org.example.query.queryEngine.implementation.QueryEngineImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FirstGeneralTest {

    @Test
    public void testRunEngine() {
        // Prepare data
        List<Circle> circles = CircleObjects.TEST_CASE_1;
        //String query = "$filter=radius gt 5 or color eq 'Blue' and (radius le 5.00 or color eq 'Yellow')";
        //List<Circle> expectedList = new ArrayList<>();
        //expectedList.add(circles.get(1));
        //expectedList.add(circles.get(2));
        //expectedList.add(circles.get(4));

        String query = "$filter=radius gt 5";
        List<Circle> expectedList = new ArrayList<>();
        expectedList.add(circles.get(2));
        expectedList.add(circles.get(4));

        // Query
        QueryEngineImpl engine = new QueryEngineImpl();
        List<Circle> queriedList = engine.run(query, circles);

        assertEquals(expectedList, queriedList);
    }
}