package miu.edu.onlineRetailSystem.controller;

import miu.edu.onlineRetailSystem.contract.AddressResponse;
import miu.edu.onlineRetailSystem.domain.Address;
import miu.edu.onlineRetailSystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    AddressService addressService;


    @PostMapping("/addresses")
    public ResponseEntity<?> save(@RequestBody Address address){
        addressService.save(address);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }
    @PostMapping("/addresses/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody Address address){
        addressService.update(id,address);
        return new ResponseEntity<Address>(address, HttpStatus.OK);
    }
    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        addressService.deleteAddressById(id);
        return new ResponseEntity<Address>(HttpStatus.OK);
    }
    @GetMapping("/addresses/{id}/shipping/{shippingAddress}")
    public List<AddressResponse> getShippingAddresses(@PathVariable Integer id, @PathVariable String shippingAddress){
        List<AddressResponse> addressesList =  addressService.getShippingAddressByCustomerId(id,shippingAddress);
        return addressesList;
    }

    @GetMapping("/addresses/{id}/billing/{billingAddress}")
    public AddressResponse getBillingAddresses(@PathVariable Integer id, @PathVariable String billingAddress) {
        AddressResponse address = addressService.getBillingAddressByCustomerId(id, billingAddress);
        return address;
    }


}
