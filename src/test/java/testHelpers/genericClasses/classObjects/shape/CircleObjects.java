package testHelpers.genericClasses.classObjects.shape;

import testHelpers.genericClasses.classDecleration.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public final class CircleObjects {
    private static final List<Circle> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<Circle> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
        Circle circle1 = new Circle("Red", 5.0);
        Circle circle2 = new Circle("Blue", 3.0);
        Circle circle3 = new Circle("Green", 7.5);
        Circle circle4 = new Circle("Yellow", 2.5);
        Circle circle5 = new Circle("Orange", 6.0);
        Circle circle6 = new Circle("Purple", 4.0);
        Circle circle7 = new Circle("Pink", 1.0);
        Circle circle8 = new Circle("Black", 3.5);
        Circle circle9 = new Circle("White", 2.0);

        RAW_LIST.add(circle1);
        RAW_LIST.add(circle2);
        RAW_LIST.add(circle3);
        RAW_LIST.add(circle4);
        RAW_LIST.add(circle5);
        RAW_LIST.add(circle6);
        RAW_LIST.add(circle7);
        RAW_LIST.add(circle8);
        RAW_LIST.add(circle9);
    }
}
