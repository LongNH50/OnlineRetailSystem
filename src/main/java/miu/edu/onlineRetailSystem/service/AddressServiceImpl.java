package miu.edu.onlineRetailSystem.service;

import jakarta.transaction.Transactional;
import miu.edu.onlineRetailSystem.contract.AddressResponse;
import miu.edu.onlineRetailSystem.domain.Address;
import miu.edu.onlineRetailSystem.domain.AddressType;
import miu.edu.onlineRetailSystem.domain.Customer;
import miu.edu.onlineRetailSystem.exception.ResourceNotFoundException;
import miu.edu.onlineRetailSystem.repository.AddressRepository;
import miu.edu.onlineRetailSystem.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public AddressResponse save(int customerID, AddressResponse addressResponse) {
        Address address = mapper.map(addressResponse, Address.class);
        Customer customer = customerRepository.findById(customerID).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Id", customerID)
        );
        address.setCustomer(customer);
        address = addressRepository.save(address);
        if (customer.getDefaultShippingAddress() == null){
            customer.setDefaultShippingAddress(address);
            customerRepository.save(customer);
        }

        return mapper.map(address, AddressResponse.class);
    }

    @Override
    public AddressResponse update(int customerId, int addressId, AddressResponse addressResponse) {
        Address address = addressRepository.findByIdAndCustomer(customerId, addressId);
        if (address == null || addressResponse.getId() != addressId)
            throw new ResourceNotFoundException("Address", "Id", addressId);

        address = mapper.map(addressResponse, Address.class);

        address = addressRepository.save(address);

        return mapper.map(address, AddressResponse.class);
    }

    public void delete(int customerId, int addressId) {
        Address address = addressRepository.findByIdAndCustomer(customerId, addressId);
        if (address == null)
            throw new ResourceNotFoundException("Address", "Id", addressId);

        addressRepository.deleteById(addressId);
    }

    @Override
    public List<AddressResponse> getShippingAddressByCustomerId(Integer customerId, AddressType addressType) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "Id", customerId));

        return addressRepository.getShippingAddressByCustomerId(customerId, addressType)
                .stream().map(entity -> mapper.map(entity, AddressResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public AddressResponse getBillingAddressByCustomerId(Integer customerId, AddressType addressType) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return mapper.map(addressRepository.getBillingAddressByCustomerId(customerId, addressType), AddressResponse.class);

    }

    @Override
    public Collection<AddressResponse> getAddresses(int customerId) {
        Collection<AddressResponse> responses = addressRepository.findByCustomer(customerId)
                .stream()
                .map(address -> mapper.map(address, AddressResponse.class))
                .toList();
        for (AddressResponse addr : responses
        ) {
            System.out.println(addr);

        }
        return responses;
    }

    @Override
    public AddressResponse getAddress(int customerId, int addressId) {
        Address address = addressRepository.findByIdAndCustomer(customerId, addressId);
        if (address == null)
            throw new ResourceNotFoundException("Address", "Id", addressId);

        return mapper.map(address, AddressResponse.class);
    }


}
