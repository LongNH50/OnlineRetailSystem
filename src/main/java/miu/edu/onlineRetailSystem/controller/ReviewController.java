package miu.edu.onlineRetailSystem.controller;

import miu.edu.onlineRetailSystem.contract.CreditCardResponse;
import miu.edu.onlineRetailSystem.contract.ReviewResponse;
import miu.edu.onlineRetailSystem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(@RequestBody ReviewResponse reviewResponse) {
        return ResponseEntity.ok(reviewService.save(reviewResponse));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ReviewResponse> removeReview(@PathVariable int id) {
        ReviewResponse reviewResponse = reviewService.findById(id);
        reviewService.remove(id);
        return ResponseEntity.ok(reviewResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> editCreditCard(@PathVariable int id,
                                                             @RequestBody ReviewResponse reviewResponse) {
        reviewService.update(id,reviewResponse);
        return ResponseEntity.ok(reviewResponse);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> view(@PathVariable int id) {
        ReviewResponse reviewResponse = reviewService.findById(id);
        return ResponseEntity.ok(reviewResponse);
    }
}
