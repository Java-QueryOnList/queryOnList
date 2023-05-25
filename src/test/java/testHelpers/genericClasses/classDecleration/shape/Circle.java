package testHelpers.genericClasses.classDecleration.shape;

import java.util.Objects;

// Circle class
public class Circle extends Shape {
    private double radius;
    private String color;

    public Circle(String color, double radius) {
        super(color);
        this.color = color;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Circle other = (Circle) obj;

        // Compare field values for equality
        return color == other.getColor() && Objects.equals(radius, other.getRadius());
    }
}
