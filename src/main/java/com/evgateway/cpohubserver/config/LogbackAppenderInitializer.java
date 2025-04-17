//package com.evgateway.cpohubserver.config;
//
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import ch.qos.logback.classic.Logger;
//
//@Component
//public class LogbackAppenderInitializer implements ApplicationListener<ContextRefreshedEvent> {
//
//    private final CustomHttpAppender customHttpAppender;
//
//    public LogbackAppenderInitializer(CustomHttpAppender customHttpAppender) {
//        this.customHttpAppender = customHttpAppender;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
//
//        customHttpAppender.setContext(rootLogger.getLoggerContext());
//        customHttpAppender.start();
//        rootLogger.addAppender(customHttpAppender);
//
//        System.out.println("CustomHttpAppender has been started and added to Logback.");
//    }
//}
