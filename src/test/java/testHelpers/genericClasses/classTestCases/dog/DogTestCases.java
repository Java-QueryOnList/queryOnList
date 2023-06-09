package testHelpers.genericClasses.classTestCases.dog;

import testHelpers.genericClasses.classDecleration.dog.Dog;
import testHelpers.genericClasses.classObjects.dog.DogObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;

public final class DogTestCases {
    private static final List<Dog> rawList01 = DogObjects.getRawList();
    public static final PreparedCase<Dog> filterTrivialEqualString;
    public static final PreparedCase<Dog> filterTrivialListField;
    public static final PreparedCase<Dog> orderByNameDesc;

    static {
        filterTrivialEqualString = createCase01();
        filterTrivialListField = createCase02();
        orderByNameDesc = createCase03();
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

    private static PreparedCase<Dog> createCase02() {
        // Create query
        String query = "$filter=favoriteToys eq 'Toy1'";

        // prepare the List which is expected after the query
        List<Dog> expectedList = rawList01; // will be full list because every element has 'Toy1'

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Dog> createCase03() {
        // Create query
        String query = "$orderBy=name desc";

        // prepare the List which is expected after the query
        List<Dog> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(8));
        expectedList.add(rawList01.get(7));
        expectedList.add(rawList01.get(6));
        expectedList.add(rawList01.get(5));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(3));
        expectedList.add(rawList01.get(29));
        expectedList.add(rawList01.get(2));
        expectedList.add(rawList01.get(28));
        expectedList.add(rawList01.get(27));
        expectedList.add(rawList01.get(26));
        expectedList.add(rawList01.get(25));
        expectedList.add(rawList01.get(24));
        expectedList.add(rawList01.get(23));
        expectedList.add(rawList01.get(22));
        expectedList.add(rawList01.get(21));
        expectedList.add(rawList01.get(20));
        expectedList.add(rawList01.get(19));
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(18));
        expectedList.add(rawList01.get(17));
        expectedList.add(rawList01.get(16));
        expectedList.add(rawList01.get(15));
        expectedList.add(rawList01.get(14));
        expectedList.add(rawList01.get(13));
        expectedList.add(rawList01.get(12));
        expectedList.add(rawList01.get(11));
        expectedList.add(rawList01.get(10));
        expectedList.add(rawList01.get(9));
        expectedList.add(rawList01.get(0));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, true);
    }
}