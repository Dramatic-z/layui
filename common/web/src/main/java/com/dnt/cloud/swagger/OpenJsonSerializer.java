package com.dnt.cloud.swagger;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;

@Component
@Primary
public class OpenJsonSerializer extends JsonSerializer {

	private ObjectMapper objectMapper = new ObjectMapper();

	public OpenJsonSerializer(List<JacksonModuleRegistrar> modules) {
		super(modules);
		for (JacksonModuleRegistrar each : modules) {
			each.maybeRegisterModule(objectMapper);
		}
	}

	public Json toJson(Object toSerialize) {
		try {
			String apidocs = objectMapper.writeValueAsString(toSerialize);
			return new Json(apidocs.replaceAll("\\{\\?", "?{"));
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Could not write JSON", e);
		}
	}
}
