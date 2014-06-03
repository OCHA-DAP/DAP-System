/**
 *
 */
package org.ocha.hdx.validation.exception;

/**
 * @author alexandru-m-g
 * 
 */
public class WrongParametersForValidationException extends RuntimeException {

	private static final long serialVersionUID = 6595363693385816880L;

	/**
	 *
	 */
	public WrongParametersForValidationException() {
	}

	/**
	 * @param message
	 */
	public WrongParametersForValidationException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public WrongParametersForValidationException(final Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WrongParametersForValidationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public WrongParametersForValidationException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
