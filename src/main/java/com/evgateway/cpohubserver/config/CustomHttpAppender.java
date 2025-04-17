//package com.evgateway.cpohubserver.config;
//
//import java.time.Instant;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//
//import org.jboss.logging.MDC;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
//import ch.qos.logback.classic.spi.ILoggingEvent;
//import ch.qos.logback.core.AppenderBase;
//
//@Component
//public class CustomHttpAppender extends AppenderBase<ILoggingEvent> {
//
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
//
//	private String channelName = "test.ocpi-cpohub-backend-server";
//
//	@Override
//	protected void append(ILoggingEvent eventObject) {
//		try {
//
//			// Prepare the log message
//			String logMessage = eventObject.getFormattedMessage();
//			String logLevel = eventObject.getLevel().toString();
//
//			String serviceName = "OCPI-CPOHUB-Backend-Server"; // Replace this with your actual service name, if dynamic
//
//			// Get the current timestamp in ISO 8601 format
//			String timestamp = DateTimeFormatter.ISO_INSTANT.format(Instant.now().atZone(ZoneOffset.UTC));
//
//			// Get class name and method name from stack trace (caller data)
//			StackTraceElement[] callerData = eventObject.getCallerData();
//			String className = callerData.length > 0 ? callerData[0].getClassName() : "UnknownClass";
//			String methodName = callerData.length > 0 ? callerData[0].getMethodName() : "UnknownMethod";
//			String requestId = (String) MDC.get("X-Request-ID");
//
//			ObjectMapper objectMapper = new ObjectMapper();
//			ObjectNode jsonNode = objectMapper.createObjectNode();
//			jsonNode.put("serviceName", serviceName);
//			jsonNode.put("logLevel", logLevel);
//			jsonNode.put("message", logMessage);
//			jsonNode.put("timestamp", timestamp);
//			jsonNode.put("className", className + "." + methodName + "()");
//			jsonNode.put("requestId", requestId);
//
//			// Convert the ObjectNode to a JSON string
//			String jsonMessage = objectMapper.writeValueAsString(jsonNode);
//			redisTemplate.convertAndSend(channelName, jsonMessage);
//			//System.out.println("Published message: '" + jsonMessage + "' to channel: '" + channelName + "'");
//		} catch (Exception e) {
//			System.err.println("Error publishing log to Redis: " + e.getMessage());
//		}
//	}
//
//}