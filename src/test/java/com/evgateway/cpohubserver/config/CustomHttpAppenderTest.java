//package com.evgateway.cpohubserver.config;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.lang.reflect.Field;
//import java.time.Instant;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//
//import org.jboss.logging.MDC;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.spi.ILoggingEvent;
//
//class CustomHttpAppenderTest {
//
//	private CustomHttpAppender customHttpAppender;
//	private RedisTemplate<String, String> redisTemplateMock;
//	private ILoggingEvent loggingEventMock;
//
//	@BeforeEach
//	void setUp() throws Exception {
//		// Mock RedisTemplate and ILoggingEvent
//		redisTemplateMock = mock(RedisTemplate.class);
//		loggingEventMock = mock(ILoggingEvent.class);
//
//		// Initialize CustomHttpAppender
//		customHttpAppender = new CustomHttpAppender();
//
//		// Use reflection to inject the mock RedisTemplate
//		Field redisTemplateField = CustomHttpAppender.class.getDeclaredField("redisTemplate");
//		redisTemplateField.setAccessible(true);
//		redisTemplateField.set(customHttpAppender, redisTemplateMock);
//
//		// Set required fields in loggingEventMock
//		when(loggingEventMock.getFormattedMessage()).thenReturn("Test log message");
//		when(loggingEventMock.getLevel()).thenReturn(Level.INFO);
//		when(loggingEventMock.getCallerData()).thenReturn(new StackTraceElement[] {
//				new StackTraceElement("com.example.MyClass", "myMethod", "MyClass.java", 123) });
//
//		// Set MDC for request ID
//		MDC.put("X-Request-ID", "test-request-id");
//	}
//
//	@Test
//	void testAppendPublishesLogToRedis() throws Exception {
//		// Capture the message published to Redis
//		ArgumentCaptor<String> channelCaptor = ArgumentCaptor.forClass(String.class);
//		ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
//
//		// Call the append method
//		customHttpAppender.append(loggingEventMock);
//
//		// Verify that the convertAndSend method was called
//		verify(redisTemplateMock, times(1)).convertAndSend(channelCaptor.capture(), messageCaptor.capture());
//
//		// Check the channel name
//		assertEquals("test.ocpi-cpohub-backend-server", channelCaptor.getValue());
//
//		// Check the published message
//		String jsonMessage = messageCaptor.getValue();
//		assertNotNull(jsonMessage);
//
//		// Validate JSON structure
//		assertTrue(jsonMessage.contains("\"serviceName\":\"OCPI-CPOHUB-Backend-Server\""));
//		assertTrue(jsonMessage.contains("\"logLevel\":\"INFO\""));
//		assertTrue(jsonMessage.contains("\"message\":\"Test log message\""));
//		assertTrue(jsonMessage.contains("\"timestamp\":\""));
//		assertTrue(jsonMessage.contains("\"className\":\"com.example.MyClass.myMethod()\""));
//		assertTrue(jsonMessage.contains("\"requestId\":\"test-request-id\""));
//	}
//
//	@Test
//	void testAppendHandlesExceptionGracefully() {
//		// Simulate an exception in RedisTemplate
//		doThrow(new RuntimeException("Redis error")).when(redisTemplateMock).convertAndSend(anyString(), anyString());
//
//		// Call the append method
//		assertDoesNotThrow(() -> customHttpAppender.append(loggingEventMock));
//
//		// Verify that the error was handled and no further interactions occurred
//		verify(redisTemplateMock, times(1)).convertAndSend(anyString(), anyString());
//	}
//}
