package org.example.query.queryParser.implementation.subClasses;

import org.example.query.queryNode.implementation.paginationNode.PaginationNode;
import org.example.query.queryParser.implementation.QueryParserImpl;

public class PaginationParser extends QueryParserImpl {
    String top;
    String skip;
    int index;
    /// TODO: Implement this
    static public String patternTop = "\\$top=(\\d+)";
    static public String patternSkip = "\\skip=(\\d+)";

    public PaginationParser(String top, String skip) {
        super(top + " " + skip);
        this.top = top;
        this.skip = skip;
    }

    public PaginationNode parse() {
        return null;
    }
}
