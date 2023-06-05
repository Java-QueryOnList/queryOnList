package testHelpers.genericClasses.classObjects.shape;

import testHelpers.genericClasses.classDecleration.shape.Square;

import java.util.ArrayList;
import java.util.List;

public final class SquareObjects {
    private static final List<Square> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<Square> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {

        // Creating objects of the Square class
        Square square1 = new Square("Red", 5.0);
        Square square2 = new Square("Blue", 7.5);
        Square square3 = new Square("Green", 3.0);
        Square square4 = new Square("Yellow", 6.2);
        Square square5 = new Square("Orange", 4.8);
        Square square6 = new Square("Purple", 2.5);
        Square square7 = new Square("Pink", 9.1);
        Square square8 = new Square("Gray", 3.7);
        Square square9 = new Square("Black", 8.0);

        RAW_LIST.add(square1);
        RAW_LIST.add(square2);
        RAW_LIST.add(square3);
        RAW_LIST.add(square4);
        RAW_LIST.add(square5);
        RAW_LIST.add(square6);
        RAW_LIST.add(square7);
        RAW_LIST.add(square8);
        RAW_LIST.add(square9);
    }
}
