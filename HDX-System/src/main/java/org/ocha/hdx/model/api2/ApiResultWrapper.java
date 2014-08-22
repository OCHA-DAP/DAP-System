/**
 *
 */
package org.ocha.hdx.model.api2;

import java.util.List;

/**
 * @author alexandru-m-g
 *
 */
public class ApiResultWrapper<T> {

	private List<T> results;

	/* used for pagination */
	private Integer totalCount;

	/* used for pagination */
	private Integer currentPage;

	/* used for pagination */
	private Integer totalNumOfPages;

	private boolean success;

	private String errorMessage;

	/* In case pagination is not used, this flag shows if there are more results than were returned */
	private boolean moreResults;



	public ApiResultWrapper(final List<T> results, final Integer totalCount, final Integer currentPage, final Integer totalNumOfPages, final boolean success, final String errorMessage,
			final boolean moreResults) {
		super();
		this.results = results;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.totalNumOfPages = totalNumOfPages;
		this.success = success;
		this.errorMessage = errorMessage;
		this.moreResults = moreResults;
	}

	public List<T> getResults() {
		return this.results;
	}

	public void setResults(final List<T> results) {
		this.results = results;
	}

	public Integer getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(final Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(final Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalNumOfPages() {
		return this.totalNumOfPages;
	}

	public void setTotalNumOfPages(final Integer totalNumOfPages) {
		this.totalNumOfPages = totalNumOfPages;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isMoreResults() {
		return this.moreResults;
	}

	public void setMoreResults(final boolean moreResults) {
		this.moreResults = moreResults;
	}


}
