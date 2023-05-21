package miu.edu.onlineRetailSystem.service;

import miu.edu.onlineRetailSystem.contract.CreditCardResponse;

public interface CreditCardService {
    CreditCardResponse save(CreditCardResponse creditCardResponse);

    CustomerService update(int creditCardId, CreditCardResponse creditCardResponse);

    CustomerService remove(int creditCard);
}
