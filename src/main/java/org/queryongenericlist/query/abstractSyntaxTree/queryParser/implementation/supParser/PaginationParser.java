package org.queryongenericlist.query.abstractSyntaxTree.queryParser.implementation.supParser;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.subNodes.paginationNode.PaginationNode;
import org.queryongenericlist.query.abstractSyntaxTree.queryParser.QueryParser;

public class PaginationParser implements QueryParser<PaginationNode> {
    /// TODO: Implement this
    static public String patternTop = "\\$top=(\\d+)";
    static public String patternSkip = "\\skip=(\\d+)";
    private final String top;
    private final String skip;
    int index;

    public PaginationParser(@NonNull final String top, @NonNull final String skip) {
        this.top = top;
        this.skip = skip;
    }

    @NonNull
    public PaginationNode parse() {
        return null;
    }

}
