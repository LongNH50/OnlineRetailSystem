package miu.edu.onlineRetailSystem.domain;

import jakarta.persistence.*;
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
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "itemID")
    private int id;
    private String name;
    private String description;
    private double price;
    private byte[] image;
    private String barcodeNumber;
    private int quantityInStock;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ItemReview",
    joinColumns = @JoinColumn(name = "itemID"),
    inverseJoinColumns = @JoinColumn(name = "reviewID"))
    private List<Review> reviews = new ArrayList<>();
}
