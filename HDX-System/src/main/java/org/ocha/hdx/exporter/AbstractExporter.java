package org.ocha.hdx.exporter;

import java.util.ArrayList;
import java.util.List;

import org.ocha.hdx.exporter.QueryData.CHANNEL_KEYS;
import org.ocha.hdx.service.ExporterService;

/**
 * Abstract exporter for report generation.
 * 
 * @author bmichiels
 * 
 * @param <E>
 *            What is this exporter working on (currently : File for CSV and TXT, XSSFWorbook for XLSX).
 * @param <QD>
 *            The information needed to query and gather the report data.
 */
public abstract class AbstractExporter<E, QD extends QueryData> implements Exporter<E, QD> {

	/**
	 * The next exporter in the global export chain (if any). If null, then the current exporter is the final exporter in the global export chain.
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
	 * 
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E export(final E exp, final QD queryData) throws Exception {
		// Keep track of the chain of exporters
		List<String> exporters = (List<String>) queryData.getChannelValue(CHANNEL_KEYS.EXPORTERS);
		if (null == exporters) {
			exporters = new ArrayList<String>();
			queryData.setChannelValue(CHANNEL_KEYS.EXPORTERS, exporters);
		}
		exporters.add(this.getClass().getName());
		if (exporter != null) {
			return exporter.export(exp, queryData);
		} else {
			/*
			// Log the processed exporters
			for (final String exporter_ : exporters) {
				logger.debug("Exporter [" + exporter_ + "] has been processed.");
			}
			// Log the processed indicator types
			final Set<String[]> indicatorTypes = (Set<String[]>) queryData.getChannelValue(CHANNEL_KEYS.INDICATOR_TYPES);
			logger.debug("Indicator types processed :");
			for (final String[] indicatorType : indicatorTypes) {
				logger.debug("\t[" + indicatorType[0] + "] => " + indicatorType[1]);
			}
			*/
			return exp;
		}
	}

	@Override
	public ExporterService getExporterService() {
		return exporterService;
	}
}
