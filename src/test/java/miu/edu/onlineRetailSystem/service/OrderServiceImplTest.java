package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.OrderResponse;
import miu.edu.onlineRetailSystem.domain.Order;
import miu.edu.onlineRetailSystem.domain.OrderStatus;
import miu.edu.onlineRetailSystem.repository.OrderRepository;
import miu.edu.onlineRetailSystem.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

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
    



}
