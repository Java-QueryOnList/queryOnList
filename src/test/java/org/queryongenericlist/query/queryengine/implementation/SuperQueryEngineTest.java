package org.queryongenericlist.query.queryengine.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.queryongenericlist.exceptions.query.queryengine.implementation.SuperQueryEngineException;
import org.queryongenericlist.query.abstractsyntaxtree.querynode.subnodes.SuperQueryNode;

import java.util.stream.Stream;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class SuperQueryEngineTest {
    SuperQueryEngine classUnderTest;

    @BeforeEach
    void setUp() {
        classUnderTest = new SuperQueryEngine();
    }

    @Test
    void apply_throws_RuntimeException() {
        SuperQueryNode superQueryNode = mock(SuperQueryNode.class);
        Mockito.when(superQueryNode.getFilterNode()).thenThrow(new RuntimeException("Test exception"));

        Assertions.assertThrows(
                RuntimeException.class,
                () -> classUnderTest.apply(
                        superQueryNode,
                        mock(Stream.class)
                )
        );
    }

    @Test
    void apply_throws_SuperQueryEngineException() {
        SuperQueryNode superQueryNode = mock(SuperQueryNode.class);
        Mockito.when(superQueryNode.getFilterNode()).thenThrow(new SuperQueryEngineException("Test exception"));

        Assertions.assertThrows(
                SuperQueryEngineException.class,
                () -> classUnderTest.apply(
                        superQueryNode,
                        mock(Stream.class)
                )
        );
    }
}