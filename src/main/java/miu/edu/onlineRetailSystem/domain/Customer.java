package miu.edu.onlineRetailSystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "billingAddressID")
    private Address billingAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Address> shippingAddresses = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "defaultShippingAddressID")
    private Address defaultShippingAddress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private List<CreditCard> creditCards = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customerID")
    List<Review> reviews = new ArrayList<>();

    public void setDefaultShippingAddress(Address address) {
        if (shippingAddresses.contains(address)) {
            defaultShippingAddress = address;
        } else {
            throw new IllegalArgumentException("Address is not associated with the customer");
        }
    }

    public void addCreditCart(CreditCard creditCard) {
        this.creditCards.add(creditCard);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
}
