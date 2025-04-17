//package com.evgateway.cpohubserver.config;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.context.event.ContextRefreshedEvent;
//
//import ch.qos.logback.classic.Logger;
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.core.Appender;
//
//import org.slf4j.LoggerFactory;
//
//class LogbackAppenderInitializerTest {
//
//    @Mock
//    private CustomHttpAppender customHttpAppender;
//
//    @Mock
//    private ContextRefreshedEvent contextRefreshedEvent;
//
//    private LogbackAppenderInitializer logbackAppenderInitializer;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        logbackAppenderInitializer = new LogbackAppenderInitializer(customHttpAppender);
//    }
//
//    @Test
//    void testOnApplicationEventAddsCustomAppenderToRootLogger() {
//        // Arrange
//        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
//        Logger rootLogger = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
//
//        // Simulate the custom appender behavior
//        when(customHttpAppender.getName()).thenReturn("CustomHttpAppender");
//        doNothing().when(customHttpAppender).setContext(loggerContext);
//        doNothing().when(customHttpAppender).start();
//
//        // Act
//        logbackAppenderInitializer.onApplicationEvent(contextRefreshedEvent);
//
//        // Assert
//        Appender<?> addedAppender = rootLogger.getAppender("CustomHttpAppender");
//        assertNotNull(addedAppender, "CustomHttpAppender should be added to the root logger");
//        assertEquals("CustomHttpAppender", addedAppender.getName(), "Appender name should match");
//    }
//}
