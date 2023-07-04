package org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine.filterpipeline;

import org.queryongenericlist.exceptions.query.queryengine.implementation.subengine.filterengine.FilterEngineException;

public class FilterPipelineException extends FilterEngineException {
    public FilterPipelineException(String message) {
        super(message);
    }

    public FilterPipelineException(String message, Throwable cause) {
        super(message, cause);
    }
}
