package testHelpers.genericClasses.classObjects.shape;

import testHelpers.genericClasses.classDecleration.shape.Square;

import java.util.ArrayList;
import java.util.List;

public class SquareObjects {
    public static final List<Square> TEST_CASE_1 = new ArrayList<>();
    static {
        prepTestCase01();
    }

    private static void prepTestCase01() {

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

        TEST_CASE_1.add(square1);
        TEST_CASE_1.add(square2);
        TEST_CASE_1.add(square3);
        TEST_CASE_1.add(square4);
        TEST_CASE_1.add(square5);
        TEST_CASE_1.add(square6);
        TEST_CASE_1.add(square7);
        TEST_CASE_1.add(square8);
        TEST_CASE_1.add(square9);
    }
}
