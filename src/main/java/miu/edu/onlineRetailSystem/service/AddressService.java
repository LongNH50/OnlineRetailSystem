package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.AddressResponse;
import miu.edu.onlineRetailSystem.contract.AddressTypeResponse;
import miu.edu.onlineRetailSystem.domain.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AddressService {
    public void save(Address address);
    Address update(int addressId, Address address);

    void deleteAddressById(int id);
    List<AddressResponse> getShippingAddressByCustomerId(Integer customerId, String shippingAddress);
    AddressResponse getBillingAddressByCustomerId(Integer customerId, String billingAddress);


}
