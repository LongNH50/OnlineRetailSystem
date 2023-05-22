package miu.edu.onlineRetailSystem.controller;

import miu.edu.onlineRetailSystem.contract.CustomerResponse;
import miu.edu.onlineRetailSystem.exceptionHandlers.CustomerException;
import miu.edu.onlineRetailSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> findAll(Pageable pageable) {
        return new ResponseEntity<>(customerService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable int customerId) {
        CustomerResponse customerResponse = customerService.getCustomer(customerId);
        if (customerResponse == null)
            return new ResponseEntity<>(new CustomerException("Customer does not exist!"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerResponse customerResponse) {
        CustomerResponse savedCustomerResponse = customerService.save(customerResponse);

        return new ResponseEntity<>(savedCustomerResponse, HttpStatus.CREATED);
    }

    @PutMapping("/customerId")
    public ResponseEntity<?> update(@PathVariable int customerId, @RequestBody CustomerResponse customerResponse) {
        CustomerResponse modifiedCustomerResponse = customerService.update(customerId, customerResponse);
        if (modifiedCustomerResponse == null)
            return new ResponseEntity<>(new CustomerException("Customer does not exist!"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(modifiedCustomerResponse, HttpStatus.OK);
    }
}
