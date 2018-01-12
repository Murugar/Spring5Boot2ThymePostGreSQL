package com.iqmsoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iqmsoft.models.Review;


public interface ReviewRepo extends JpaRepository<Review, Long> {
}
