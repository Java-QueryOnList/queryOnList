package testHelpers.genericClasses.classObjects.shape;

import testHelpers.genericClasses.classDecleration.shape.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ShapeObjects {
    private static final List<Shape> RAW_LIST = new ArrayList<>();

    static {
        initRawList();
    }

    public static List<Shape> getRawList() {
        return RAW_LIST;
    }

    private static void initRawList() {
        RAW_LIST.add((Shape) CircleObjects.getRawList());
        RAW_LIST.add((Shape) RectangleObjects.getRawList());
        RAW_LIST.add((Shape) SquareObjects.getRawList());

        Collections.shuffle(RAW_LIST);
    }
}
