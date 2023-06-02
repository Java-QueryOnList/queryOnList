package testHelpers.genericClasses.classObjects.shape;


import testHelpers.genericClasses.classDecleration.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

public final class RectangleObjects {
    private static final List<Rectangle> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<Rectangle> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
        Rectangle rectangle1 = new Rectangle("Red", 5.0, 10.0);
        Rectangle rectangle2 = new Rectangle("Blue", 7.0, 3.0);
        Rectangle rectangle3 = new Rectangle("Green", 4.0, 6.0);
        Rectangle rectangle4 = new Rectangle("Yellow", 2.0, 8.0);
        Rectangle rectangle5 = new Rectangle("Orange", 9.0, 12.0);
        Rectangle rectangle6 = new Rectangle("Purple", 6.0, 9.0);
        Rectangle rectangle7 = new Rectangle("Pink", 3.0, 5.0);
        Rectangle rectangle8 = new Rectangle("Brown", 8.0, 4.0);
        Rectangle rectangle9 = new Rectangle("Gray", 10.0, 2.0);

        RAW_LIST.add(rectangle1);
        RAW_LIST.add(rectangle2);
        RAW_LIST.add(rectangle3);
        RAW_LIST.add(rectangle4);
        RAW_LIST.add(rectangle5);
        RAW_LIST.add(rectangle6);
        RAW_LIST.add(rectangle7);
        RAW_LIST.add(rectangle8);
        RAW_LIST.add(rectangle9);
    }
}
