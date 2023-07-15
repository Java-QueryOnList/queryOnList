# Java-QueryOnList

The Java-QueryOnList library is a Java Library that provides functionality for parsing queries and retrieving queried
lists based on
the input query of type String.

## Overview

With the current implementation the Java QueryOnList involves various components and workflows, explained in the
following:

### Visual Diagram

![Diagram](Diagram.svg)

### Component Details

| Type           | Component            | Description                                                                                                                                          |
|:---------------|:---------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------|
| Executor       | `QueryExecutor`      | Interface for executing a given query on a given list and returning the queried list.                                                                |
| Executor       | `SuperQueryExecutor` | Implements `QueryExecutor` and provides specific parser and engine for executing the given query on a given list.                                    |
| Parser         | `QueryParser`        | Interface for parsing a query string and converting it to an abstract syntax tree (AST).                                                             |
| Parser         | `SuperQueryParser`   | Implements `QueryParser` and provides specific logic and features for parsing a given query. Returning `SuperQueryNode`.                             |
| Parser         | `FilterParser`       | Implements `QueryParser`, parses filter queries and returns a `FilterNode`.                                                                          |
| Parser         | `SortingParser`      | Implements `QueryParser`, parses sorting queries and returns a `SortingNode`.                                                                        |
| Parser         | `PaginationParser`   | Implements `QueryParser`, parses pagination queries and returns a `PaginationNode`.                                                                  |
| AST            | `QueryNode`          | Interface for representing a query node in the AST.                                                                                                  |
| AST            | `SuperQueryNode`     | Implements `QueryNode` and provides specific structure for representing a AST with nodes for filter, sorting and pagination.                         |
| AST            | `FilterNode`         | Implements `QueryNode` and represents filter nodes inside the AST.                                                                                   |
| AST            | `SortingNode`        | Implements `QueryNode` and represents sorting nodes inside the AST.                                                                                  |
| AST            | `PaginationNode`     | Implements `QueryNode` and represents pagination nodes inside the AST.                                                                               |
| Engine         | `QueryEngine`        | Interface for executing a given AST object on a given stream of a list and returning the queried stream.                                             |
| Engine         | `SuperQueryEngine`   | Implements `QueryEngine` and provides a specific logic for executing a given `SuperQueryNode` with it's subengines on given list stream.             |
| Engine         | `FilterEngine`       | Implements `QueryEngine` and provides specific functionalities for executing a given `FilterNode` structure on a given list stream.                  |
| Engine         | `SortingEngine`      | Implements `QueryEngine` and provides specific functionalities for executing a given `SortingNode` structure on a given list stream.                 |
| Engine         | `PaginationEngine`   | Implements `QueryEngine` and provides specific functionalities for executing a given `PaginationNode` structure on a given list stream.              |
| Result Wrapper | `PaginatedResult`    | Wrapper for the queried list for additional information besides the result such as the original queries, the base URL and the URL for the next page. |
| Utility        | `StringParser`       | Provides methods for extracting substrings from a string based on a regular expression pattern.                                                      |
| Utility        | `ObjectHandler`      | Provides methods for resolving object values and handling arrays and lists.                                                                          |
| Utility        | `GenericClassHelper` | Assists in working with generic classes by providing reflection-based methods for type resolution and instantiation.                                 |
| Utility        | `ComparativeHelper`  | Provides a Comparator implementation for comparing objects of different types.                                                                       |
| Utility        | `UrlParser`          | Uses `StringParser` to provide methods for extracting the query and base URL from a full URL.                                                        |

## Usage

### Example

To use the Java-QueryOnList library you need to create
a [SuperQueryExecutor](src/main/java/org/queryongenericlist/query/queryexecutor/implementation/SuperQueryExecutor.java) (
or your custom implementation of `QueryExecutor`) and call the `execute` method with the query and the class.

For example this will return a list of people called David and sort them by hireDate in ascending order:

```java
import org.queryongenericlist.query.queryexecutor.implementation.SuperQueryExecutor;

public class Example {
    public static void main(String[] args) {
        SuperQueryExecutor superQueryExecutor = new SuperQueryExecutor();
        String query = "$filter=name eq 'David'&$orderBy=hireDate";
        List<Person> rawList = List.of(new Person("David", 2010), new Person("John", 2015), new Person("David", 2009));
        List<Person> queriedList = superQueryExecutor.execute(query, rawList);
    }
}
```

