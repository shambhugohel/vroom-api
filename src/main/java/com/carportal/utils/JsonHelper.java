package com.carportal.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UtilityClass
public class JsonHelper {

  private static final ObjectMapper mapper;

  private final Logger LOGGER = LoggerFactory.getLogger(JsonHelper.class.getName());

  static {
    mapper = new ObjectMapper();
  }

  public static String convertToJson(Object object) {
    String jsonResult = "";
    try {
      jsonResult = mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      LOGGER.debug("Exception Occured while Converting Java Object into Json" + e.getMessage());
    }
    return jsonResult;
  }

  public static <T> T convertToObject(String jsonString, Class<T> classs) {
    T result = null;
    try {
      result = mapper.readValue(jsonString, classs);
    } catch (JsonProcessingException e) {
      LOGGER.debug("Exception Occured while Converting Json into Java Object" + e.getMessage());
    }

    return result;
  }

}
