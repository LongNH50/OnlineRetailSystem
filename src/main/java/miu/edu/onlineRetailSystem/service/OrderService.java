package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.OrderResponse;
import miu.edu.onlineRetailSystem.domain.OrderStatus;

public interface OrderService {
    OrderResponse placeOrder(OrderResponse orderResponse);

    OrderResponse updateStatus(int orderId, OrderStatus orderStatus);
}
