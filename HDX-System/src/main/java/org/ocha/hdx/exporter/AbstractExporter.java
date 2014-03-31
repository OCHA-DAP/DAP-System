package org.ocha.hdx.exporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ocha.hdx.exporter.QueryData.CHANNEL_KEYS;
import org.ocha.hdx.service.ExporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract exporter for report generation.
 * 
 * @author bmichiels
 * 
 * @param <E>
 *            What is this exporter working on (currently : File for PDF, XSSFWorbook for XLSX).
 * @param <QD>
 *            The information needed to query and gather the report data.
 */
public abstract class AbstractExporter<E, QD extends QueryData> implements Exporter<E, QD> {

	private static Logger logger = LoggerFactory.getLogger(AbstractExporter.class);
	
	/**
	 * The next exporter in the global export chain (if any).
	 * If null, then the current exporter is the final exporter in the global export chain. 
	 */
	protected final Exporter<E, QD> exporter;
	
	/**
	 * The service needed to perform the report generation.
	 */
	protected final ExporterService exporterService;

	/**
	 * This constructor is for the final exporter of a chain of exporters. It propagates the exporter service to its predecessors for proper generation.
	 * 
	 * @param exporterService
	 */
	public AbstractExporter(final ExporterService exporterService) {
		super();
		exporter = null;
		this.exporterService = exporterService;
	}

	/**
	 * This constructor takes the following exporter in the global export chain.
	 * 
	 * @param exporter
	 */
	public AbstractExporter(final Exporter<E, QD> exporter) {
		super();
		this.exporter = exporter;
		this.exporterService = exporter.getExporterService();
	}

	/**
	 * Generate the report.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E export(final E incomingData, final QD queryData) {
		List<String> exporters = (List<String>) queryData.getChannelValue(CHANNEL_KEYS.EXPORTERS);
		if(null == exporters) {
			exporters = new ArrayList<String>();
			queryData.setChannelValue(CHANNEL_KEYS.EXPORTERS, exporters);
		}
		exporters.add(this.getClass().getName());
		if (exporter != null) {
			return exporter.export(incomingData, queryData);
		} else {
			/*
			for (final String exporter_ : exporters) {
				logger.debug("Exporter [" + exporter_ + "] has been processed.");
			}
			final Set<String[]> indicatorTypes = (Set<String[]>) queryData.getChannelValue(CHANNEL_KEYS.INDICATOR_TYPES);
			logger.debug("Indicator types processed :");
			for (final String[] indicatorType : indicatorTypes) {
				logger.debug("\t[" + indicatorType[0] + "] => " + indicatorType[1]);
			}
			*/
			return incomingData;
		}
	}

	@Override
	public ExporterService getExporterService() {
		return exporterService;
	}
}
