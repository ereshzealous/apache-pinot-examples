package com.spring.pinot.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on 01/October/2021 By Author Eresh, Gorantla
 **/
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum City {

	INDIA_CITIES("India", Stream.of("Hyderabad", "Bangalore", "Mumbai", "New Delhi", "Pune").collect(Collectors.toList())),
	USA_CITIES("United States", Stream.of("San Jose", "New York", "California", "Texas", "Arizona").collect(Collectors.toList())),
	RUSSIA_CITIES("Russia", Stream.of("Moscow", "St Petersburg", "Novosibirsk", "Kazan", "Samara").collect(Collectors.toList())),
	GERMANY_CITIES("Germany", Stream.of("Berlin", "Hamburg", "Munich", "Essen", "Stuttgart").collect(Collectors.toList())),
	FRANCE_CITIES("France", Stream.of("Marseille", "Paris", "Lyon", "Bordeaux", "Rennes").collect(Collectors.toList())),
	SOUTH_KOREA_CITIES("South Korea", Stream.of("Seoul", "Pusan", "Inchon", "Taegu", "Taejon").collect(Collectors.toList())),
	CHINA_CITIES("China", Stream.of("Shanghai", "Tianjin", "Wuhan", "Harbin", "Qingdao").collect(Collectors.toList())),
	CANADA_CITIES("Canada", Stream.of("Toronto", "Vancouver", "Scarborough", "Mississauga", "Winnipeg").collect(Collectors.toList())),
	AUSTRALIA_CITIES("Australia", Stream.of("Melbourne", "Sydney", "Perth", "Brisbane", "Adelaide").collect(Collectors.toList())),
	THAILAND_CITIES("Thailand", Stream.of("Bangkok", "Songkhla", "Nakhon Sawan", "Nonthaburi", "Chiang Mai").collect(Collectors.toList())),
	ITALY_CITIES("Italy", Stream.of("Roma", "Milano", "Genova", "Bologna", "Venezia").collect(Collectors.toList())),
	JAPAN_CITIES("Japan", Stream.of("Tokyo", "Osaka", "Nagoya", "Sapporo", "Kawasaki").collect(Collectors.toList())),
	SRI_LANKA_CITIES("Sri Lanka", Stream.of("Dehiwala", "Colombo", "Kandy", "Jaffna", "Negombo").collect(Collectors.toList())),
	ENGLAND_CITIES("England", Stream.of("Birmingham", "London", "Liverpool", "Manchester", "Leeds").collect(Collectors.toList())),
	NETHERLANDS_CITIES("NetherLands", Stream.of("Amsterdam", "Rotterdam", "Haag", "Utrecht", "Eindhoven").collect(Collectors.toList()));

	private String country;
	private List<String> cities;

	public static String generateRandomCity(String country) {
		City[] cities = City.values();
		City city = Arrays.stream(cities).filter(data -> StringUtils.equalsIgnoreCase(data.getCountry(), country)).findFirst().orElse(null);
		if (city != null) {
			final Integer index = ThreadLocalRandom.current().nextInt(0, city.cities.size() - 1);
			return city.getCities().get(index);
		}
		return "UNKNOWN";
	}

}