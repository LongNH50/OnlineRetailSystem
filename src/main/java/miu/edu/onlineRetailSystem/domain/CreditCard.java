package miu.edu.onlineRetailSystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "creditCards")
public class CreditCard {

    @Id
    @GeneratedValue
    @Column(name = "creditCardID")
    private int id;
    private String number;
    private LocalDateTime expirationDate;
    private String securityCode;
}
