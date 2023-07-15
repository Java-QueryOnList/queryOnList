package testHelpers.genericClasses.classObjects.datatypeholder;

import testHelpers.genericClasses.classDecleration.datatypeholder.DataTypeHolder;

import java.util.ArrayList;
import java.util.List;

public final class DataTypeHolderObjects {
    private static final List<DataTypeHolder> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<DataTypeHolder> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
        DataTypeHolder dataTypeHolder1 = new DataTypeHolder(
                true,
                (byte) 1,
                (short) 10,
                100,
                1000L,
                1.5f,
                3.14,
                'A',
                new Object(),
                "Hello",
                42,
                Boolean.TRUE,
                (byte) 2,
                (short) 20,
                200,
                2000L,
                2.5f,
                6.28,
                'B',
                null,
                new int[]{1, 2, 3},
                new String[]{"foo", "bar"},
                new Object[]{new Object()},
                new DataTypeHolder.CustomClass(100)
        );

        DataTypeHolder dataTypeHolder2 = new DataTypeHolder(
                false,
                (byte) 3,
                (short) 30,
                300,
                3000L,
                3.5f,
                9.42,
                'C',
                new Object(),
                "World",
                84,
                Boolean.FALSE,
                (byte) 4,
                (short) 40,
                400,
                4000L,
                4.5f,
                12.56,
                'D',
                null,
                new int[]{4, 5, 6},
                new String[]{"baz", "qux"},
                new Object[]{new Object()},
                new DataTypeHolder.CustomClass(200)
        );

        DataTypeHolder dataTypeHolder3 = new DataTypeHolder(
                true,
                (byte) 5,
                (short) 50,
                500,
                5000L,
                5.5f,
                15.7,
                'E',
                new Object(),
                "OpenAI",
                126,
                Boolean.FALSE,
                (byte) 6,
                (short) 60,
                600,
                6000L,
                6.5f,
                18.9,
                'F',
                null,
                new int[]{7, 8, 9},
                new String[]{"quux", "corge"},
                new Object[]{new Object()},
                new DataTypeHolder.CustomClass(300)
        );

        RAW_LIST.add(dataTypeHolder1);
        RAW_LIST.add(dataTypeHolder2);
        RAW_LIST.add(dataTypeHolder3);
    }
}
