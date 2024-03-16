package com.example.pramod.watchlist.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.pramod.watchlist.entity.Movie;
import com.example.pramod.watchlist.service.DatabaseService;

import jakarta.validation.Valid;

@RestController
public class MovieController {

	@Autowired
	DatabaseService databaseService;
	
	@GetMapping("/watchlistItemForm")
	public ModelAndView showwatchlistForm(@RequestParam(required = false) Integer id) {
		
		System.out.println(id);
		String viewName="watchlistItemForm";
		
		Map<String,Object> model=new HashMap<>();
		
		if(id==null) {
			model.put("watchlistItem", new Movie());
		}else {
			model.put("watchlistItem", databaseService.getMovieById(id));
		}
//		Movie dummyMovie=new Movie();
//		dummyMovie.setTitle("Dummy");
//		dummyMovie.setRating(1);
//		dummyMovie.setPriority(0);
//		dummyMovie.setComment("NO");
//		model.put("watchlistItem", dummyMovie);
//		
		
		return new ModelAndView(viewName,model);
	}
	
	// This code word after fill data and submit. the current page["watchlistItemForm"] redirect to new page["watchlist"].
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitwatchlistForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			//if errors are there, display the form and enter again.
			return new ModelAndView("watchlistItemForm");
		}
		/*
		  if(id==null)
		  { create new movie 
		  }else{
		  update 
		  }
		*/
		
		Integer id =movie.getId();
		if(id == null) {
			databaseService.create(movie);
		}else {
			databaseService.update(movie,id);
		}
		
		RedirectView rd=new RedirectView();
		rd.setUrl("/watchlist");
		
		return new ModelAndView(rd);
	}
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchlist() {
		// TODO Auto-generated method stub
		
		String viewName="watchlist";
		Map<String,Object> model=new HashMap<>();
		List<Movie> moiveList= databaseService.getAllMovies();
		model.put("watchlistrows",moiveList);
		model.put("noofmovies", moiveList.size());
		return new ModelAndView(viewName, model);
	}
}
