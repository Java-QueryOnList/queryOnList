package experiments;

import testHelpers.genericClasses.classDecleration.dog.Dog;
import testHelpers.genericClasses.classDecleration.shape.Circle;
import testHelpers.genericClasses.classDecleration.shape.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// Can anytime be deleted and used as a playground for other experiments
public class Experiment {

    public static void main(final String[] args) {
        System.out.println("Run Main");
        List<Circle> circles = new ArrayList<>();
        circles.add(new Circle("Red", 5));
        circles.add(new Circle("Blue", 5));
        circles.add(new Circle("Blue", 5));
        circles.add(new Circle("Yellow", 5));
        circles.add(new Circle("Blue", 5));
        circles.add(new Circle("Blue", 5));
        circles.add(new Circle("Yellow", 5));

        List<List<Circle>> subLists = divideIntoSublistsbyGetter(circles, Shape::getColor);
        subLists.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);
    }

    private static <T> List<List<T>> divideIntoSublistsbyGetter(List<T> wholeList, Function<T, ?> getter) {
        Map<Object, List<T>> map = new HashMap<>();
        for (T element : wholeList) {
            var key = getter.apply(element);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(element);
        }
        return new ArrayList<>(map.values());
    }

}