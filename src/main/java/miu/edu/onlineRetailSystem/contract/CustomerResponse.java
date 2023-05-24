package miu.edu.onlineRetailSystem.contract;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.onlineRetailSystem.domain.Address;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    private AddressResponse billingAddress;

    private List<AddressResponse> shippingAddresses = new ArrayList<>();

    private List<CreditCardResponse> creditCards = new ArrayList<>();

    List<ReviewResponse> reviewResponses = new ArrayList<>();

    private AddressResponse defaultShippingAddress;

    public void addCreditCartResponse(CreditCardResponse creditCardResponse) {
        this.creditCards.add(creditCardResponse);
    }

    public void addReviewResponse(ReviewResponse reviewResponse) {
        this.reviewResponses.add(reviewResponse);
    }
}
