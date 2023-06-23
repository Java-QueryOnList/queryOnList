package org.queryongenericlist.utils;

import lombok.NonNull;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.subclasses.PrimitiveValue;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.leafnode.subclasses.ReferenceValue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ObjectHandler {

    /**
     * returns the primitive value of a given object from the leafValue object
     *
     * @param leafValue the value of the last node of an abstract syntax tree
     * @param element   an object of the class of the element whose field has to be extracted
     * @param <T>       class type of the object which is going to be resolved
     * @return return the value of the given object which is primitive which we checked via {@link Primitives#isPrimitive(Object)}
     */
    public static <T> Object resolveObject(@NonNull final Object leafValue, @NonNull T element) {
        PrimitiveValue primitiveValue = null;
        if (leafValue instanceof PrimitiveValue) {
            primitiveValue = (PrimitiveValue) leafValue;
        } else if (leafValue instanceof ReferenceValue) {
            primitiveValue = GenericClassHelper.extractAllFields(element, ((ReferenceValue) leafValue).fieldNames());
        }

        assert primitiveValue != null;
        return primitiveValue.value();
    }

    @NonNull
    public static Object ifArrayConvertToList(@NonNull Object maybeArray) {
        if (maybeArray.getClass().isArray()) {
            List<Object> list = new ArrayList<>();
            int length = Array.getLength(maybeArray);
            for (int i = 0; i < length; i++) {
                Object item = Array.get(maybeArray, i);
                list.add(item);
            }
            maybeArray = list;
        }
        return maybeArray;
    }
}
