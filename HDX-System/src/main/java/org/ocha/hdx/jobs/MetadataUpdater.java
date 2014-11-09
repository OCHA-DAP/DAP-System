package org.ocha.hdx.jobs;

import org.ocha.hdx.service.HDXService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MetadataUpdater implements Runnable {

	private static final Logger log = LoggerFactory.getLogger(MetadataUpdater.class);

	@Autowired
	private HDXService hdxService;

	@Override
	@Scheduled(fixedDelay = 100000, initialDelay = 100000)
	public void run() {
		try {
			// ...

		} catch (final Throwable e) {
			log.error(e.toString(), e);
		}
	}

}
