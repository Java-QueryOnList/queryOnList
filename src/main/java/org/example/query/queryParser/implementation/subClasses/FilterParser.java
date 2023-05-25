package org.example.query.queryParser.implementation.subClasses;

import org.example.query.queryNode.implementation.filterNode.FilterNode;
import org.example.query.queryNode.implementation.filterNode.filterValue.FilterValue;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses.PrimitiveOperand;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses.ReferenceOperand;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.Operator;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.comparisonOperator.subClasses.*;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalAnd;
import org.example.query.queryNode.implementation.filterNode.filterValue.subClasses.operator.logicalOperator.subClasses.LogicalOr;
import org.example.query.queryParser.implementation.QueryParserImpl;
import org.example.utils.StringParser;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class FilterParser extends QueryParserImpl {
    List<String> splitQuery;

    private static final String SPLIT_PATTERN = "([\\w.]+|'.+?'|\\S)"; //regex for any token of the query, whether operator or operand
    int index;

    public FilterParser(String query) {
        super(query);
        this.splitQuery = StringParser.getAll(query, SPLIT_PATTERN);
        this.index = 0;
    }

    public FilterParser(String query, int index) {
        super(query);
        splitQuery = StringParser.getAll(query, SPLIT_PATTERN);
        this.index = index;
    }

    public FilterParser(List<String> splitQuery) {
        super(String.join(" ", splitQuery));
        this.splitQuery = splitQuery;
        this.index = 0;
    }

    public FilterNode parse() {
        Stack<FilterNode> operatorStack = new Stack<>();
        Stack<FilterNode> operandStack = new Stack<>();

        while (index < splitQuery.size()) {
            String subString = splitQuery.get(index);

            if (isNumber(subString)) {
                Double number = Double.parseDouble(subString);
                PrimitiveOperand numberOperand = new PrimitiveOperand(number);
                operandStack.push(new FilterNode(numberOperand));
            } else if (isString(subString)) {

                String strWithoutQuotes = subString.substring(1, subString.length() - 1);
                PrimitiveOperand stringOperand = new PrimitiveOperand(strWithoutQuotes);
                operandStack.push(new FilterNode(stringOperand));
            } else if (isOperator(subString)) {
                Operator operator = operatorStringToObject(subString);
                FilterNode operatorNode = new FilterNode(operator);
                // if last operator on stack has higher or equal precedence than current operator
                while (!operatorStack.isEmpty() && precedence(operatorObjectToString(operatorStack.peek().getValue())) >= precedence(operatorObjectToString(operatorNode.getValue()))) {
                    // apply operand
                    FilterNode poppedOperatorNode = operatorStack.pop();
                    poppedOperatorNode.right = operandStack.pop();
                    poppedOperatorNode.left = operandStack.pop();
                    operandStack.push(poppedOperatorNode);
                }
                operatorStack.push(operatorNode);
            } else if (Objects.equals(subString, "(")) {
                index++;
                FilterParser subParser = new FilterParser(splitQuery.subList(index, splitQuery.size()));
                FilterNode subTree = subParser.parse();
                operandStack.push(subTree);
                index += subParser.index;
            } else if (Objects.equals(subString, ")")) {
                break;
            } else {
                // if substring is field
                String[] fieldNames = subString.split("\\.");
                ReferenceOperand referenceOperand = new ReferenceOperand(fieldNames);
                operandStack.push(new FilterNode(referenceOperand));
            }

            index++;
        }

        while (!operatorStack.isEmpty()) {
            FilterNode poppedOperatorNode = operatorStack.pop();
            poppedOperatorNode.right = operandStack.pop();
            poppedOperatorNode.left = operandStack.pop();
            operandStack.push(poppedOperatorNode);
        }

        return operandStack.pop();
    }

    public static boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isString(String input) {
        if (input.length() > 0) {
            char firstChar = input.charAt(0);
            char lastChar = input.charAt(input.length() - 1);
            return firstChar == '\'' && lastChar == '\'';
        }
        return false;
    }

    private boolean isOperator(String subString) {
        int p = precedence(subString);
        return p >= 1 && p <= 4;
    }

    private Operator operatorStringToObject(String subString) {
        return switch (subString) {
            case "eq" -> new Equal();
            case "ge" -> new GreaterOrEqual();
            case "gt" -> new GreaterThan();
            case "le" -> new LessOrEqual();
            case "lt" -> new LessThan();
            case "ne" -> new NotEqual();
            case "or" -> new LogicalOr();
            case "and" -> new LogicalAnd();
            default -> throw new IllegalStateException("Unexpected value: " + subString);
        };
    }

    private String operatorObjectToString(FilterValue operator) {
        String result = "";
        if (operator instanceof Equal) {
            result = "eq";
        } else if (operator instanceof GreaterOrEqual) {
            result = "ge";
        } else if (operator instanceof GreaterThan) {
            result = "gt";
        } else if (operator instanceof LessOrEqual) {
            result = "le";
        } else if (operator instanceof LessThan) {
            result = "lt";
        } else if (operator instanceof NotEqual) {
            result = "ne";
        } else if (operator instanceof LogicalOr) {
            result = "or";
        } else if (operator instanceof LogicalAnd) {
            result = "and";
        }
        return result;
    }

    private int precedence(String operator) {
        return switch (operator) {
            case "(", ")" -> 5;
            case "gt", "ge", "lt", "le" -> 4;
            case "eq", "ne" -> 3;
            case "and" -> 2;
            case "or" -> 1;
            default -> 0;
        };
    }
}