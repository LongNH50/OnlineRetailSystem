package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.ReviewResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    ReviewResponse save(ReviewResponse reviewResponse);

    ReviewResponse update(int reviewId, ReviewResponse reviewResponse);

    ReviewResponse view(int reviewId);


    ReviewResponse remove(int id);

    ReviewResponse findById(int id);
}
