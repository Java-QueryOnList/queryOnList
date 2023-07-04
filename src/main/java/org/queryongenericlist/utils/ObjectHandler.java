package org.queryongenericlist.utils;

import lombok.NonNull;
import org.queryongenericlist.exceptions.utils.objecthandler.IfArrayConvertToListException;
import org.queryongenericlist.exceptions.utils.objecthandler.ResolveObjectException;
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
        try {
            PrimitiveValue primitiveValue = null;
            if (leafValue instanceof PrimitiveValue) {
                primitiveValue = (PrimitiveValue) leafValue;
            } else if (leafValue instanceof ReferenceValue) {
                primitiveValue = GenericClassHelper.extractAllFields(element, ((ReferenceValue) leafValue).fieldNames());
            }

            assert primitiveValue != null;
            return primitiveValue.value();
        } catch (Throwable throwable) {
            if (throwable instanceof ResolveObjectException) {
                throw throwable;
            } else {
                throw new ResolveObjectException("Error while resolving object: " + element, throwable);
            }
        }
    }

    @NonNull
    public static Object ifArrayConvertToList(@NonNull Object maybeArrayOrSet) {
        try {
            if (maybeArrayOrSet.getClass().isArray()) {
                List<Object> list = new ArrayList<>();
                int length = Array.getLength(maybeArrayOrSet);
                for (int i = 0; i < length; i++) {
                    Object item = Array.get(maybeArrayOrSet, i);
                    list.add(item);
                }
                maybeArrayOrSet = list;
            }
            return maybeArrayOrSet;
        } catch (Throwable throwable) {
            if (throwable instanceof IfArrayConvertToListException) {
                throw throwable;
            } else {
                throw new IfArrayConvertToListException("Error while trying to convert to list if it is of instance array: " + maybeArrayOrSet, throwable);
            }
        }
    }
}
