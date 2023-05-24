package miu.edu.onlineRetailSystem.controller;

import miu.edu.onlineRetailSystem.contract.OrderLineResponse;
import miu.edu.onlineRetailSystem.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderlines")
public class OrderLineController {
    @Autowired
    private OrderLineService orderLineService;

    @GetMapping
    public ResponseEntity<List<OrderLineResponse>> getAllOrderLines() {
        List<OrderLineResponse> orderLines = orderLineService.getAllOrderLines();
        return new ResponseEntity<>(orderLines, HttpStatus.OK);
    }

    @GetMapping("/{orderLineId}")
    public ResponseEntity<OrderLineResponse> getOrderLine(@PathVariable("orderLineId") int id) {
        OrderLineResponse orderLine = orderLineService.findById(id);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }

    @PutMapping("/{orderLineId}")
    public ResponseEntity<OrderLineResponse> updateOrderLine(@PathVariable("orderLineId") int id, @RequestBody OrderLineResponse orderLineResponse) {
        OrderLineResponse orderLine = orderLineService.update(id, orderLineResponse);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderLineResponse> saveOrderLine(@RequestBody OrderLineResponse orderLineResponse) {
        OrderLineResponse orderLine = orderLineService.save(orderLineResponse);
        return new ResponseEntity<>(orderLine, HttpStatus.OK);
    }
}
