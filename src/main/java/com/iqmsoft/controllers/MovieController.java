package com.iqmsoft.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iqmsoft.models.Movie;
import com.iqmsoft.models.Review;
import com.iqmsoft.repositories.MovieRepo;
import com.iqmsoft.repositories.ReviewRepo;

@Controller
public class MovieController {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ReviewRepo reviewRepo;

    //lists all movies in table
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("myMovies", movieRepo.findAll());
        return "index";
    }

    //takes you to create movie page
    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("movie", new Movie());
        return "add";
    }

    //creates movie
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String index(@ModelAttribute Movie movie) {
        System.out.println(movie);
        movieRepo.save(movie);
        return "redirect:/";
    }

    //get to movie detail/update page
    @RequestMapping(value = "/movie/{id}")
    public String updateMovie(Model model,
                             @PathVariable("id") Long id) {
        Optional<Movie> myMovie = movieRepo.findById(id);
        model.addAttribute("movie", myMovie.get());
        System.out.println(id);
        return "update";
    }

    //updates movie
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.POST)
    public String updateMovieForm(@ModelAttribute Movie movie) {
        movieRepo.save(movie);
        return "redirect:/";
    }

    //lists all reviews
    @RequestMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("myReviews", reviewRepo.findAll());
        return "review";
    }

    //takes you to create review page
    @RequestMapping("/createReview/{movieId}")
    public String createReviewForm(Model model,
                                   @PathVariable("movieId") long movieId) {
        model.addAttribute("review", new Review());
        model.addAttribute("id", movieId);
        return "create";
    }

    //creates review
    @RequestMapping(value = "/createReview/{movieId}", method = RequestMethod.POST)
    public String createReview(@PathVariable("movieId") long movieId,
                               @ModelAttribute Review review) {

        Optional<Movie> movie = movieRepo.findById(movieId);
        review.setMovie(movie.get());
        reviewRepo.save(review);

        return "redirect:/";

    }

    //lists all reviews for a particular movie
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") long id) {
        Optional<Movie> movie = movieRepo.findById(id);
        model.addAttribute("myReviews", movie.get().getReviews());
        return "detail";
    }

}
