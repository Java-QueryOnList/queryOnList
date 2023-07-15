package testHelpers.genericClasses.classDecleration.datatypeholder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DataTypeHolder {
    private boolean booleanField;
    private byte byteField;
    private short shortField;
    private int intField;
    private long longField;
    private float floatField;
    private double doubleField;
    private char charField;

    private Object objectField;
    private String stringField;
    private Number numberField;
    private Boolean booleanObjectField;
    private Byte byteObjectField;
    private Short shortObjectField;
    private Integer integerObjectField;
    private Long longObjectField;
    private Float floatObjectField;
    private Double doubleObjectField;
    private Character characterField;
    private Void voidField;

    private int[] intArrayField;
    private String[] stringArrayField;
    private Object[] objectArrayField;

    // Custom user-defined type
    private CustomClass customClassField;

    @Data
    @AllArgsConstructor
    public static class CustomClass {
        // fields and methods of your custom class
        private int customField;
    }
}