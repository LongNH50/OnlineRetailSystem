package miu.edu.onlineRetailSystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String description;
    private int stars;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "buyerID")
    private Customer buyer;

}
