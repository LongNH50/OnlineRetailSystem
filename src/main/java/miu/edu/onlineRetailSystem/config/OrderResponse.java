package miu.edu.onlineRetailSystem.config;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.onlineRetailSystem.domain.Address;
import miu.edu.onlineRetailSystem.domain.Customer;
import miu.edu.onlineRetailSystem.domain.OrderLine;
import miu.edu.onlineRetailSystem.domain.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private int id;

    private CustomerResponse customer;

    private AddressResponse shippingAddress;

    private OrderStatus status;

    private List<OrderLineResponse> lineItems = new ArrayList<>();


}
