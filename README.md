# Java-QueryOnList

The Java-QueryOnList is a Java Library that provides functionality for parsing queries and retrieving queried lists based on
the input query of type String.

## Usage

### Example

To use the Java-QueryOnList library you need to create a `QueryExecutor` and call the `execute` method with the query and the class.

For example this will return a list of people called David and sort them by hireDate in ascending order:
```java
import org.queryongenericlist.query.queryexecutor.implementation.SuperQueryExecutor;

public class Example {
  public static void main(String[] args) {
    SuperQueryExecutor superQueryExecutor = new SuperQueryExecutor();
    String query = "$filter=name eq 'david'&$orderBy=hireDate";
    List<Person> rawList = List.of(new Person("David", 2010), new Person("John", 2015), new Person("David", 2009));
    List<Person> queriedList = superQueryExecutor.execute(query, rawList);
  }
}
```

You can also get the same query from a full url:
```
String query = superQueryExecutor.getQueryFromUrl("https://api.cust.cloud/v1.0/people?$filter=name eq 'david'&$orderBy=hireDate");
```

## Features

- Parses queries of type String (e.g. `"$filter=name eq 'david'&$orderBy=hireDate"`), you can also get a query from a
  full url
- Supports querying lists of any type (`List<T>`)
- Custom queries can be implemented by extending the `QueryEngine` and/or `QueryParser` class

## Implemented Queries

With the current implementation `QueryParserImpl` and `QueryEngineImpl` the Query Engine supports the following queries:

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
String query = "https://api.cust.cloud/v1.0/people?$orderBy=name"
```

Will return all people sorted by name in ascending order.

```
String query = "https://api.cust.cloud/v1.0/people?$orderBy=name desc"
```

Will return all people sorted by name in descending order.

Sub-sorts can be specified by a comma-separated list of property names with OPTIONAL direction qualifier.

```
String query = "https://api.cust.cloud/v1.0/people?$orderBy=name desc,hireDate"
```

Will return all people sorted by name in descending order and a secondary sort order of hireDate in ascending order.
Sorting MUST compose with filtering such that:

```
String query = "https://api.cust.cloud/v1.0/people?$filter=name eq 'david'&$orderBy=hireDate"
```

Will return all people whose name is David sorted in ascending order by hireDate.

### Filtering

#### Definition

- Determined by the value of the `$filter` query parameter.
- allows clients to filter a collection of resources that are addressed by a request
- Resources for which the expression evaluates to false or to null, or which reference properties that are unavailable
  due to permissions, are omitted from the response.

#### Examples

```
String query = "https://api.cust.cloud/v1.0/products?$filter=price lt 10.00"
```

Will return all Products whose Price is less than $10.00

```
String query = "$filter=radius gt 5 or color eq 'Blue' and (radius le 5.00 or color eq 'Yellow')";
```

Will return all Circle Objects whose radius is greater than 5 or whose color is blue and whose radius is less than or equal to 5 or whose color is yellow.

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

### Pagination

#### Definition

- Determined by the value of the `$skip` and `$top` query parameters.
- Allows clients to specify a window of results.
- The `$skip` query parameter specifies the number of items to skip in the result.
- The `$top` query parameter specifies a non-negative integer n that limits the number of items returned from a
  collection to the first n items.

#### Examples

```
String query = "https://api.cust.cloud/v1.0/products?$skip=10"
```

Will return all Products starting from the 10th Product.

```
String query = "https://api.cust.cloud/v1.0/products?$top=5"
```

Will return the first 5 Products.

```
String query = "https://api.cust.cloud/v1.0/products?$skip=10&$top=5"
```

Will return the 5 Products starting from the 10th Product.

## Advanced Usage

### Building Custom Queries

The Java-QueryOnList supports extending the QueryEngine and/or QueryParser classes for creating custom queries. This can be useful when you need to support different or more complex scenarios that aren't covered by the built-in functionality.

## Testing

To verify the correct functionality of the QueryEngine, we provide a testing framework which includes two main classes: `CaseTester` and `PreparedCase`.

### CaseTester

The `CaseTester` class is a generic class that is used to create a set of test cases for a certain type of object.

This class has a method `addCase` that takes a `PreparedCase` object as an argument and adds it to the list of cases that will be tested when the `testCases` method is called.

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

`PreparedCase` also has a method `getQueriedList` that retrieves the queried list based on the provided query and raw list.

Here's a simple example of creating a `PreparedCase`:

```
PreparedCase<Car> filterYearOfProduction = new PreparedCase<>(
    List.of(new Car("Toyota", 2009), new Car("Toyota", 2010), new Car("Honda", 2009)), // rawList 
    "$filter=year eq 2009", // query
    List.of(new Car("Toyota", 2009), new Car("Honda", 2009)), // expectedList
);
```

In this example, `CarTestCases.filterYearOfProduction` is a `PreparedCase` object that describes a specific test case.

You can add as many test cases as needed to the `CaseTester` to ensure all functionality is tested thoroughly. For instance, if you want to add more test cases for the Car class, you can just chain `addCase` calls like this:

```
CaseTester<Car> carCaseTester = new CaseTester<>();

carCaseTester
    .addCase(CarTestCases.filterYearOfProduction)
    .addCase(CarTestCases.filterTrivialOr)
    .addCase(CarTestCases.filterTrivialNot)
    .testCases();
```

These test classes provide a convenient and flexible way to ensure that the QueryEngine is working as expected. By creating a `PreparedCase` for each scenario that needs to be tested, you can ensure that all edge cases are covered and that any regressions will be caught immediately.

For test examples see the [GenericClassTest](src/test/java/org/queryongenericlist/GenericClassTests.java) class.

## Contributing

Contributions are welcome! Please submit a pull request on our GitHub repository.

## License

QueryEngine is released under the MIT License. Please see the `LICENSE` file for more details.