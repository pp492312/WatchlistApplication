package com.example.pramod.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pramod.watchlist.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Integer> {

}
