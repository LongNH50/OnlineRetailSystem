package miu.edu.onlineRetailSystem.repository;

import miu.edu.onlineRetailSystem.domain.CreditCard;
import miu.edu.onlineRetailSystem.domain.Customer;
import miu.edu.onlineRetailSystem.domain.Review;
import miu.edu.onlineRetailSystem.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findCreditCardByCustomer_WhenValidCustomerId_ShouldReturnCreditCards() {
        int customerId = 1;

        Collection<CreditCard> creditCards = customerRepository.findCreditCardByCustomer(customerId);

        assertNotNull(creditCards);
        // Add assertions for the expected credit card data
    }

//    @Test
//    void findCreditCardByCreditCardIdAndCustomer_WhenValidIds_ShouldReturnCreditCard() {
//        int customerId = 1;
//        int creditCardId = 1;
//
//        CreditCard creditCard = customerRepository.findCreditCardByCreditCardIdAndCustomer(customerId, creditCardId);
//
//        assertNotNull(creditCard, "Credit Card should not be null");
//        assertEquals(creditCardId, creditCard.getId(), "Credit Card ID should match");
//
//        System.out.println("Actual Credit Card: " + creditCard);
//        System.out.println("Credit Card ID: " + creditCard.getId());
//    }



    @Test
    void findReviewsByCustomer_WhenValidCustomerId_ShouldReturnReviewsPage() {
        int customerId = 1;
        Pageable pageable = PageRequest.of(0, 10);

        Page<Review> reviewsPage = customerRepository.findReviewsByCustomer(customerId, pageable);

        assertNotNull(reviewsPage);
    }
}
