package miu.edu.onlineRetailSystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import miu.edu.onlineRetailSystem.contract.*;
import miu.edu.onlineRetailSystem.domain.*;
import miu.edu.onlineRetailSystem.exception.CustomerErrorException;
import miu.edu.onlineRetailSystem.exception.ResourceNotFoundException;
import miu.edu.onlineRetailSystem.repository.AddressRepository;
import miu.edu.onlineRetailSystem.repository.CustomerRepository;
import miu.edu.onlineRetailSystem.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderLineService orderLineService;

    @Override
    public OrderResponse save(int customerId, OrderResponse orderResponse) {
        Order order = modelMapper.map(orderResponse, Order.class);
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Id", customerId)
        );
        order.setCustomer(customer);
        order.setStatus(OrderStatus.NEW);
         if (orderResponse.getLineItems().size() == 0)
             throw new CustomerErrorException("Add at least one item!");

        order = orderRepository.save(order);

        for (OrderLineResponse orderLineResponse : orderResponse.getLineItems()) {
            orderLineService.save(order.getId(), orderLineResponse);
        }

        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse update(int customerId, int orderId, OrderResponse orderResponse) {
        Order order = orderRepository.findByIdAndStatusAndCustomer(customerId, orderId, OrderStatus.NEW);
        if (order == null || orderResponse.getId() != orderId)
            throw new ResourceNotFoundException("Order", "Id", orderId);

        order = modelMapper.map(orderResponse, Order.class);
        order = orderRepository.save(order);

        return modelMapper.map(order, OrderResponse.class);
    }
    @KafkaListener(topics = "changeOrderStatus2")
    public void updateKafka(String orderDetails) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        OrderUpdation orderUpdation = objectMapper.readValue(orderDetails, OrderUpdation.class);
        // update status set status = DELIVERED where status = NEW
        System.out.println("receiving: ....");
        orderRepository.updateOrderStatus(orderUpdation.getOrderId(), orderUpdation.getCustomerId(), orderUpdation.getOrderStatus());
    }

    @Override
    public OrderResponse placeOrder(int customerId, int orderId) {
        Order order = orderRepository.findByIdAndStatusAndCustomer(customerId, orderId, OrderStatus.NEW);
        if (order == null)
            throw new ResourceNotFoundException("Order", "Id", orderId);
        Address defaultShippingAddress = addressRepository.findDefaultAddressByCustomer(customerId);
        if (defaultShippingAddress == null)
            throw new CustomerErrorException("Please add a shipping address!");

        updateStock(order);
        order.setStatus(OrderStatus.PLACED);
        order.setShippingAddress(defaultShippingAddress);
        order = orderRepository.save(order);

        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse updateStatus(int customerId, int orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findByIdAndCustomer(customerId, orderId);
        if (order == null)
            throw new ResourceNotFoundException("Order", "Id", orderId);

        order.setStatus(orderStatus);
        order = orderRepository.save(order);

        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public Page<OrderResponse> findAllByCustomer(int customerId, Pageable pageable) {
        return orderRepository.findAllByCustomer(customerId, pageable).map(order -> modelMapper.map(order, OrderResponse.class));
    }

    @Override
    public OrderResponse getOrder(int customerId, int orderId) {
        Order order = orderRepository.findByIdAndCustomer(customerId, orderId);
        if (order == null)
            throw new ResourceNotFoundException("Order", "Id", orderId);

        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public Collection<OrderLineResponse> getCustomerOrderLines(int customerId, int orderId) {
        return orderRepository.findOrderLinesByCustomer(customerId, orderId)
                .stream()
                .map(orderLine -> modelMapper.map(orderLine, OrderLineResponse.class))
                .toList();
    }

    @Override
    public OrderLineResponse getCustomerOrderLine(int customerId, int orderId, int orderLineId) {
        OrderLine orderLine = orderRepository.findOrderLineByIdAndCustomerAndOrder(customerId, orderId, orderLineId);
        if (orderLine == null)
            throw new ResourceNotFoundException("OrderLine", "Id", orderLineId);

        return modelMapper.map(orderLine, OrderLineResponse.class);
    }

    @Override
    public ReviewResponse getReviewByCustomerAndOrder(int customerId, int orderId, int reviewId) {
        Review review = orderRepository.findReviewByCustomerAndOrder(customerId, orderId, reviewId);

        if (review == null)
            throw new ResourceNotFoundException("Review", "orderId", orderId);

        return modelMapper.map(review, ReviewResponse.class);
    }

    @Override
    public ReviewResponse getReviewByIdAndCustomerAndOrder(int customerId, int orderId, int reviewId) {
        Review review = orderRepository.findReviewByIdAndCustomerAndOrder(customerId, orderId, reviewId);
        if (review == null)
            throw new ResourceNotFoundException("Review", "Id", reviewId);

        return modelMapper.map(review, ReviewResponse.class);
    }

    private void updateStock(Order order) {

        for (OrderLine orderLine : order.getLineItems()) {
            int inStock = orderLine.getItem().getQuantityInStock();
            int quantity = orderLine.getQuantity();
            if (quantity > inStock) throw new CustomerErrorException("Quantity of " + orderLine.getItem().getName() +
                    " is greater than the quantity in stock");
            orderLine.getItem().setQuantityInStock(inStock - quantity);
        }
    }
}
