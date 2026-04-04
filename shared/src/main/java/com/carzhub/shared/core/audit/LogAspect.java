package com.carzhub.shared.core.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
@Component("LoggingAspect")
public class LogAspect {

  private final static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

  /*
   * @Pointcut(value = "com.carportal.controller.*.*.*(..))") public void
   * myPointCut() {
   *
   * }
   *
   * @Around("myPointCut()") public Object applicationLogger(ProceedingJoinPoint
   * pjp) throws Throwable { ObjectMapper mapper = new ObjectMapper(); String
   * methodName = pjp.getSignature().getName(); String className =
   * pjp.getTarget().getClass().toString(); Object[] array = pjp.getArgs();
   * LOGGER.info("Method Invoked: " + className + " : " + methodName + "()" +
   * "Arguments: " + mapper.writeValueAsString(array)); Object object =
   * pjp.proceed(); LOGGER.info(className + " : " + methodName + "()" + "Response: "
   * + mapper.writeValueAsString(array)); return object;
   *
   * }
   */

}
