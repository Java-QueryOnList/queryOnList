package org.queryongenericlist.query.queryengine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.queryongenericlist.query.queryexecutor.implementation.SuperQueryExecutor;
import org.queryongenericlist.query.result.PaginatedResult;
import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classObjects.car.CarObjects;

import java.util.List;

public class SuperQueryEngineTest {

    @Test
    public void testExecuteForPaginatedResult() {
        // prepare data
        SuperQueryExecutor superQueryExecutor = new SuperQueryExecutor();
        String query = "$filter=engine.horsepower gt 50&$skip=4&$top=2";
        String baseUrl = "https://example.com/cars";
        List<Car> onList = CarObjects.getRawList();
        PaginatedResult<Car> paginatedResult = superQueryExecutor.executeForPaginatedResult(query, onList, baseUrl);

        // actual results
        PaginatedResult<Car> actualNextPageResult = paginatedResult.getNextPageResult(onList);
        String nextQuery = "$filter=engine.horsepower gt 50&$skip=6&$top=2";
        PaginatedResult<Car> expectedNextPageResult = superQueryExecutor.executeForPaginatedResult(nextQuery, onList, baseUrl);

        // Testing results
        Assertions.assertEquals(expectedNextPageResult.getResult(), actualNextPageResult.getResult());
        Assertions.assertEquals(expectedNextPageResult.getPaginationQuery(), actualNextPageResult.getPaginationQuery());
        Assertions.assertEquals(expectedNextPageResult.getCurrentSkip(), actualNextPageResult.getCurrentSkip());
    }
}
