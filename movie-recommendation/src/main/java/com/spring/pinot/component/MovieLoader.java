package com.spring.pinot.component;

import com.spring.pinot.model.Movie;
import lombok.SneakyThrows;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created on 01/October/2021 By Author Eresh, Gorantla
 **/
@Component
public class MovieLoader {

	private List<Movie> movies;

	@SneakyThrows
	@PostConstruct
	public void init() {
		movies = Collections.emptyList();
		File file = ResourceUtils.getFile("classpath:movies.txt");
		List<String> lines = Files.readAllLines(file.toPath());
		movies = lines.stream().map(data -> extractMovieDetails(data)).collect(Collectors.toList());
	}

	public Movie extractRandomMovie() {
		return this.movies.get(ThreadLocalRandom.current()
		                                 .nextInt(0, this.movies.size() - 1));
	}

	private Movie extractMovieDetails(String data) {
		String[] movieInfo = data.split("::");
		return new Movie(Integer.parseInt(movieInfo[0]), movieInfo[1], Arrays.asList(movieInfo[2].split("\\|")));
	}
}