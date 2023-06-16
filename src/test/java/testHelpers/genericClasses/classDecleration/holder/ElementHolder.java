package testHelpers.genericClasses.classDecleration.holder;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ElementHolder {
    private int intValue;
    private double doubleValue;
    private String stringValue;
    private boolean booleanValue;
    private char charValue;
    private byte byteValue;
    private short shortValue;
    private long longValue;
    private float floatValue;
    private Object objectValue;
}