You can also get the same query from a full url:

```
String query = superQueryExecutor.getQueryFromUrl("https://api.cust.cloud/v1.0/people?$filter=name eq 'David'&$orderBy=hireDate");
```

## Features

- Parses queries of type String (e.g. `"$filter=name eq 'David'&$orderBy=hireDate"`), you can also get a query from a
  full url
- Supports querying lists of any type (`List<T>`)
- Custom queries possible by
  implementing [QueryExecutor](src/main/java/org/queryongenericlist/query/queryexecutor/QueryExecutor.java), [QueryEngine](src/main/java/org/queryongenericlist/query/queryengine/QueryEngine.java)
  and/or [QueryParser](src/main/java/org/queryongenericlist/query/abstractsyntaxtree/queryparser/QueryParser.java)

## Implemented Queries

The Java-QueryOnList library currently supports the following queries when using the default implementations
of [SuperQueryParser](src/main/java/org/queryongenericlist/query/abstractsyntaxtree/queryparser/implementation/SuperQueryParser.java)
and [SuperQueryEngine](src/main/java/org/queryongenericlist/query/queryengine/implementation/SuperQueryEngine.java):

### Filtering

#### Definition

- Determined by the value of the `$filter` query parameter.
- allows clients to filter a collection of resources that are addressed by a request
- Resources for which the expression evaluates to false or to null, or which reference properties that are unavailable
  due to permissions, are omitted from the response.

#### Examples

```
String query = "$filter=price lt 10.00"
```

Will return all Products whose Price is less than $10.00

```
String query = "$filter=radius gt 5 or color eq 'Blue' and (radius le 5.00 or color eq 'Yellow')";
```

Will return all Circle Objects whose radius is greater than 5 or whose color is blue and whose radius is less than or
equal to 5 or whose color is yellow.

#### Operators

| Operator             | Meaning               | Example                                                  |
|:---------------------|:----------------------|:---------------------------------------------------------|
| Comparison Operators |                       |                                                          |
| eq                   | Equal                 | city eq 'Waiblingen'                                     |
| ne                   | Not equal             | city ne 'Ludwigsburg'                                    |
| gt                   | Greater than          | price gt 20                                              |
| ge                   | Greater than or equal | price ge 10                                              |
| lt                   | Less than             | price lt 20                                              |
| le                   | Less than or equal    | price le 100                                             |
| Logical Operators    |                       |                                                          |
| and                  | Logical and           | price le 200 and price gt 3.5                            |
| or                   | Logical or            | price le 3.5 or price gt 200                             |
| not                  | Logical negation      | not price le 3.5                                         |
| Grouping Operators   |                       |                                                          |
| ( )                  | Precedence grouping   | (priority eq 1 or city eq 'Waiblingen') and price gt 100 |

#### Operator precedence

- Some operators have precedence over others
- Operators in the same category have equal precedence

Ranking of precedence (descending):

5. **Grouping:** ()
4. **Unary:** not, gt, ge, lt, le
3. **Equality:** eq, ne
2. **Conditional AND:** and
1. **Conditional OR:** or

### Sorting

#### Definition

- Determined by the value of the `$orderBy` query parameter.
- Contains a comma-separated list of expressions used to sort the items
- A special case of such an expression is a property path terminating on a primitive property
- NULL values are sorted as "less than" non-NULL values.
- MAY include the suffix "asc" for ascending (default) or "desc" for descending (separated from the property name by one
  or more spaces)
- items with the same value for the first expression are sorted by the result value of the second expression, and so on

#### Examples

```
String query = "$orderBy=name"
```

Will return all people sorted by name in ascending order.

```
String query = "$orderBy=name desc"
```

Will return all people sorted by name in descending order.

Sub-sorts can be specified by a comma-separated list of property names with OPTIONAL direction qualifier.

```
String query = "$orderBy=name desc,hireDate"
```

Will return all people sorted by name in descending order and a secondary sort order of hireDate in ascending order.
Sorting MUST compose with filtering such that:

```
String query = "$filter=name eq 'David'&$orderBy=hireDate"
```

Will return all people whose name is David sorted in ascending order by hireDate.

### Pagination

#### Definition

- Determined by the value of the `$skip` and `$top` query parameters.
- Allows clients to specify a window of results.
- The `$skip` query parameter specifies the number of items to skip in the result.
- The `$top` query parameter specifies a non-negative integer n that limits the number of items returned from a
  collection to the first n items.

