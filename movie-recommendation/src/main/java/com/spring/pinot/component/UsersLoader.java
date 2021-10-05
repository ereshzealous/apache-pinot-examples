package com.spring.pinot.component;

import lombok.SneakyThrows;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created on 01/October/2021 By Author Eresh, Gorantla
 **/
@Component
public class UsersLoader {

	private List<String> users = null;

	@SneakyThrows
	@PostConstruct
	public void init() {
		users = new ArrayList<>();
		File file = ResourceUtils.getFile("classpath:users.txt");
		users = Files.readAllLines(file.toPath());
	}

	public String getRandomUser() {
		return this.users.get(ThreadLocalRandom.current()
		                                       .nextInt(0, this.users.size() - 1));
	}
}