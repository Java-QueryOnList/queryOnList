package org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.subclasses;

import lombok.NonNull;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.LeafNode;

public record ReferenceValue(String[] fieldNames) implements LeafNode {
    public ReferenceValue(@NonNull final String[] fieldNames) {
        this.fieldNames = fieldNames;
    }

    @NonNull
    public static ReferenceValue fromSubstring(String subString) {
        String[] fieldNames = subString.split("\\.");
        return new ReferenceValue(fieldNames);
    }
}
