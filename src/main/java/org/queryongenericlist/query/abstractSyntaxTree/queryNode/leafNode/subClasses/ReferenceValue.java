package org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.abstractSyntaxTree.queryNode.leafNode.LeafNode;

public record ReferenceValue(String[] fieldNames) implements LeafNode {
    public ReferenceValue(@NonNull final String[] fieldNames) {
        this.fieldNames = fieldNames;
    }

    public static ReferenceValue fromSubstring(String subString) {
        String[] fieldNames = subString.split("\\.");
        return new ReferenceValue(fieldNames);
    }
}
