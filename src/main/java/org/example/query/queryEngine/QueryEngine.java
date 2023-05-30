package org.example.query.queryEngine;

import lombok.NonNull;

import java.util.List;

public interface QueryEngine {

    @NonNull
    <T> List<T> run(@NonNull final String query, List<T> onList);

}
