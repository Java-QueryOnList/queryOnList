package testHelpers.genericClasses.classObjects.shape;

import testHelpers.genericClasses.classDecleration.shape.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapeObjects {
    public static final List<Shape> TEST_CASE_1 = new ArrayList<>();

    static {
        prepTestCase01();
    }

    private static void prepTestCase01() {
        TEST_CASE_1.add((Shape) CircleObjects.TEST_CASE_1);
        TEST_CASE_1.add((Shape) RectangleObjects.TEST_CASE_1);
        TEST_CASE_1.add((Shape) SquareObjects.TEST_CASE_1);

        Collections.shuffle(TEST_CASE_1);
    }
}
