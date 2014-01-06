package org.ocha.dap.tools;

import java.io.Closeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOTools {

	private static final Logger log = LoggerFactory.getLogger(IOTools.class);

	/**
	 * Attempts to close the resource.
	 * 
	 * @param resource
	 *            May be null.
	 */
	public static void closeResource(final Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (final Throwable e) {
				final String caller = String.format("%s.%s()", e.getStackTrace()[1].getClassName(), e.getStackTrace()[1].getMethodName());
				final String message = String.format("Failed to close resource=%s, cause=%s, caller=%s", resource, e.getCause(), caller);
				if (log.isDebugEnabled()) {
					// Log more details when debug is enabled.
					log.debug(message, e);
				} else {
					log.debug(message);
				}
			}
		}
	}

}
