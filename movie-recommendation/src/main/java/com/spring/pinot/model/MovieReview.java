package com.spring.pinot.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created on 01/October/2021 By Author Eresh, Gorantla
 **/
@Data
@ToString
public class MovieReview {
	private String id;
	private String userId;
	private String movie;
	private List<String> categories;
	private Boolean registeredUser;
	private String country;
	private String city;
	private Double rating;
	private String ratedOn;
}