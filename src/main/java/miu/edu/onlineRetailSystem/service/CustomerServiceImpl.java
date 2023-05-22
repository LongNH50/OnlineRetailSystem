package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.CustomerResponse;
import miu.edu.onlineRetailSystem.domain.Customer;
import miu.edu.onlineRetailSystem.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public CustomerResponse save(CustomerResponse customerResponse) {
        Customer customer = modelMapper.map(customerResponse, Customer.class);
        customer = customerRepository.save(customer);

        return modelMapper.map(customer, CustomerResponse.class);
    }

    @Override
    public CustomerResponse update(int customerId, CustomerResponse customerResponse) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent() && customerResponse.getId() == customerId) {
            Customer customer = modelMapper.map(customerResponse, Customer.class);
            customer = customerRepository.save(customer);

            return modelMapper.map(customer, CustomerResponse.class);
        }

        return null;
    }

    @Override
    public CustomerResponse getCustomer(int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        return customerOptional.map(customer -> modelMapper.map(customer, CustomerResponse.class)).orElse(null);
    }

    @Override
    public Page<CustomerResponse> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable).map(customer -> modelMapper.map(customer, CustomerResponse.class));
    }
}
