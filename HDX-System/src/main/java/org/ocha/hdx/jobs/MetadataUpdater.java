package org.ocha.hdx.jobs;

import org.ocha.hdx.service.CkanSynchronizerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MetadataUpdater implements Runnable {

	private static final Logger log = LoggerFactory.getLogger("ckan-updater-logger");

	@Autowired
	private CkanSynchronizerService ckanSynchronizerServiceImpl;

	@Override
	@Scheduled(fixedDelay = 100000, initialDelay = 1000)
	public void run() {
		try {
			log.debug("Starting MetadataUpdater.run");
			ckanSynchronizerServiceImpl.updateMetadataToCkan();
			log.debug("After MetadataUpdater.run");

		} catch (final Throwable e) {
			log.error(e.toString(), e);
		}
	}

}
