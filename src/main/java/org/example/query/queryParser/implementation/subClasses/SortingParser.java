package org.example.query.queryParser.implementation.subClasses;

import org.example.query.queryNode.implementation.sortingNode.SortingNode;
import org.example.query.queryParser.implementation.QueryParserImpl;

public class SortingParser extends QueryParserImpl {
    String query;
    int index;

    static public String pattern = "\\$orderBy=([^(&)]+)";

    public SortingParser(String query) {
        super(query);
        this.index = 0;
    }

    public SortingParser(String query, int index) {
        super(query);
        this.index = index;
    }
    public SortingNode parse() {
        return null;
    }
}
