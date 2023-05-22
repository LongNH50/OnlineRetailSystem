package miu.edu.onlineRetailSystem.service;

import jakarta.transaction.Transactional;
import miu.edu.onlineRetailSystem.contract.OrderResponse;
import miu.edu.onlineRetailSystem.contract.OrderStatusResponse;
import miu.edu.onlineRetailSystem.domain.Order;
import miu.edu.onlineRetailSystem.domain.OrderLine;
import miu.edu.onlineRetailSystem.domain.OrderStatus;
import miu.edu.onlineRetailSystem.exceptionHandlers.CustomerException;
import miu.edu.onlineRetailSystem.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderResponse save(OrderResponse orderResponse) {
        Order order = modelMapper.map(orderResponse, Order.class);
        order.setStatus(OrderStatus.NEW);
        order = orderRepository.save(order);

        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse update(int orderId, OrderResponse orderResponse) {
        Order order = orderRepository.findByIdAndStatus(orderId, OrderStatus.NEW);
        if (order != null && orderResponse.getId() == orderId) {
            order = modelMapper.map(orderResponse, Order.class);
            order = orderRepository.save(order);

            return modelMapper.map(order, OrderResponse.class);
        }
        return null;
    }

    @Override
    public OrderResponse placeOrder(int orderId) throws CustomerException {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            updateStock(order);
            order.setStatus(OrderStatus.PLACED);
            order = orderRepository.save(order);

            return modelMapper.map(order, OrderResponse.class);
        }
        return null;
    }

    @Override
    public OrderResponse updateStatus(int orderId, OrderStatusResponse orderStatusResponse) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            OrderStatus orderStatus = modelMapper.map(orderStatusResponse, OrderStatus.class);
            Order order = orderOptional.get();
            order.setStatus(orderStatus);
            order = orderRepository.save(order);

            return modelMapper.map(order, OrderResponse.class);
        }
        return null;
    }

    @Override
    public Page<OrderResponse> findAllByCustomer(int customerId, Pageable pageable) {
        return orderRepository.findAllByCustomer(customerId, pageable).map(order -> modelMapper.map(order, OrderResponse.class));
    }

    @Override
    public OrderResponse getOrder(int orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        return orderOptional.map(order -> modelMapper.map(order, OrderResponse.class)).orElse(null);

    }

    private void updateStock(Order order) throws CustomerException {

        for (OrderLine orderLine : order.getLineItems()) {
            int inStock = orderLine.getItem().getQuantityInStock();
            int quantity = orderLine.getQuantity();
            System.out.println("quantity: " + quantity + " in stock: " + inStock);
            if (quantity > inStock) throw new CustomerException("Quantity of " + orderLine.getItem().getName() +
                    " is greater than the quantity in stock");
            orderLine.getItem().setQuantityInStock(inStock - quantity);
        }
    }
}
