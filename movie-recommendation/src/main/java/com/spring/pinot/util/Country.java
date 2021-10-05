package com.spring.pinot.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 01/October/2021 By Author Eresh, Gorantla
 **/
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Country {
	INDIA("India"),
	USA("United States"),
	RUSSIA("Russia"),
	GERMANY("Germany"),
	FRANCE("France"),
	SOUTH_KOREA("South Korea"),
	CHINA("China"),
	CANADA("Canada"),
	AUSTRALIA("Australia"),
	THAILAND("Thailand"),
	ITALY("Italy"),
	JAPAN("Japan"),
	SRI_LANKA("Sri Lanka"),
	ENGLAND("England"),
	NETHERLANDS("NetherLands");

	private String name;

	public static Country getRandomCountry() {
		Country[] countries = Country.values();
		return countries[ThreadLocalRandom.current()
		                                  .nextInt(0, countries.length - 1)];
	}
}