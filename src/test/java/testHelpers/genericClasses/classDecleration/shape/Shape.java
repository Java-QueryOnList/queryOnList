package testHelpers.genericClasses.classDecleration.shape;

// Shape class
public abstract class Shape {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double getArea();
}

