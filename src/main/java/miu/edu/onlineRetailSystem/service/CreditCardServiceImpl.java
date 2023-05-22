package miu.edu.onlineRetailSystem.service;

import jakarta.persistence.EntityNotFoundException;
import miu.edu.onlineRetailSystem.contract.CreditCardResponse;
import miu.edu.onlineRetailSystem.domain.CreditCard;
import miu.edu.onlineRetailSystem.repository.CreditCardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService{
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CreditCardRepository creditCardRepository;
    @Override
    public CreditCardResponse save(CreditCardResponse creditCardResponse) {

        CreditCard creditCard = mapper.map(creditCardResponse, CreditCard.class);

        creditCardRepository.save(creditCard);
        return creditCardResponse;
    }

    @Override
    public CreditCardResponse update(int creditCardId, CreditCardResponse creditCardResponse) {
        CreditCard creditCard = creditCardRepository.findById(creditCardId).orElseThrow(() ->new EntityNotFoundException("credit card not found"));
//        CreditCard creditCard = mapper.map(creditCardResponse, CreditCard.class);
        creditCard.setNumber(creditCardResponse.getNumber());
        creditCard.setExpirationDate(creditCardResponse.getExpirationDate());
        creditCard.setSecurityCode(creditCardResponse.getSecurityCode());
        creditCardRepository.save(creditCard);
        return creditCardResponse;
    }

    @Override
    public CreditCardResponse remove(int id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("credit card not found"));
        creditCardRepository.delete(creditCard);
        CreditCardResponse creditCardresponse = mapper.map(creditCard, CreditCardResponse.class);
        return creditCardresponse;
    }

    @Override
    public CreditCardResponse findById(int id) {
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("credit card not found"));
        CreditCardResponse creditCardresponse = mapper.map(creditCard, CreditCardResponse.class);
        return creditCardresponse;
    }

}
