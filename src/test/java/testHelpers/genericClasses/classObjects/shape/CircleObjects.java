package testHelpers.genericClasses.classObjects.shape;

import testHelpers.genericClasses.classDecleration.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class CircleObjects {
    public static final List<Circle> TEST_CASE_1 = new ArrayList<>();

    static {
        prepTestCase01();
    }

    private static void prepTestCase01() {
        Circle circle1 = new Circle("Red", 5.0);
        Circle circle2 = new Circle("Blue", 3.0);
        Circle circle3 = new Circle("Green", 7.5);
        Circle circle4 = new Circle("Yellow", 2.5);
        Circle circle5 = new Circle("Orange", 6.0);
        Circle circle6 = new Circle("Purple", 4.0);
        Circle circle7 = new Circle("Pink", 1.0);
        Circle circle8 = new Circle("Black", 3.5);
        Circle circle9 = new Circle("White", 2.0);

        TEST_CASE_1.add(circle1);
        TEST_CASE_1.add(circle2);
        TEST_CASE_1.add(circle3);
        TEST_CASE_1.add(circle4);
        TEST_CASE_1.add(circle5);
        TEST_CASE_1.add(circle6);
        TEST_CASE_1.add(circle7);
        TEST_CASE_1.add(circle8);
        TEST_CASE_1.add(circle9);
    }
}
