package org.example.query.queryParser.implementation.subClasses;

import lombok.NonNull;
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

    private static final String SPLIT_PATTERN = "([\\w.]+|'.+?'|\\S)"; //regex for any token of the query, whether operator or operand
    int index;
    private List<String> splitQuery;

    public FilterParser(@NonNull final String query) {
        super(query);
        this.splitQuery = StringParser.getAll(query, SPLIT_PATTERN);
        this.index = 0;
    }

    public FilterParser(@NonNull final String query, final int index) {
        super(query);
        splitQuery = StringParser.getAll(query, SPLIT_PATTERN);
        this.index = index;
    }

    public FilterParser(@NonNull final List<String> splitQuery) {
        super(String.join(" ", splitQuery));
        this.splitQuery = splitQuery;
        this.index = 0;
    }

    public static boolean isNumber(@NonNull final String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isString(@NonNull final String input) {
        if (input.length() > 0) {
            final char firstChar = input.charAt(0);
            final char lastChar = input.charAt(input.length() - 1);
            return firstChar == '\'' && lastChar == '\'';
        }
        return false;
    }

    @NonNull
    public FilterNode parse() {
        final Stack<FilterNode> operatorStack = new Stack<>();
        final Stack<FilterNode> operandStack = new Stack<>();

        while (index < splitQuery.size()) {
            final String subString = splitQuery.get(index);

            if (isNumber(subString)) {
                final Double number = Double.parseDouble(subString);
                final PrimitiveOperand numberOperand = new PrimitiveOperand(number);
                operandStack.push(new FilterNode(numberOperand));
            } else if (isString(subString)) {

                final String strWithoutQuotes = subString.substring(1, subString.length() - 1);
                final PrimitiveOperand stringOperand = new PrimitiveOperand(strWithoutQuotes);
                operandStack.push(new FilterNode(stringOperand));
            } else if (isOperator(subString)) {
                final Operator operator = operatorStringToObject(subString);
                final FilterNode operatorNode = new FilterNode(operator);
                // if last operator on stack has higher or equal precedence than current operator
                while (!operatorStack.isEmpty() && precedence(operatorObjectToString(operatorStack.peek().getValue())) >= precedence(operatorObjectToString(operatorNode.getValue()))) {
                    // apply operand
                    final FilterNode poppedOperatorNode = operatorStack.pop();
                    poppedOperatorNode.setRight(operandStack.pop());
                    poppedOperatorNode.setLeft(operandStack.pop());
                    operandStack.push(poppedOperatorNode);
                }
                operatorStack.push(operatorNode);
            } else if (Objects.equals(subString, "(")) {
                index++;
                final FilterParser subParser = new FilterParser(splitQuery.subList(index, splitQuery.size()));
                final FilterNode subTree = subParser.parse();
                operandStack.push(subTree);
                index += subParser.index;
            } else if (Objects.equals(subString, ")")) {
                break;
            } else {
                // if substring is field
                final String[] fieldNames = subString.split("\\.");
                final ReferenceOperand referenceOperand = new ReferenceOperand(fieldNames);
                operandStack.push(new FilterNode(referenceOperand));
            }

            index++;
        }

        while (!operatorStack.isEmpty()) {
            final FilterNode poppedOperatorNode = operatorStack.pop();
            poppedOperatorNode.setRight(operandStack.pop());
            poppedOperatorNode.setLeft(operandStack.pop());
            operandStack.push(poppedOperatorNode);
        }

        return operandStack.pop();
    }

    private boolean isOperator(@NonNull final String subString) {
        final int p = precedence(subString);
        return p >= 1 && p <= 4;
    }

    @NonNull
    private Operator operatorStringToObject(@NonNull final String subString) {
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

    @NonNull
    private String operatorObjectToString(@NonNull final FilterValue operator) {
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

    private int precedence(@NonNull final String operator) {
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