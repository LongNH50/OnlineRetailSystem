package miu.edu.onlineRetailSystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addressTypes")
public class AddressType {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    public AddressType(String name) {
        this.name = name;
    }
}