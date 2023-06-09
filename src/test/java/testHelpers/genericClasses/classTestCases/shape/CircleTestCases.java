package testHelpers.genericClasses.classTestCases.shape;

import testHelpers.genericClasses.classDecleration.shape.Circle;
import testHelpers.genericClasses.classObjects.shape.CircleObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;

public final class CircleTestCases {
    private static final List<Circle> rawList01 = CircleObjects.getRawList();
    public static final PreparedCase<Circle> caseComplexQueryPrecedence;

    static {
        caseComplexQueryPrecedence = createCase01();
    }

    private static PreparedCase<Circle> createCase01() {
        // Create query
        String query = "$filter=radius gt 5 or color eq 'Blue' and (radius le 5.00 or color eq 'Yellow')";

        // prepare the List which is expected after the query
        List<Circle> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(2));
        expectedList.add(rawList01.get(4));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }
}