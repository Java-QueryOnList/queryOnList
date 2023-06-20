package org.queryongenericlist.query.abstractsyntaxtree.queryparser.implementation.subparser;

import lombok.NonNull;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.paginationnode.PaginationNode;
import org.queryongenericlist.query.abstractsyntaxtree.queryparser.QueryParser;

public class PaginationParser implements QueryParser<PaginationNode> {
    private final String top;
    private final String skip;

    public PaginationParser(final String top, final String skip) {
        this.top = top;
        this.skip = skip;
    }

    @NonNull
    public PaginationNode parse() {
        int top = this.top != null ? Integer.parseInt(this.top) : 0;
        int skip = this.skip != null ? Integer.parseInt(this.skip) : 0;
        PaginationNode paginationNode = new PaginationNode(top, skip);
        return paginationNode;
    }

}
