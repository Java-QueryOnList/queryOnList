package org.queryongenericlist.query.result;

import lombok.Getter;
import org.queryongenericlist.query.queryexecutor.QueryExecutor;
import org.queryongenericlist.query.queryexecutor.implementation.SuperQueryExecutor;

import java.util.List;

@Getter
public class PaginatedResult<T> {
    final private List<T> result;
    final private String filterQuery;
    final private String orderByQuery;
    final private String paginationQuery;
    final private Integer currentSkip;
    final private Integer currentTop;
    final private String baseUrl;

    // Constructor
    public PaginatedResult(
            List<T> result,
            String filterQuery,
            String orderByQuery,
            String paginationQuery,
            Integer currentSkip,
            Integer currentTop,
            String baseUrl
    ) {
        this.result = result;
        this.filterQuery = filterQuery;
        this.orderByQuery = orderByQuery;
        this.paginationQuery = paginationQuery;
        this.currentSkip = currentSkip;
        this.currentTop = currentTop;
        this.baseUrl = baseUrl;
    }

    /**
     * Calculate the current page number based on the current skip and top
     *
     * @return the current page number
     */
    public int getCurrentPageNumber() {
        if (!hasPagination()) {
            return 1;
        }
        return (int) Math.ceil((double) (currentSkip + 1) / currentTop);
    }

    private boolean hasPagination() {
        return currentSkip != null && currentTop != null && currentTop != 0;
    }

    /**
     * While {@link #currentTop} returns the current limit of items per page, this method returns the actual current number of items which can be less than the limit
     *
     * @return the total number of items of the result list
     */
    public int getCurrentCount() {
        return result.size();
    }

    /**
     * Get the next page.
     *
     * @param onList the raw list of items
     * @return the next page as a PaginatedResult<T> or null if the current page is the last one
     */
    public PaginatedResult<T> getNextPageResult(List<T> onList) {

        PaginatedResult<T> paginatedResult = null;

        if (hasPagination()) {
            // Execute the query
            QueryExecutor executor = new SuperQueryExecutor();
            int nextSkip = currentSkip + currentTop;
            String nextPaginationQuery = "$skip=" + nextSkip + "&$top=" + currentTop;
            String nextQuery = getQueryOfPage(nextPaginationQuery);
            List<T> nextPageResult = executor.execute(nextQuery, onList);

            // Create the new paginated result
            paginatedResult = new PaginatedResult<>(
                    nextPageResult,
                    filterQuery,
                    orderByQuery,
                    nextPaginationQuery,
                    nextSkip,
                    currentTop,
                    baseUrl
            );
        }
        return paginatedResult;
    }

    private String getQueryOfPage(String newPaginationQuery) {
        StringBuilder queryBuilder = new StringBuilder();

        // Add the filter query
        if (filterQuery != null && !filterQuery.isEmpty()) {
            queryBuilder.append(filterQuery);
        }

        // Add the order by query
        if (orderByQuery != null && !orderByQuery.isEmpty()) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append("&");
            }
            queryBuilder.append(orderByQuery);
        }

        // Add the new pagination query
        if (hasPagination()) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append("&");
            }
            queryBuilder.append(newPaginationQuery);
        }

        return queryBuilder.toString();
    }


    /**
     * Get the next page url.
     *
     * @return the next page url or null if the current page is the last one
     */
    public String getNextPageUrl() {
        String nextPageUrl = null;
        if (baseUrl != null) {
            int nextSkip = currentSkip + currentTop;
            String nextPaginationQuery = "$skip=" + nextSkip + "&$top=" + currentTop;
            nextPageUrl = baseUrl + "?" + getQueryOfPage(nextPaginationQuery);
        }
        return nextPageUrl;
    }
}
