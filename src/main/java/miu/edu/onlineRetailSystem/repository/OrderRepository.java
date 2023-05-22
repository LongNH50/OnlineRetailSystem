package miu.edu.onlineRetailSystem.repository;

import miu.edu.onlineRetailSystem.domain.Order;
import miu.edu.onlineRetailSystem.domain.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByIdAndStatus(int id, OrderStatus orderStatus);

    @Query("select o from Order o where o.customer.id = :customerId")
    Page<Order> findAllByCustomer(@Param(value = "customerId") int customerId, Pageable pageable);
}
