package miu.edu.onlineRetailSystem.repository;

import miu.edu.onlineRetailSystem.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}