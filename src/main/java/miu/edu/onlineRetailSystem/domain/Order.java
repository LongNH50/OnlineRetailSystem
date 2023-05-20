package miu.edu.onlineRetailSystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "orderID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "shippingAddressID")
    private Address shippingAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn(name = "orderID")
    private List<OrderLine> lineItems = new ArrayList<>();


}
