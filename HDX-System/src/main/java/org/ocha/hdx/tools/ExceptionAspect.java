package org.ocha.hdx.tools;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class ExceptionAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);

	public static Object handle(final ProceedingJoinPoint pjp) throws Throwable {
		try {
			return pjp.proceed();
		} catch (final Throwable t) {
			// so something with t: log, wrap, return default, ...
			LOGGER.debug("invocation of " + pjp.getSignature().toLongString() + " failed", t);
			throw t;
		}
	}
}
