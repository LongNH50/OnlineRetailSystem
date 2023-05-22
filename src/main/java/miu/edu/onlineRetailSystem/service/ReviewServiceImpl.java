package miu.edu.onlineRetailSystem.service;

import jakarta.persistence.EntityNotFoundException;
import miu.edu.onlineRetailSystem.contract.ReviewResponse;
import miu.edu.onlineRetailSystem.domain.Review;
import miu.edu.onlineRetailSystem.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ReviewResponse save(ReviewResponse reviewResponse) {
        Review review = mapper.map(reviewResponse, Review.class);
        reviewRepository.save(review);
        return reviewResponse;
    }

    @Override
    public ReviewResponse update(int reviewId, ReviewResponse reviewResponse) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->new EntityNotFoundException("review not found"));
//        Review review = mapper.map(reviewResponse, Review.class);
        review.setTitle(reviewResponse.getTitle());
        review.setDescription(reviewResponse.getDescription());
        review.setDate(reviewResponse.getDate());
        review.setStars(reviewResponse.getStars());
        reviewRepository.save(review);
        return reviewResponse;
    }

    @Override
    public ReviewResponse view(int reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->new EntityNotFoundException("credit card not found"));
        ReviewResponse reviewResponse = mapper.map(review, ReviewResponse.class);
        return reviewResponse;
    }



    @Override
    public ReviewResponse remove(int id) {

        Review review = reviewRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("credit card not found"));
        reviewRepository.delete(review);
        ReviewResponse reviewResponse = mapper.map(review, ReviewResponse.class);
        return reviewResponse;
    }

    @Override
    public ReviewResponse findById(int id) {
        Review review = reviewRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("credit card not found"));
        ReviewResponse reviewResponse = mapper.map(review, ReviewResponse.class);
        return reviewResponse;
    }
}
