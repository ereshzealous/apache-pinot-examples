package com.spring.pinot.kafka;

import com.spring.pinot.component.MovieLoader;
import com.spring.pinot.component.UsersLoader;
import com.spring.pinot.model.Movie;
import com.spring.pinot.model.MovieReview;
import com.spring.pinot.util.City;
import com.spring.pinot.util.Country;
import com.spring.pinot.util.DataUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.MimeType;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 01/October/2021 By Author Eresh, Gorantla
 **/
@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class KafkaConfiguration {

	private final StreamBridge streamBridge;
	private final MovieLoader movieLoader;
	private final UsersLoader usersLoader;

	@Value("${spring.cloud.stream.bindings.eventProducer-out-0.content-type}")
	private String streamOutMimeType;

	@Scheduled(fixedDelay = 1000, initialDelay = 5000)
	@SneakyThrows
	public void publishCovidTrackerData() {
		List<MovieReview> covidCasesList = generateMovieReviews();
		covidCasesList.stream().forEach(data -> {
			streamBridge.send("eventProducer-out-0", data, MimeType.valueOf(streamOutMimeType));
			//System.out.println(data.toString());
		});
	}

	private List<MovieReview> generateMovieReviews() {
		Integer limit = ThreadLocalRandom.current()
		                                 .nextInt(3000, 5000);
		List<MovieReview> movieReviews = IntStream.range(0, limit).mapToObj(v -> createMovieReview()).collect(Collectors.toList());
		return movieReviews;
	}

	private MovieReview createMovieReview() {
		Movie movie = movieLoader.extractRandomMovie();
		Integer randomInteger = ThreadLocalRandom.current()
		                                         .nextInt(0, 10000);
		Boolean isAnonymous = randomInteger % 3 == 0;
		MovieReview movieReview = new MovieReview();
		movieReview.setId(UUID.randomUUID().toString());
		if (isAnonymous) {
			movieReview.setUserId(UUID.randomUUID().toString());
		} else {
			movieReview.setUserId(usersLoader.getRandomUser());
		}
		movieReview.setMovie(movie.getName());
		movieReview.setCategories(movie.getCategories());
		String country = Country.getRandomCountry().getName();
		movieReview.setCountry(country);
		movieReview.setCity(City.generateRandomCity(country));
		movieReview.setRegisteredUser(!isAnonymous);
		movieReview.setRatedOn(DataUtils.generateCreatedOn());
		movieReview.setRating(ThreadLocalRandom.current()
		                                       .nextDouble(1, 10));
		return movieReview;
	}
}