#### Examples

```
String query = "$skip=10"
```

Will return all Products starting from the 10th Product.

```
String query = "$top=5"
```

Will return the first 5 Products.

```
String query = "$skip=10&$top=5"
```

Will return the 5 Products starting from the 10th Product.

## Advanced Usage

### Building Custom Queries

The Java-QueryOnList supports custom implementations
of [QueryExecutor](src/main/java/org/queryongenericlist/query/queryexecutor/QueryExecutor.java), [QueryEngine](src/main/java/org/queryongenericlist/query/queryengine/QueryEngine.java)
and/or [QueryParser](src/main/java/org/queryongenericlist/query/abstractsyntaxtree/queryparser/QueryParser.java) to
support custom queries. This can be useful when you need to support different or more complex scenarios that aren't
covered by the built-in functionality.

## Testing

To verify the correct functionality of the Java-QueryOnList library, we provide a testing framework which includes two
main classes: `CaseTester` and `PreparedCase`.

### CaseTester

The `CaseTester` class is a generic class that is used to create a set of test cases for a certain type of object.

This class has a method `addCase` that takes a `PreparedCase` object as an argument and adds it to the list of cases
that will be tested when the `testCases` method is called.

Here's a simple example of using `CaseTester`:

```
CaseTester<Car> carCaseTester = new CaseTester<>();

carCaseTester
    .addCase(CarTestCases.filterYearOfProduction)
    .testCases();
```

In this example, `CarTestCases.filterYearOfProduction` is a `PreparedCase` object that describes a specific test case.

### PreparedCase

The `PreparedCase` class is a generic class that describes a specific test case. It includes:

- `rawList`: the full list with elements which the query is run on.
- `query`: the query itself of type String.
- `expectedList`: the expected list after the rawList getting queried.
- `gettersForOrderBy`: all getters to check order (Only when testing 'orderBy' to check order).

`PreparedCase` also has a method `getQueriedList` that retrieves the queried list based on the provided query and raw
list.

Here's a simple example of creating a `PreparedCase`:

```
PreparedCase<Car> filterYearOfProduction = new PreparedCase<>(
    List.of(new Car("Toyota", 2009), new Car("Toyota", 2010), new Car("Honda", 2009)), // rawList 
    "$filter=year eq 2009", // query
    List.of(new Car("Toyota", 2009), new Car("Honda", 2009)), // expectedList
);
```

In this example, `CarTestCases.filterYearOfProduction` is a `PreparedCase` object that describes a specific test case.

You can add as many test cases as needed to the `CaseTester` to ensure all functionality is tested thoroughly. For
instance, if you want to add more test cases for the Car class, you can just chain `addCase` calls like this:

```
CaseTester<Car> carCaseTester = new CaseTester<>();

carCaseTester
    .addCase(CarTestCases.filterYearOfProduction)
    .addCase(CarTestCases.filterTrivialOr)
    .addCase(CarTestCases.filterTrivialNot)
    .testCases();
```

These test classes provide a convenient and flexible way to ensure that the Java-QueryOnList library is working as
expected. By creating a `PreparedCase` for each scenario that needs to be tested, you can ensure that all edge cases are
covered and that any regressions will be caught immediately.

For test examples see the [GenericClassTest](src/test/java/org/queryongenericlist/GenericClassTests.java) class.

## Contributing

Contributions are welcome! Please submit a pull request on our GitHub repository.

## Acknowledgments

This project greatly appreciates the contributions of:

- [svencc](https://github.com/orgs/Java-QueryOnList/people/svencc) - svencc led the implementation, DevOps setup, and
  managed client communication. His ability to translate customer requirements into executable tasks and provide crucial
  support to smartkanak was invaluable. His diverse skill set has been a cornerstone of the project.

- [smartkanak](https://github.com/orgs/Java-QueryOnList/people/smartkanak) - Masterminded the project's core
  functionalities. Meticulously developed the parsing logic, engine implementation, execution flow, and ensured robust
  testing. His technical acumen has been pivotal to the project's success.

Our sincere thanks go to both of them for their indispensable contributions. This appreciation is a tribute to their
dedication and hard work, of course without the intention of praising themselves, as this was written by ChatGPT and not
by them.

## License

Java-QueryOnList is released under the MIT License. Please see the [LICENSE](LICENSE) file for more details.