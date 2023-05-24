package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.CreditCardResponse;
import miu.edu.onlineRetailSystem.contract.OrderResponse;
import miu.edu.onlineRetailSystem.domain.CreditCard;
import miu.edu.onlineRetailSystem.domain.Order;
import miu.edu.onlineRetailSystem.domain.OrderStatus;
import miu.edu.onlineRetailSystem.repository.CreditCardRepository;
import miu.edu.onlineRetailSystem.repository.OrderRepository;
import miu.edu.onlineRetailSystem.service.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CreditCardRepository creditCardRepository;
    @Mock
    private  CreditCardService creditCardService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        OrderResponse orderResponse = new OrderResponse();
        Order order = new Order();

        when(modelMapper.map(orderResponse, Order.class)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(modelMapper.map(order, OrderResponse.class)).thenReturn(orderResponse);

        OrderResponse result = orderService.save(orderResponse);

        assertEquals(orderResponse, result);

        verify(modelMapper, times(1)).map(orderResponse, Order.class);
        verify(orderRepository, times(1)).save(order);
        verify(modelMapper, times(1)).map(order, OrderResponse.class);
    }


    @Test
    public void testUpdate_InvalidOrderIdOrOrderResponse_ExceptionThrown() {
        // Mock data
        int orderId = 1;
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(orderId);

        // Mock repository to return null (order not found)
        Mockito.when(orderRepository.findByIdAndStatus(orderId, OrderStatus.NEW)).thenReturn(null);

        // Call the service method and assert that it throws an exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.update(orderId, orderResponse);
        });
    }


}
