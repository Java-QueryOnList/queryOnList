package org.queryongenericlist.query.queryParser.implementation.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.sortingNode.SortingNode;
import org.queryongenericlist.query.queryParser.implementation.QueryParserImpl;

public class SortingParser extends QueryParserImpl {

    static public String pattern = "\\$orderBy=([^(&)]+)";
    private int index;

    public SortingParser(@NonNull final String query) {
        super(query);
        this.index = 0;
    }

    public SortingParser(String query, int index) {
        super(query);
        this.index = index;
    }

    @NonNull
    public SortingNode parse() {
        return null;
    }

}
