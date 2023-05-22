package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.AddressResponse;
import miu.edu.onlineRetailSystem.domain.Address;
import miu.edu.onlineRetailSystem.domain.Customer;
import miu.edu.onlineRetailSystem.repository.AddressRepository;
import miu.edu.onlineRetailSystem.repository.AddressTypeRepository;
import miu.edu.onlineRetailSystem.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressTypeRepository addressTypeRepository;

    @Override
    public void save(Address address) {
        customerRepository.save(address.getCustomer());
        addressTypeRepository.save(address.getAddressType());
        addressRepository.save(address);
    }

    @Override
    public Address update(int addressId, Address address) {
        Address foundAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        foundAddress.setCity(address.getCity());
        foundAddress.setStreet(address.getStreet());
        foundAddress.setState(address.getState());
        foundAddress.setZipCode(address.getZipCode());
         addressRepository.save(foundAddress);
         return addressRepository.findById(addressId)
                 .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
    }

    public void deleteAddressById(int id){
        addressRepository.deleteById(id);
    }

    @Override
    public List<AddressResponse> getShippingAddressByCustomerId(Integer customerId , String addressType) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        List<AddressResponse> addresses = addressRepository.getShippingAddressByCustomerId(customerId,"shippingAddress")
                .stream().map(entity -> mapper.map(entity, AddressResponse.class))
                .collect(Collectors.toList());
        return addresses;

    }
    @Override
    public AddressResponse getBillingAddressByCustomerId(Integer customerId , String addressType) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        AddressResponse address = mapper.map(addressRepository.getBillingAddressByCustomerId(customerId,"billingAddress"), AddressResponse.class);
        return address;

    }



}
