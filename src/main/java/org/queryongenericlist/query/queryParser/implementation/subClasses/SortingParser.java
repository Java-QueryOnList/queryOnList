package org.queryongenericlist.query.queryParser.implementation.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.sortingNode.SortingNode;
import org.queryongenericlist.query.queryParser.QueryParser;
import org.queryongenericlist.query.queryParser.implementation.SuperQueryParser;

public class SortingParser implements QueryParser<SortingNode> {

    static public String pattern = "\\$orderBy=([^(&)]+)";
    private int index;

    public SortingParser(@NonNull final String query) {
        this.index = 0;
    }

    public SortingParser(String query, int index) {
        this.index = index;
    }

    @NonNull
    public SortingNode parse() {
        return null;
    }

}
