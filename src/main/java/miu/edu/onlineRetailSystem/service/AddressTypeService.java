package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contact.AddressResponse;
import miu.edu.onlineRetailSystem.contact.AddressTypeResponse;

public interface AddressTypeService {

    void save(AddressTypeResponse addressTypeResponse);
    AddressTypeResponse update(int addressTypeId, AddressTypeResponse addressTypeResponse);


//    AddressTypeResponse update(AddressTypeResponse addressTypeResponse);
}
