package org.queryongenericlist.query.result;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.queryongenericlist.query.queryexecutor.implementation.SuperQueryExecutor;
import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classObjects.car.CarObjects;

import java.util.List;

public class PaginatedResultTest {

    @Test
    public void testCurrentPageNumber() {
        // prepare data
        SuperQueryExecutor superQueryExecutor = new SuperQueryExecutor();
        String query = "$filter=engine.horsepower gt 50&$skip=4&$top=2";
        String baseUrl = "https://example.com/cars";
        List<Car> onList = CarObjects.getRawList();
        PaginatedResult<Car> paginatedResult = superQueryExecutor.executeForPaginatedResult(query, onList, baseUrl);

        // actual results
        int actualCurrentPageNumber = paginatedResult.getCurrentPageNumber();

        // Testing results
        Assertions.assertEquals(3, actualCurrentPageNumber);
    }

    @Test
    public void testNextPageUrl() {
        // prepare data
        SuperQueryExecutor superQueryExecutor = new SuperQueryExecutor();
        String query = "$filter=engine.horsepower gt 50&$skip=4&$top=2";
        String baseUrl = "https://example.com/cars";
        List<Car> onList = CarObjects.getRawList();
        PaginatedResult<Car> paginatedResult = superQueryExecutor.executeForPaginatedResult(query, onList, baseUrl);

        // actual results
        String actualNextPageUrl = paginatedResult.getNextPageUrl().orElse(null);

        // testing results
        Assertions.assertEquals("https://example.com/cars?$filter=engine.horsepower gt 50&$skip=6&$top=2", actualNextPageUrl);
    }

    @Test
    public void testLastPageUrl() {
        // prepare data
        SuperQueryExecutor superQueryExecutor = new SuperQueryExecutor();
        String query = "$filter=engine.horsepower gt 50&$skip=4&$top=1000";
        String baseUrl = "https://example.com/cars";
        List<Car> onList = CarObjects.getRawList();
        PaginatedResult<Car> paginatedResult = superQueryExecutor.executeForPaginatedResult(query, onList, baseUrl);

        // actual results
        String actualNextPageUrl = paginatedResult.getNextPageUrl().orElse(null);

        // Testing results
        Assertions.assertNull(actualNextPageUrl); // because there is no next page when top=1000
    }
}
