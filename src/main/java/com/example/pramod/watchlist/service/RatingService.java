package com.example.pramod.watchlist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {

	//String apiUrl="https://www.omdbapi.com/?apikey=a8431cee&t=";
	String apiUrl="https://www.omdbapi.com/?apikey=336b2f16&t=";
	
	public String getMovieRating(String title) {
		try {
			// try to fetch the rating by calling omdb api
			RestTemplate template=new RestTemplate();
			// apiUrl + title
			
			ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl + title, ObjectNode.class);
			
			ObjectNode jsonObject=response.getBody();
			
			System.out.println(jsonObject.path("imdbRating").asText());
			return jsonObject.path("imdbRating").asText();
			
			}catch(Exception e) {
			// to user entered rating will be taken
				System.out.println("Either movie name not available or api is down"+e.getMessage());
				return null;
		}
	}
}
