package testHelpers.genericClasses.classTestCases.developer;

import testHelpers.genericClasses.classDecleration.developer.Developer;
import testHelpers.genericClasses.classDecleration.developer.Project;
import testHelpers.genericClasses.classObjects.developer.DeveloperObjects;
import testHelpers.genericClasses.classTestCases.PreparedCase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public final class DeveloperTestCases {
    private static final List<Developer> rawList01 = DeveloperObjects.getRawList();
    public static final PreparedCase<Developer> filterWithNullAgeBetween30And40;
    public static final PreparedCase<Developer> filterWithNullOnSubclass;
    public static final PreparedCase<Developer> orderByLanguagesWithNullOnList;
    public static final PreparedCase<Developer> orderByLanguagesThenProjects;

    static {
        filterWithNullAgeBetween30And40 = createCase01();
        filterWithNullOnSubclass = createCase02();
        orderByLanguagesWithNullOnList = createCase03();
        orderByLanguagesThenProjects = createCase04();
    }

    private static PreparedCase<Developer> createCase01() {
        // Create query
        String query = "$filter=age ge 30 and age le 40";

        // prepare the List which is expected after the query
        List<Developer> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(3));
        expectedList.add(rawList01.get(4));
        expectedList.add(rawList01.get(5));
        expectedList.add(rawList01.get(9));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Developer> createCase02() {
        // Create query
        String query = "$filter=address.city eq 'Berlin'";

        // prepare the List which is expected after the query
        List<Developer> expectedList = new ArrayList<>();
        expectedList.add(rawList01.get(3));

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList);
    }

    private static PreparedCase<Developer> createCase03() {
        // Create query
        String query = "$orderBy=programmingLanguages";

        // prepare the List which is expected after the query
        List<Developer> expectedList = new ArrayList<>();
        // "C#"
        expectedList.add(rawList01.get(4));
        // "CSS"
        expectedList.add(rawList01.get(6));
        // "Java"
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(10));
        // "Javascript"
        expectedList.add(rawList01.get(0));
        // "PHP"
        expectedList.add(rawList01.get(8));
        // "Python"
        expectedList.add(rawList01.get(2));
        // "Ruby"
        expectedList.add(rawList01.get(5));
        // "Swift"
        expectedList.add(rawList01.get(9));
        // null
        expectedList.add(rawList01.get(3));
        expectedList.add(rawList01.get(7));
        expectedList.add(rawList01.get(11));

        // Prepare getters for order check
        List<Function<Developer, ?>> gettersForOrderBy = List.of(Developer::getProgrammingLanguages);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }

    private static PreparedCase<Developer> createCase04() {
        // Create query
        String query = "$orderBy=programmingLanguages, projects.name";

        // prepare the List which is expected after the query
        List<Developer> expectedList = new ArrayList<>();
        // "C#"
        expectedList.add(rawList01.get(4));
        // "CSS"
        expectedList.add(rawList01.get(6));
        // "Java"
        expectedList.add(rawList01.get(1));
        expectedList.add(rawList01.get(10));
        // "Javascript"
        expectedList.add(rawList01.get(0));
        // "PHP"
        expectedList.add(rawList01.get(8));
        // "Python"
        expectedList.add(rawList01.get(2));
        // "Ruby"
        expectedList.add(rawList01.get(5));
        // "Swift"
        expectedList.add(rawList01.get(9));
        // null
        expectedList.add(rawList01.get(7));
        expectedList.add(rawList01.get(11));
        expectedList.add(rawList01.get(3));

        // Prepare getters for order check
        Function<Developer, String[]> programmingLanguagesGetter = Developer::getProgrammingLanguages;
        Function<Developer, String> projectNameGetter = (developer) -> {
            List<Project> projects = developer.getProjects();

            if (projects == null || projects.isEmpty()) {
                return null;
            }
            // min because ascending
            return projects.stream()
                    .map(Project::getName)
                    .min(Comparator.naturalOrder())
                    .orElse("");
        };
        List<Function<Developer, ?>> gettersForOrderBy = List.of(programmingLanguagesGetter, projectNameGetter);

        // Return Created PreparedCase Object
        return new PreparedCase<>(rawList01, query, expectedList, gettersForOrderBy);
    }
}