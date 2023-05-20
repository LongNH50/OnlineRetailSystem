package miu.edu.onlineRetailSystem.repository;

import miu.edu.onlineRetailSystem.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
