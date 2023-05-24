package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.AddressResponse;
import miu.edu.onlineRetailSystem.domain.Address;
import miu.edu.onlineRetailSystem.domain.Customer;
import miu.edu.onlineRetailSystem.repository.AddressRepository;
import miu.edu.onlineRetailSystem.repository.AddressTypeRepository;
import miu.edu.onlineRetailSystem.repository.CustomerRepository;
import miu.edu.onlineRetailSystem.service.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
public class AddressServiceImplTest {

    @Mock
    private ModelMapper mapper;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressTypeRepository addressTypeRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Customer customer;
    private Address address;
    private Address address1;
    private Address address2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer();
        address = new Address();
        address1 = new Address();
        address2 = new Address();
    }

    @Test
    public void testSave() {
        when(customerRepository.save(address.getCustomer())).thenReturn(address.getCustomer());
        when(addressTypeRepository.save(address.getAddressType())).thenReturn(address.getAddressType());
        when(addressRepository.save(address)).thenReturn(address);

        addressService.save(address);

        verify(customerRepository, times(1)).save(address.getCustomer());
        verify(addressTypeRepository, times(1)).save(address.getAddressType());
        verify(addressRepository, times(1)).save(address);
    }


    @Test
    public void testUpdate() {
        int addressId = 1;
        Address foundAddress = new Address();
        foundAddress.setId(addressId);

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(foundAddress));
        when(addressRepository.save(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Address updatedAddress = new Address();
        updatedAddress.setCity("New City");
        updatedAddress.setStreet("New Street");
        updatedAddress.setState("New State");
        updatedAddress.setZipCode("New Zip Code");

        Address result = addressService.update(addressId, updatedAddress);

        assertEquals(updatedAddress.getCity(), result.getCity());
        assertEquals(updatedAddress.getStreet(), result.getStreet());
        assertEquals(updatedAddress.getState(), result.getState());
        assertEquals(updatedAddress.getZipCode(), result.getZipCode());

        verify(addressRepository, times(2)).findById(addressId);
        verify(addressRepository, times(1)).save(any(Address.class));
    }

    @Test
    public void testDeleteAddressById() {
        int addressId = 1;

        addressService.deleteAddressById(addressId);

        verify(addressRepository, times(1)).deleteById(addressId);
    }
    @Test
    public void testGetShippingAddressByCustomerId() {
        Integer customerId = 1;
        String addressType = "shippingAddress";
        Address address1 = new Address();
        Address address2 = new Address();

        Customer customer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        when(addressRepository.getShippingAddressByCustomerId(customerId, addressType))
                .thenReturn(Arrays.asList(address1, address2));

        AddressResponse addressResponse1 = new AddressResponse();
        AddressResponse addressResponse2 = new AddressResponse();
        when(mapper.map(any(Address.class), eq(AddressResponse.class))).thenReturn(addressResponse1, addressResponse2);

        List<AddressResponse> result = addressService.getShippingAddressByCustomerId(customerId, addressType);

        assertEquals(2, result.size());
        assertEquals(addressResponse1, result.get(0));
        assertEquals(addressResponse2, result.get(1));

        verify(customerRepository, times(1)).findById(customerId);
        verify(addressRepository, times(1)).getShippingAddressByCustomerId(customerId, addressType);
        verify(mapper, times(2)).map(any(Address.class), eq(AddressResponse.class));
    }


    @Test
    public void testGetBillingAddressByCustomerId() {
        Integer customerId = 1;
        String addressType = "billingAddress";
        AddressResponse addressResponse = new AddressResponse();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(addressRepository.getBillingAddressByCustomerId(customerId, addressType)).thenReturn(address1);
        when(mapper.map(address1, AddressResponse.class)).thenReturn(addressResponse);

        AddressResponse result = addressService.getBillingAddressByCustomerId(customerId, addressType);

        assertEquals(addressResponse, result);

        verify(customerRepository, times(1)).findById(customerId);
        verify(addressRepository, times(1)).getBillingAddressByCustomerId(customerId, addressType);
        verify(mapper, times(1)).map(address1, AddressResponse.class);
    }
}

