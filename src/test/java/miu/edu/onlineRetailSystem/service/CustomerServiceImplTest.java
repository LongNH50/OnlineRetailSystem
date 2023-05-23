package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.CustomerResponse;
import miu.edu.onlineRetailSystem.domain.Customer;
import miu.edu.onlineRetailSystem.repository.CustomerRepository;
import miu.edu.onlineRetailSystem.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {

        CustomerResponse customerResponse = new CustomerResponse();
        Customer customer = new Customer();


        when(modelMapper.map(customerResponse, Customer.class)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer, CustomerResponse.class)).thenReturn(customerResponse);


        CustomerResponse result = customerService.save(customerResponse);


        assertEquals(customerResponse, result);


        verify(modelMapper, times(1)).map(customerResponse, Customer.class);
        verify(customerRepository, times(1)).save(customer);
        verify(modelMapper, times(1)).map(customer, CustomerResponse.class);
    }

    @Test
    public void testUpdate() {
        int customerId = 1;
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customerId);
        Customer customer = new Customer();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(modelMapper.map(customerResponse, Customer.class)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer, CustomerResponse.class)).thenReturn(customerResponse);

        CustomerResponse result = customerService.update(customerId, customerResponse);

        assertEquals(customerResponse, result);

        verify(customerRepository, times(1)).findById(customerId);
        verify(modelMapper, times(1)).map(customerResponse, Customer.class);
        verify(customerRepository, times(1)).save(customer);
        verify(modelMapper, times(1)).map(customer, CustomerResponse.class);
    }
    @Test
    public void testGetCustomer() {
        int customerId = 1;
        Customer customer = new Customer();
        CustomerResponse customerResponse = new CustomerResponse();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(modelMapper.map(customer, CustomerResponse.class)).thenReturn(customerResponse);

        CustomerResponse result = customerService.getCustomer(customerId);

        assertEquals(customerResponse, result);

        verify(customerRepository, times(1)).findById(customerId);
        verify(modelMapper, times(1)).map(customer, CustomerResponse.class);
    }

}
