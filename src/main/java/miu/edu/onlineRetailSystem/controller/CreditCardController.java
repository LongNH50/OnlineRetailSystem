package miu.edu.onlineRetailSystem.controller;

import miu.edu.onlineRetailSystem.contract.CreditCardResponse;
import miu.edu.onlineRetailSystem.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditCards")
public class CreditCardController {
    @Autowired
    CreditCardService creditCardService;


    @GetMapping("/{id}")
    public ResponseEntity<CreditCardResponse> getCreditCardById(@PathVariable int id) {
//        CreditCardResponse creditCardResponse = creditCardService.findById(id);
        return ResponseEntity.ok(creditCardService.findById(id));
    }
    @PostMapping
    public ResponseEntity<CreditCardResponse> createCreditCard(@RequestBody CreditCardResponse creditCardResponse) {
        return ResponseEntity.ok(creditCardService.save(creditCardResponse));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CreditCardResponse> removeCreditCard(@PathVariable int id) {
        CreditCardResponse creditCardResponse = creditCardService.findById(id);
        creditCardService.remove(id);
        return ResponseEntity.ok(creditCardResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardResponse> editCreditCard(@PathVariable int id,
                                                             @RequestBody CreditCardResponse creditCardResponse) {
        creditCardService.update(id,creditCardResponse);
        return ResponseEntity.ok(creditCardResponse);

    }

}
