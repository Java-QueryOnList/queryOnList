package testHelpers.genericClasses.classTestCases.shape;

import testHelpers.genericClasses.classDecleration.shape.Shape;
import testHelpers.genericClasses.classObjects.shape.ShapeObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;

public final class ShapeTestCases {
    private static final List<Shape> rawList01 = ShapeObjects.getRawList();
    public static final PreparedCase<Shape> filterSuperClassField;
    public static final PreparedCase<Shape> OrderBySuperClassField;
    public static final PreparedCase<Shape> filterAndOrderByEmptyQuery;
    public static final PreparedCase<Shape> emptyQuery;

    static {
        filterSuperClassField = createCase01();
        OrderBySuperClassField = createCase02();
        filterAndOrderByEmptyQuery = createCase03();
        emptyQuery = createCase04();
    }

    private static PreparedCase<Shape> createCase01() {
        // Create query
        String query = "$filter=color eq 'Blue'";

        // prepare the List which is expected after the query
        List<Shape> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(10));
        expectedList.add(rawList01.get(19));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Shape> createCase02() {
        // Create query
        String query = "$orderBy=color";

        // prepare the List which is expected after the query
        List<Shape> expectedList = new ArrayList<>();
        // Black
        expectedList.add(rawList01.get(7));
        expectedList.add(rawList01.get(26));

        // Blue
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(10));
        expectedList.add(rawList01.get(19));

        // Brown
        expectedList.add(rawList01.get(16));

        // Gray
        expectedList.add(rawList01.get(17));
        expectedList.add(rawList01.get(25));

        // Green
        expectedList.add(rawList01.get(2));
        expectedList.add(rawList01.get(11));
        expectedList.add(rawList01.get(20));

        // Orange
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(13));
        expectedList.add(rawList01.get(22));

        // Pink
        expectedList.add(rawList01.get(6));
        expectedList.add(rawList01.get(15));
        expectedList.add(rawList01.get(24));

        // Purple
        expectedList.add(rawList01.get(5));
        expectedList.add(rawList01.get(14));
        expectedList.add(rawList01.get(23));

        // Red
        expectedList.add(rawList01.get(0));
        expectedList.add(rawList01.get(9));
        expectedList.add(rawList01.get(18));

        // White
        expectedList.add(rawList01.get(8));

        // Yellow
        expectedList.add(rawList01.get(3));
        expectedList.add(rawList01.get(12));
        expectedList.add(rawList01.get(21));


        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, true);
    }

    private static PreparedCase<Shape> createCase03() {
        // Create query
        String query = "$filter=$orderBy=";

        // prepare the List which is expected after the query
        List<Shape> expectedList = rawList01;

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Shape> createCase04() {
        // Create query
        String query = "";

        // prepare the List which is expected after the query
        List<Shape> expectedList = rawList01;

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }
}