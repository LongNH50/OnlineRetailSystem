package miu.edu.onlineRetailSystem.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum AddressType {
    BILLING_ADDRESS,
    SHIPPING_ADDRESS
}
