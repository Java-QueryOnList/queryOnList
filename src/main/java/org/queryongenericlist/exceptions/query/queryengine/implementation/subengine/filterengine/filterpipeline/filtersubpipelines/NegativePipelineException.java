package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine.filterpipeline.filtersubpipelines;

import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine.filterpipeline.FilterPipelineException;

public class NegativePipelineException extends FilterPipelineException {
    public NegativePipelineException(String message) {
        super(message);
    }

    public NegativePipelineException(String message, Throwable cause) {
        super(message, cause);
    }
}
