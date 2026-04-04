package com.carzhub.shared.exceptions.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@org.springframework.stereotype.Component
@ConfigurationProperties(prefix = "carportal.api.error")
public class ApiErrorProperties {

  String format; // apierror | problemjson

}
