package org.ocha.hdx.exporter;

import java.util.HashMap;
import java.util.Map;

/**
 * This class specify the criteria for the data to be collected. It should be subclassed for every report type.
 * 
 * @author bmichiels
 * 
 */
public abstract class QueryData {
	
	/**
	 * Channel keys allowing communication between exporters.
	 * @author bmichiels
	 *
	 */
	public enum CHANNEL_KEYS {
		EXPORTERS,
		DATA_SERIES
	}

	/**
	 * Communication channel through all exporters.
	 */
	private final Map<CHANNEL_KEYS, Object> channel = new HashMap<>();

	/**
	 * Get a channel value.
	 * @param key
	 * @return
	 */
	public Object getChannelValue(final CHANNEL_KEYS key) {
		return channel.get(key);
	}

	/**
	 * Set a channel value.
	 * @param key
	 * @param value
	 */
	public void setChannelValue(final CHANNEL_KEYS key, final Object value) {
		channel.put(key, value);
	}
}
