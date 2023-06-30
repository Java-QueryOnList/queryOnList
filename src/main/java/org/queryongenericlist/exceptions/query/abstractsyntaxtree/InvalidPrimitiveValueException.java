package org.queryongenericlist.exceptions.query.abstractsyntaxtree;

import java.util.List;

public class InvalidPrimitiveValueException extends IllegalArgumentException {

    public InvalidPrimitiveValueException(String message, Object invalidValue, List<String> supportedTypes) {
        super(buildMessage(message, invalidValue, supportedTypes));
    }

    private static String buildMessage(String message, Object invalidValue, List<String> supportedTypes) {
        StringBuilder messageBuilder = new StringBuilder(message);
        messageBuilder.append(invalidValue);
        messageBuilder.append("\n");
        messageBuilder.append("Supported primitive types are: ");
        messageBuilder.append(String.join(", ", supportedTypes));
        return messageBuilder.toString();
    }
}
