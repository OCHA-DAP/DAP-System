package org.ocha.hdx.rest.helper;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
@Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
public class XSSFWorkbookWriter implements MessageBodyWriter<XSSFWorkbook> {

	private static Logger logger = LoggerFactory.getLogger(XSSFWorkbookWriter.class);

	@Override
	public boolean isWriteable(final Class<?> type, final Type genericType, final Annotation[] annotations, final MediaType mediaType) {
		return type == XSSFWorkbook.class;
	}

	@Override
	public long getSize(final XSSFWorkbook t, final Class<?> type, final Type genericType, final Annotation[] annotations, final MediaType mediaType) {
		// deprecated by JAX-RS 2.0 and ignored by Jersey runtime
		return 0;
	}

	@Override
	public void writeTo(final XSSFWorkbook t, final Class<?> type, final Type genericType, final Annotation[] annotations, final MediaType mediaType, final MultivaluedMap<String, Object> httpHeaders,
			final OutputStream entityStream) throws IOException, WebApplicationException {
		logger.debug("Writing XLSX workbook...");
		t.write(entityStream);
		logger.debug("XLSX workbook written.");
	}
}