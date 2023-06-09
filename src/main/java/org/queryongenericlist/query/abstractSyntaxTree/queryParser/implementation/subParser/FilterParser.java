package org.queryongenericlist.query.abstractSyntaxTree.queryParser.implementation.subParser;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.QueryNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.FilterNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.subClasses.PrimitiveValue;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.subClasses.ReferenceValue;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.FilterOperator;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.comparativeOperator.subClasses.*;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.logicalOperator.subClasses.LogicalAnd;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.logicalOperator.subClasses.LogicalOr;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.filterNode.filterOperator.negativeOperator.NegativeOperator;
import org.queryongenericlist.query.abstractSyntaxTree.queryParser.QueryParser;
import org.queryongenericlist.utils.StringParser;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class FilterParser implements QueryParser<FilterNode> {

    private static final String SPLIT_PATTERN = "([\\w.]+|'.+?'|\\S)"; //regex for any token of the query, whether operator or operand
    final private List<String> splitQuery;
    private int index;
    private int currentBoundary;

    public FilterParser(@NonNull final String query) {
        this.splitQuery = StringParser.getAll(query, SPLIT_PATTERN);
        this.index = 0;
        this.currentBoundary = splitQuery.size();
    }

    public FilterParser(@NonNull final List<String> splitQuery) {
        this.splitQuery = splitQuery;
        this.index = 0;
        this.currentBoundary = splitQuery.size();
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

    @Override
    public @NonNull FilterNode parse() {
        final Stack<FilterNode> operatorStack = new Stack<>();
        final Stack<FilterNode> operandStack = new Stack<>();

        while (index < splitQuery.size() && index < currentBoundary) {
            final String subString = splitQuery.get(index);
            if (Objects.equals(subString, "(")) {
                index++;
                final FilterParser subParser = new FilterParser(splitQuery.subList(index, splitQuery.size()));
                final FilterNode subTree = subParser.parse();
                operandStack.push(subTree);
                index += subParser.index;
            } else if (Objects.equals(subString, ")")) {
                break;
            } else if (Objects.equals(subString, "not")) {
                final FilterNode negationNode = new FilterNode(new NegativeOperator());
                final int nextIndex = index + 1;
                final FilterParser subParser = new FilterParser(splitQuery.subList(nextIndex, splitQuery.size()));
                if (!splitQuery.get(nextIndex).equals("(")) {
                    // if next token after "not" is not a bracket then limit the boundary of subParser to only 3 more
                    subParser.currentBoundary = nextIndex + 3;
                }
                final FilterNode subTree = subParser.parse();
                negationNode.setTailRight(subTree);
                operandStack.push(negationNode);
                index += subParser.index;
            } else if (isOperator(subString)) {
                final FilterOperator operator = operatorStringToObject(subString);
                final FilterNode operatorNode = new FilterNode(operator);
                // if last operator on stack has higher or equal precedence than current operator
                while (!operatorStack.isEmpty() && precedence(operatorObjectToString(operatorStack.peek().getHead())) >= precedence(operatorObjectToString(operatorNode.getHead()))) {
                    // apply operand
                    final FilterNode poppedOperatorNode = getNodeFromStacks(operatorStack, operandStack);
                    operandStack.push(poppedOperatorNode);
                }
                operatorStack.push(operatorNode);
            } else if (isNumber(subString)) {
                final Double number = Double.parseDouble(subString);
                final PrimitiveValue numberOperand = new PrimitiveValue(number);
                operandStack.push(new FilterNode(numberOperand));
            } else if (isString(subString)) {
                final String strWithoutQuotes = subString.substring(1, subString.length() - 1);
                final PrimitiveValue stringOperand = new PrimitiveValue(strWithoutQuotes);
                operandStack.push(new FilterNode(stringOperand));
            } else if (subString.equals("true") || subString.equals("false")){
                // if substring is boolean
                final PrimitiveValue booleanOperand = new PrimitiveValue(subString.equals("true"));
                operandStack.push(new FilterNode(booleanOperand));
            } else {
                // if substring is field
                final String[] fieldNames = subString.split("\\.");
                final ReferenceValue referenceOperand = new ReferenceValue(fieldNames);
                operandStack.push(new FilterNode(referenceOperand));
            }

            index++;
        }

        while (!operatorStack.isEmpty()) {
            final FilterNode poppedOperatorNode = getNodeFromStacks(operatorStack, operandStack);
            operandStack.push(poppedOperatorNode);
        }

        return operandStack.pop();
    }

    private static FilterNode getNodeFromStacks(Stack<FilterNode> operatorStack, Stack<FilterNode> operandStack) {
        // get instance for node
        FilterNode poppedOperatorNode = operatorStack.pop();

        // set left and right of node
        poppedOperatorNode.setTailRight(operandStack.pop());
        poppedOperatorNode.setTailLeft(operandStack.pop());

        return poppedOperatorNode;
    }

    private boolean isOperator(@NonNull final String subString) {
        final int p = precedence(subString);
        return p >= 1 && p <= 4;
    }

    @NonNull
    private FilterOperator operatorStringToObject(@NonNull final String subString) {
        return switch (subString) {
            case "eq" -> new Equal();
            case "ge" -> new GreaterOrEqual();
            case "gt" -> new GreaterThan();
            case "le" -> new LessOrEqual();
            case "lt" -> new LessThan();
            case "ne" -> new NotEqual();
            case "not" -> new NegativeOperator();
            case "or" -> new LogicalOr();
            case "and" -> new LogicalAnd();
            default -> throw new IllegalStateException("Unexpected value: " + subString);
        };
    }

    @NonNull
    private String operatorObjectToString(@NonNull final QueryNode operator) {
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
        } else if (operator instanceof NegativeOperator) {
            result = "not";
        } else if (operator instanceof LogicalOr) {
            result = "or";
        } else if (operator instanceof LogicalAnd) {
            result = "and";
        }

        return result;
    }

    private int precedence(@NonNull final String operator) {
        return switch (operator) {
            case "(", ")" -> 6;
            case "not" -> 5;
            case "gt", "ge", "lt", "le" -> 4;
            case "eq", "ne" -> 3;
            case "and" -> 2;
            case "or" -> 1;
            default -> 0;
        };
    }

}