package com.spring.pinot.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 01/October/2021 By Author Eresh, Gorantla
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataUtils {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static String generateCreatedOn() {
		LocalDateTime localDate = LocalDateTime.now();
		int day = ThreadLocalRandom.current().nextInt(1, 28);
		int hour =  ThreadLocalRandom.current().nextInt(0, 23);
		int minutes =  ThreadLocalRandom.current().nextInt(1, 59);
		int seconds =  ThreadLocalRandom.current().nextInt(1, 59);
		int nanoSeconds =  ThreadLocalRandom.current().nextInt(100, 900);
		LocalDateTime localDateTime = LocalDateTime.of(2021, 9, day, hour, minutes, seconds, nanoSeconds);
		return DATE_FORMATTER.format(localDateTime);
	}
}