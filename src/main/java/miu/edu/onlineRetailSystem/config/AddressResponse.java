package miu.edu.onlineRetailSystem.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.edu.onlineRetailSystem.domain.AddressType;
import miu.edu.onlineRetailSystem.domain.Customer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private int id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private boolean isDefault;

    private CustomerResponse customerResponse;

    private AddressTypeResponse addressTypeResponse;


}
