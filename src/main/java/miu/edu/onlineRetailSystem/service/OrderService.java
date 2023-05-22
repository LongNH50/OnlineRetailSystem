package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.OrderResponse;
import miu.edu.onlineRetailSystem.contract.OrderStatusResponse;
import miu.edu.onlineRetailSystem.domain.Order;
import miu.edu.onlineRetailSystem.domain.OrderStatus;
import miu.edu.onlineRetailSystem.exceptionHandlers.CustomerException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface OrderService {

    OrderResponse save(OrderResponse orderResponse);

    OrderResponse update(int orderId, OrderResponse orderResponse);

    /**
     * return null when the order does not exist
     * throw an exception when a quantity of an orderLine is greater than the quantity in stock
     *  otherwise return an OrderResponse object
     * @param orderId
     * @return
     * @throws CustomerException
     */
    OrderResponse placeOrder(int orderId) throws CustomerException;

    OrderResponse updateStatus(int orderId, OrderStatusResponse orderStatusResponse);

    Page<OrderResponse> findAllByCustomer(int customerId, Pageable pageable);

    OrderResponse getOrder(int orderId);
}
