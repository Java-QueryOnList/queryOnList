# Offene TODO'S:
- filter queries:
  - "not" berücksichtigen
  - tests
- orderBy queries:
  - alles
- pagination queries:
  - alles

***

Die folgende Doku wurde mithilfe der gegebenen PDF erstellt, und wurde zum eigenen Verständnis erstellt. Muss auf jeden Fall noch angepasst, erweitert und verfeinert werden:

- Readme file todos
  - genau beschreiben was die library kann und was nicht
  - beispiele wie man die methoden verwendet
  - stihl spezfische Dinge rausnehmen

***

# QueryEngine

The QueryEngine is a Java Library that provides functionality for parsing queries and retrieving queried lists based on the input query of type String.

## Features

- Parses queries of type String (e.g. `"$filter=name eq 'david'&$orderBy=hireDate"`)
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
- MAY include the suffix "asc" for ascending (default) or "desc" for descending (separated from the property name by one or more spaces)
- items with the same value for the first expression are sorted by the result value of the second expression, and so on

#### Examples
```
GET https://api.stihl.cloud/v1.0/people?$orderBy=name
```
Will return all people sorted by name in ascending order.

```
GET https://api.stihl.cloud/v1.0/people?$orderBy=name desc
```
Will return all people sorted by name in descending order.

Sub-sorts can be specified by a comma-separated list of property names with OPTIONAL direction qualifier.
```
GET https://api.stihl.cloud/v1.0/people?$orderBy=name desc,hireDate
```
Will return all people sorted by name in descending order and a secondary sort order of hireDate in ascending order. Sorting MUST compose with filtering such that:
```
GET https://api.stihl.cloud/v1.0/people?$filter=name eq 'david'&$orderBy=hireDate
```
Will return all people whose name is David sorted in ascending order by hireDate.


### Filtering
#### Definition
- Determined by the value of the `$filter` query parameter.
- allows clients to filter a collection of resources that are addressed by a request
- Resources for which the expression evaluates to false or to null, or which reference properties that are unavailable due to permissions, are omitted from the response.

#### Examples

```
GET https://api.stihl.cloud/v1.0/products?$filter=price lt 10.00
```
Will return all Products whose Price is less than $10.00

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
- TODO: Not defined yet
#### Examples

```
GET https://api.stihl.cloud/v1.0/products?TODO
```
Will return TODO