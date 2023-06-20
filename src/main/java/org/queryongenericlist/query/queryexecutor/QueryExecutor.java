package org.queryongenericlist.query.queryexecutor;

import lombok.NonNull;

import java.util.List;

public interface QueryExecutor {

    @NonNull
    <T> List<T> execute(@NonNull final String query, List<T> onList);

}
