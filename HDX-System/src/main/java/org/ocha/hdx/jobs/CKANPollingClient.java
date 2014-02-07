package org.ocha.hdx.jobs;

import org.ocha.hdx.service.HDXService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CKANPollingClient implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(CKANPollingClient.class);

	@Autowired
	private HDXService hdxService;

	@Override
	// @Scheduled(fixedDelay = 100000, initialDelay = 100000)
	public void run() {
		try {
			// for now, just a simple get, no JSON POST
			// FIXME we should have the name of the dataset here, either static
			// or
			// discovered
			hdxService.getDatasetsListFromCKAN(null);
			// FIXME do something with it
		} catch (final Throwable e) {
			log.error(e.toString(), e);
		}
	}

}
