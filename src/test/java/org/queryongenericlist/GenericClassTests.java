package org.queryongenericlist;

import org.junit.jupiter.api.Test;
import testHelpers.genericClasses.classDecleration.car.Car;
import testHelpers.genericClasses.classTestCases.CaseTester;
import testHelpers.genericClasses.classTestCases.car.CarTestCase;

public class GenericClassTests {

    @Test
    public void testOnCarClass() {
        CaseTester<Car> carCaseTester = new CaseTester<>();

        carCaseTester
                .addCase(CarTestCase.case01())
                .executeCases();
    }
}