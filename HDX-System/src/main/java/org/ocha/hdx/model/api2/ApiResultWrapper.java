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

	private boolean success;

	private String errorMessage;

	/* used for pagination */
	private Integer totalCount;

	/* used for pagination */
	private Integer currentPage;

	/* used for pagination */
	private Integer totalNumOfPages;

	/* In case pagination is not used, this flag shows if there are more results than were returned */
	private boolean moreResults;

	private List<T> results;

	public ApiResultWrapper(final String errorMessage){
		this.success = false;
		this.errorMessage = errorMessage;
	}


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


	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return this.success;
	}


	/**
	 * @param success the success to set
	 */
	public void setSuccess(final boolean success) {
		this.success = success;
	}


	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return this.errorMessage;
	}


	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}


	/**
	 * @return the totalCount
	 */
	public Integer getTotalCount() {
		return this.totalCount;
	}


	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(final Integer totalCount) {
		this.totalCount = totalCount;
	}


	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return this.currentPage;
	}


	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(final Integer currentPage) {
		this.currentPage = currentPage;
	}


	/**
	 * @return the totalNumOfPages
	 */
	public Integer getTotalNumOfPages() {
		return this.totalNumOfPages;
	}


	/**
	 * @param totalNumOfPages the totalNumOfPages to set
	 */
	public void setTotalNumOfPages(final Integer totalNumOfPages) {
		this.totalNumOfPages = totalNumOfPages;
	}


	/**
	 * @return the moreResults
	 */
	public boolean isMoreResults() {
		return this.moreResults;
	}


	/**
	 * @param moreResults the moreResults to set
	 */
	public void setMoreResults(final boolean moreResults) {
		this.moreResults = moreResults;
	}


	/**
	 * @return the results
	 */
	public List<T> getResults() {
		return this.results;
	}


	/**
	 * @param results the results to set
	 */
	public void setResults(final List<T> results) {
		this.results = results;
	}



}
