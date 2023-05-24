package miu.edu.onlineRetailSystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public  class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemID")
    private int id;

    @NotNull
    private String name;
    private String description;

    @NotNull
    private double price;

    private byte[] image;
    @NotNull
    private String barcodeNumber;
    @NotNull
    private int quantityInStock;

@OneToMany(cascade = CascadeType.DETACH)
    private List<Review> reviews = new ArrayList<>();
}
