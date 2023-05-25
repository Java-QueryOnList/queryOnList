package testHelpers.genericClasses.classObjects.shape;


import testHelpers.genericClasses.classDecleration.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class RectangleObjects {
    public static final List<Rectangle> TEST_CASE_1 = new ArrayList<>();

    static {
        prepTestCase01();
    }

    private static void prepTestCase01() {
        Rectangle rectangle1 = new Rectangle("Red", 5.0, 10.0);
        Rectangle rectangle2 = new Rectangle("Blue", 7.0, 3.0);
        Rectangle rectangle3 = new Rectangle("Green", 4.0, 6.0);
        Rectangle rectangle4 = new Rectangle("Yellow", 2.0, 8.0);
        Rectangle rectangle5 = new Rectangle("Orange", 9.0, 12.0);
        Rectangle rectangle6 = new Rectangle("Purple", 6.0, 9.0);
        Rectangle rectangle7 = new Rectangle("Pink", 3.0, 5.0);
        Rectangle rectangle8 = new Rectangle("Brown", 8.0, 4.0);
        Rectangle rectangle9 = new Rectangle("Gray", 10.0, 2.0);

        TEST_CASE_1.add(rectangle1);
        TEST_CASE_1.add(rectangle2);
        TEST_CASE_1.add(rectangle3);
        TEST_CASE_1.add(rectangle4);
        TEST_CASE_1.add(rectangle5);
        TEST_CASE_1.add(rectangle6);
        TEST_CASE_1.add(rectangle7);
        TEST_CASE_1.add(rectangle8);
        TEST_CASE_1.add(rectangle9);
    }
}
