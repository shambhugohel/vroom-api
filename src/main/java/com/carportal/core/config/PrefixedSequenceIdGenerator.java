package com.carportal.core.config;

import java.io.Serializable;
import java.util.Properties;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;

@SuppressWarnings("deprecation")
public class PrefixedSequenceIdGenerator extends SequenceStyleGenerator {

  public static final String PREFIX_PARAM = "prefix";
  public static final String NUMBER_FORMAT_PARAM = "numberFormat";

  private String prefix;
  private String numberFormat;

  @Override
  public void configure(org.hibernate.type.Type type, Properties params,
      org.hibernate.service.ServiceRegistry serviceRegistry) {
    super.configure(type, params, serviceRegistry);
    prefix = ConfigurationHelper.getString(PREFIX_PARAM, params, "");
    numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAM, params, "%06d");
  }

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object) {
    return prefix + "-" + String.format(numberFormat, super.generate(session, object));
  }

}
