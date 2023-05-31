package org.example.query.queryParser
        ;

import lombok.NonNull;
import org.example.query.queryNode.QueryNode;

public interface QueryParser {

    @NonNull
    QueryNode parse();

}
