package miu.edu.onlineRetailSystem.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressTypeResponse {

    private Integer id;
    private String name;

    public AddressTypeResponse(String name) {
        this.name = name;
    }
}
