package edu.drexel.util;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MappingJackson2StringMarshaller {
	
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	private ObjectMapper objectMapper = new ObjectMapper();
	private Boolean prettyPrint;
	
	public MappingJackson2StringMarshaller setPrettyPrint(boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
		configurePrettyPrint();
		
		return this;
	}
	
	private void configurePrettyPrint() {
		if (this.prettyPrint != null) {
			this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, this.prettyPrint);
		}
	}
	
	public ObjectMapper getObjectMapper() {
		return this.objectMapper;
	}
	
	public MappingJackson2StringMarshaller setObjectMapper(ObjectMapper objectMapper) {
		if (objectMapper == null) {
			throw new IllegalArgumentException("ObjectMapper must not be null");
		}
		
		this.objectMapper = objectMapper;
		configurePrettyPrint();
		
		return this;
	}
	
	public String marshall(Object object) {
		StringWriter writer = new StringWriter();
		JsonGenerator jsonGenerator;
		try {
			jsonGenerator = this.objectMapper.getFactory().createGenerator(writer);
			this.objectMapper.writeValue(jsonGenerator, object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		if (this.objectMapper.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
			jsonGenerator.useDefaultPrettyPrinter();
		}
		
		return writer.toString();
	}
}
