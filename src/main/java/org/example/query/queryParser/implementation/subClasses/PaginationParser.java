package org.example.query.queryParser.implementation.subClasses;

import lombok.NonNull;
import org.example.query.queryNode.implementation.paginationNode.PaginationNode;
import org.example.query.queryParser.implementation.QueryParserImpl;

public class PaginationParser extends QueryParserImpl {
    /// TODO: Implement this
    static public String patternTop = "\\$top=(\\d+)";
    static public String patternSkip = "\\skip=(\\d+)";
    private final String top;
    private final String skip;
    int index;

    public PaginationParser(@NonNull final String top, @NonNull final String skip) {
        super(top + " " + skip);
        this.top = top;
        this.skip = skip;
    }

    @NonNull
    public PaginationNode parse() {
        return null;
    }

}
