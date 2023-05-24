package miu.edu.onlineRetailSystem.repository;

import miu.edu.onlineRetailSystem.domain.Order;
import miu.edu.onlineRetailSystem.domain.OrderStatus;
import miu.edu.onlineRetailSystem.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void findByIdAndStatus_WhenValidIdAndStatus_ThenReturnOrder() {
        // Arrange
        Order order = new Order();
        order.setStatus(OrderStatus.PLACED);
        entityManager.persist(order);
        entityManager.flush();

        // Act
        Order found = orderRepository.findByIdAndStatus(order.getId(), OrderStatus.PLACED);

        // Assert
//        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(order.getId(), found.getId());
        Assertions.assertEquals(order.getStatus(), found.getStatus());
    }


}
