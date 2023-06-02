package testHelpers.genericClasses.classTestCases.dog;

import testHelpers.genericClasses.classDecleration.dog.Dog;
import testHelpers.genericClasses.classObjects.dog.DogObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;

public final class DogTestCases {
    private static final List<Dog> rawList01 = DogObjects.getRawList();
    public static final PreparedCase<Dog> case01;

    static {
        case01 = createCase01();
    }

    private static PreparedCase<Dog> createCase01() {
        // Create query
        String query = "$filter=name eq 'Dog23'";

        // prepare the List which is expected after the query
        List<Dog> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(22));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }
}