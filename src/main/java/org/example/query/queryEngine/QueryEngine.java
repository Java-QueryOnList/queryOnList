package org.example.query.queryEngine;

import java.util.List;

public interface QueryEngine {
    <T> List<T> run(String query, List<T> onList);
}
