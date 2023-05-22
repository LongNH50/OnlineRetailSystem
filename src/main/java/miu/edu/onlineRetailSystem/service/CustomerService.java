package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerResponse save(CustomerResponse customerResponse);
    CustomerResponse update(int customerId, CustomerResponse customerResponse);

    CustomerResponse getCustomer(int customerId);

    Page<CustomerResponse> findAll(Pageable pageable);
}
