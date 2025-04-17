package com.evgateway.cpohubserver.Utils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Utils {

	public static String getCurrentUtcTime(long days) throws ParseException {

		Instant instant = Instant.now();

		ZoneId zoneId = ZoneId.of("UTC");

		LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime().minusDays(days).minusMinutes(5);

		LocalDateTime withZeroSeconds = localDateTime.withSecond(0);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		String formattedDateTime = withZeroSeconds.format(formatter);

		return formattedDateTime;
	}

	public static String getTimeFormate(Double timecon) {

		long totalSeconds = (long) (timecon * 3600);

		// Extract hours, minutes, and seconds
		long hh = totalSeconds / 3600;
		long mm = (totalSeconds % 3600) / 60;
		long ss = totalSeconds % 60;
		return String.format("%02d:%02d:%02d", hh, mm, ss);
	}

}
