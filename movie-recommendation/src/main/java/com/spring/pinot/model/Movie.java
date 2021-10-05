package com.spring.pinot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * Created on 01/October/2021 By Author Eresh, Gorantla
 **/
@Data
@AllArgsConstructor
@ToString
public class Movie {
	private Integer id;
	private String name;
	private List<String> categories = Collections.emptyList();
}