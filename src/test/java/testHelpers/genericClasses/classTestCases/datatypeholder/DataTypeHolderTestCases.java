package testHelpers.genericClasses.classTestCases.datatypeholder;

import testHelpers.genericClasses.classDecleration.datatypeholder.DataTypeHolder;
import testHelpers.genericClasses.classObjects.datatypeholder.DataTypeHolderObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class DataTypeHolderTestCases {
    private static final List<DataTypeHolder> rawList01 = DataTypeHolderObjects.getRawList();
    public static final PreparedCase<DataTypeHolder> filterBooleanField;
    public static final PreparedCase<DataTypeHolder> filterStringField;
    public static final PreparedCase<DataTypeHolder> orderByIntegerFieldDesc;
    public static final PreparedCase<DataTypeHolder> filterCharField;

    static {
        filterBooleanField = createCase01();
        filterStringField = createCase02();
        orderByIntegerFieldDesc = createCase03();
        filterCharField = createCase04();
    }

    private static PreparedCase<DataTypeHolder> createCase01() {
        // Create query
        String query = "$filter=booleanField eq true";

        // Prepare the List which is expected after the query
        List<DataTypeHolder> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(0));
        expectedList.add(rawList01.get(2));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<DataTypeHolder> createCase02() {
        // Create query
        String query = "$filter=stringField eq 'Hello'";

        // Prepare the List which is expected after the query
        List<DataTypeHolder> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(0));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<DataTypeHolder> createCase03() {
        // Create query
        String query = "$orderBy=intField desc";

        // Prepare the List which is expected after the query
        List<DataTypeHolder> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(2));
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(0));

        // Prepare the getter for order check
        Function<DataTypeHolder, Integer> getIntField = DataTypeHolder::getIntField;
        List<Function<DataTypeHolder, ?>> gettersForOrderBy = List.of(getIntField);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }

    private static PreparedCase<DataTypeHolder> createCase04() {
        // Create query
        String query = "$filter=charField eq 'A'";

        // Prepare the List which is expected after the query
        List<DataTypeHolder> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(0));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

}

