package com.iqmsoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.iqmsoft.models.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
}
