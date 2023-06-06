package examples;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
 * only for sketching ideas
 * no working code to find here
 */
public class firstSketch {

    void test() {
        final String query = "filter=TestItem.field1 eq 'value 1' and TestItem.field2 gt 'value 2' $orderby=TestItem.field1 desc, TestItem.field2 desc"
        List.of(
                        TestItem.builder().field1("value1").field2("value2").build(),
                        TestItem.builder().field1("value1").field2("value2").build()
                ).stream()

                .filter((final TestItem item) -> item.getField1().equals("value 1"))
                .filter((final TestItem item) -> item.getField2().compareTo("value 2") > 0)
                .sorted(
                        Comparator.comparing((final TestItem o1) -> {
                                    return o1.getField1();

                                }).reversed()
                                .thenComparing((final TestItem o1) -> {
                                    return o1.getField2();
                                }).reversed()
                )
                .collect(Collectors.toList());
    }


    void test2() {
        final String query = "filter=TestItem.field1 eq 'value 1' or TestItem.field2 gt 'value 2' $orderby=TestItem.field1 desc, TestItem.field2 desc"
        List.of(
                        TestItem.builder().field1("value1").field2("value2").build(),
                        TestItem.builder().field1("value1").field2("value2").build()
                ).stream()

                .filter((final TestItem item) -> {
                    // left             or                  // right
                    return (item.getField1().equals("value 1")) || (item.getField2().compareTo("value 2") > 0);
                })
                .sorted(
                        Comparator.comparing((final TestItem o1) -> {
                                    return o1.getField1();

                                }).reversed()
                                .thenComparing((final TestItem o1) -> {
                                    return o1.getField2();
                                }).reversed()
                )
                .collect(Collectors.toList());
    }


    void test3() {
        final String query = "filter=(TestItem.field1 eq 'value 1' or TestItem.field1 eq 'value 2' ) or TestItem.field2 gt 'value 2' $orderby=TestItem.field1 desc, TestItem.field2 desc"
        List.of(
                        TestItem.builder().field1("value1").field2("value2").build(),
                        TestItem.builder().field1("value1").field2("value2").build()
                ).stream()

                .filter((final TestItem item) -> {

                    final Predicate<String> leftGroup1 = (fieldValue) -> fieldValue.equals("value 1");
                    final Predicate<String> rightGroup1 = (fieldValue) -> fieldValue.equals("value 2");
                    final Predicate<String> group2 = (fieldValue) -> fieldValue.compareTo("value 2") > 0;

                    leftGroup1.or(rightGroup1).test("value 1");

                    return leftGroup1.test(item.getField1()) || rightGroup1.test(item.getField1());
                    group2.test(item.getField2());

                })
                .sorted(
                        Comparator.comparing((final TestItem o1) -> {
                                    return o1.getField1();

                                }).reversed()
                                .thenComparing((final TestItem o1) -> {
                                    return o1.getField2();
                                }).reversed()
                )
                .collect(Collectors.toList());
    }

    void test4CurriedBySchoenfinkel() {
        final String query = "filter=(TestItem.field1 eq 'value 1' or TestItem.field1 eq 'value 2' ) or TestItem.field2 gt 'value 2' $orderby=TestItem.field1 desc, TestItem.field2 desc"
        List.of(
                        TestItem.builder().field1("value1").field2("value2").build(),
                        TestItem.builder().field1("value1").field2("value2").build()
                ).stream()

                .filter((final TestItem item) -> {
                    // https://de.wikipedia.org/wiki/Currying / Schönfinkeln
                    final Predicate<TestItem> startPredicatePipeline = (TestItem ignore) -> true;

                    final Predicate<TestItem> leftGroup1 = functionalEQPredicateBuilderPoweredBySchoenfinkel("field1", "value1");
                    final Predicate<TestItem> rightGroup1 = functionalEQPredicateBuilderPoweredBySchoenfinkel("field1", "value2");
                    final Predicate<TestItem> grouped = functionalOrGroupedPredicateBuilderPoweredBySchoenfinkel(leftGroup1, rightGroup1);

                    final Predicate<TestItem> group2 = functionalGTPredicateBuilderPoweredBySchoenfinkel("field2", "value2");

                    return startPredicatePipeline
                            .and(grouped)
                            .or(group2)
                            .test(item);
                })
                .sorted(
                        Comparator.comparing((final TestItem o1) -> {
                                    return o1.getField1();

                                }).reversed()
                                .thenComparing((final TestItem o1) -> {
                                    return o1.getField2();
                                }).reversed()
                )
                .collect(Collectors.toList());
    }

    @NonNull
    private Predicate<TestItem> functionalEQPredicateBuilderPoweredBySchoenfinkel(
            @NonNull final String fieldName,
            @NonNull final String value
    ) {
        return (reflectiveObject) -> extractFieldViaReflection(reflectiveObject, fieldName).equals(value);
    }

    @NonNull
    private <T> Predicate<T> functionalOrGroupedPredicateBuilderPoweredBySchoenfinkel(
            @NonNull final Predicate<T> leftGroup1,
            @NonNull final Predicate<T> rightGroup1)
    {
        return leftGroup1.or(rightGroup1);
    }

    @NonNull
    private Predicate<TestItem> functionalGTPredicateBuilderPoweredBySchoenfinkel(
            @NonNull final String fieldName,
            @NonNull final String value
    ) {
        return (reflectiveObject) -> extractFieldViaReflection(reflectiveObject, fieldName).compareTo(value) > 0;
    }


    // use reflection to get value of Object.fieldName
    private String extractFieldViaReflection(
            @NonNull final Object reflectionObject,
            @NonNull final String fieldName
    ) {
        // pseudocode ....
        return "";
    }

    @Data
    @Builder
    class TestItem {
        private String field1;
        private String field2;
    }

    /*
    - (node) price
        -ascending
        - name (node)
            - descending
     */

}
