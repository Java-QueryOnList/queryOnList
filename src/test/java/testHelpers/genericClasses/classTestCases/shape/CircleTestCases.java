package testHelpers.genericClasses.classTestCases.shape;

import org.queryongenericlist.query.queryexecutor.implementation.SuperQueryExecutor;
import org.queryongenericlist.utils.stringparser.applied.UrlParser;
import testHelpers.genericClasses.classDecleration.shape.Circle;
import testHelpers.genericClasses.classObjects.shape.CircleObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class CircleTestCases {
    private static final List<Circle> rawList01 = CircleObjects.getRawList();
    public static final PreparedCase<Circle> filterComplexQueryPrecedence;
    public static final PreparedCase<Circle> orderByRadius;
    public static final PreparedCase<Circle> filterAndOrderByComplexQuery;
    public static final PreparedCase<Circle> orderByAndFilterComplexQuery;
    public static final PreparedCase<Circle> filterAndOrderByComplexFullURL;

    static {
        filterComplexQueryPrecedence = createCase01();
        orderByRadius = createCase02();
        filterAndOrderByComplexQuery = createCase03();
        orderByAndFilterComplexQuery = createCase04();
        filterAndOrderByComplexFullURL = createCase05();
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

    private static PreparedCase<Circle> createCase02() {
        // Create query
        String query = "$orderBy=radius";

        // prepare the List which is expected after the query
        List<Circle> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(6));
        expectedList.add(rawList01.get(8));
        expectedList.add(rawList01.get(3));
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(7));
        expectedList.add(rawList01.get(5));
        expectedList.add(rawList01.get(0));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(2));

        // Prepare getters for order check
        List<Function<Circle, ?>> gettersForOrderBy = List.of(Circle::getRadius);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }

    private static PreparedCase<Circle> createCase03() {
        // Create query
        String query = "$filter=radius gt 5 or color eq 'Blue' and (radius le 5.00 or color eq 'Yellow')&$orderBy=radius";

        // prepare the List which is expected after the query
        List<Circle> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(2));

        // Prepare getters for order check
        List<Function<Circle, ?>> gettersForOrderBy = List.of(Circle::getRadius);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }

    private static PreparedCase<Circle> createCase04() {
        // Create query
        String query = "$orderBy=radius&$filter=radius gt 5 or color eq 'Blue' and (radius le 5.00 or color eq 'Yellow')";

        // prepare the List which is expected after the query
        List<Circle> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(2));

        // Prepare getters for order check
        List<Function<Circle, ?>> gettersForOrderBy = List.of(Circle::getRadius);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }

    private static PreparedCase<Circle> createCase05() {
        // Create query
        SuperQueryExecutor executor = new SuperQueryExecutor();
        String fullURL = "https://www.example.com/?$filter=radius gt 5 or color eq 'Blue' and (radius le 5.00 or color eq 'Yellow')&$orderBy=radius";
        String query = UrlParser.getQueryFromUrl(fullURL);

        // prepare the List which is expected after the query
        List<Circle> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(2));

        // Prepare getters for order check
        List<Function<Circle, ?>> gettersForOrderBy = List.of(Circle::getRadius);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }
}