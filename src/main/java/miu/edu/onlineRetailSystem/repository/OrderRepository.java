package miu.edu.onlineRetailSystem.repository;

import miu.edu.onlineRetailSystem.domain.Order;
import miu.edu.onlineRetailSystem.domain.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByIdAndStatus(int id, OrderStatus orderStatus);
    Page<Order> findAllByCustomer(int customer, Pageable pageable);
}
