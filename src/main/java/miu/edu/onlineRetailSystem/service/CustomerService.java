package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.CustomerResponse;

public interface CustomerService {
    CustomerResponse save(CustomerResponse customerResponse);
    CustomerResponse update(int customerId, CustomerResponse customerResponse);
}
