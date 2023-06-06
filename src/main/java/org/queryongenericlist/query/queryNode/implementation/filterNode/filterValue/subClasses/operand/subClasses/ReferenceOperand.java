package org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.subClasses;

import lombok.NonNull;
import org.queryongenericlist.query.queryNode.implementation.filterNode.filterValue.subClasses.operand.Operand;

public record ReferenceOperand(String[] fieldNames) implements Operand {
    public ReferenceOperand(@NonNull final String[] fieldNames) {
        this.fieldNames = fieldNames;
    }
}
