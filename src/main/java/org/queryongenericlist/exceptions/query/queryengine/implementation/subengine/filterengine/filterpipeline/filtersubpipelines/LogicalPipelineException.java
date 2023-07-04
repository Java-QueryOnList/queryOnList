package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine.filterpipeline.filtersubpipelines;

import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine.filterpipeline.FilterPipelineException;

public class LogicalPipelineException extends FilterPipelineException {
    public LogicalPipelineException(String message) {
        super(message);
    }

    public LogicalPipelineException(String message, Throwable cause) {
        super(message, cause);
    }
}
