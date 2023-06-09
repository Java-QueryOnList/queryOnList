package testHelpers.genericClasses.classObjects.shape;

import testHelpers.genericClasses.classDecleration.shape.Shape;

import java.util.ArrayList;
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
        RAW_LIST.addAll(CircleObjects.getRawList());
        RAW_LIST.addAll(RectangleObjects.getRawList());
        RAW_LIST.addAll(SquareObjects.getRawList());
    }
}
