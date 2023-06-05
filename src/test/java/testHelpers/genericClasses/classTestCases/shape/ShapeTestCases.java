package testHelpers.genericClasses.classTestCases.shape;

import testHelpers.genericClasses.classDecleration.shape.Shape;
import testHelpers.genericClasses.classObjects.shape.ShapeObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;

public final class ShapeTestCases {
    private static final List<Shape> rawList01 = ShapeObjects.getRawList();
    public static final PreparedCase<Shape> case01;

    static {
        case01 = createCase01();
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
}