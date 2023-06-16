package testHelpers.genericClasses.classDecleration.holder;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ArrayHolder {
    final private int[] intArray;
    final private double[] doubleArray;
    final private String[] stringArray;
    final private boolean[] booleanArray;
    final private char[] charArray;
    final private byte[] byteArray;
    final private short[] shortArray;
    final private long[] longArray;
    final private float[] floatArray;
    final private Object[] objectArray;
}
