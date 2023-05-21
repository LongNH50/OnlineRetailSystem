package miu.edu.onlineRetailSystem.config;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.onlineRetailSystem.domain.Address;
import miu.edu.onlineRetailSystem.domain.CreditCard;
import miu.edu.onlineRetailSystem.domain.Review;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buyer")
    List<ReviewResponse> reviewResponses = new ArrayList<>();
}
