package miu.edu.onlineRetailSystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderLines")
public class OrderLine {

    @Id
    @GeneratedValue
    @Column(name = "orderLineID")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "itemID")
    private Item item;

    private int quantity;
    private double discount;

}
