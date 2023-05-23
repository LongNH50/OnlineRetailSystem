package miu.edu.onlineRetailSystem.controller;

import miu.edu.onlineRetailSystem.contract.OrderLineResponse;
import miu.edu.onlineRetailSystem.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orderlines")
public class OrderLineController {
//    @Autowired
//    private OrderLineService orderLineService;
//
//    @GetMapping
//    public ResponseEntity<List<OrderLineResponse>> getAllOrderLines() {
//        List<OrderLineResponse> orderLines = orderLineService.getAllOrderLines();
//        return new ResponseEntity<>(orderLines, HttpStatus.OK);
//    }
//
//    @GetMapping("/{orderLineId}")
//    public ResponseEntity<OrderLineResponse> getOrderLine(@PathVariable("orderLineId") int id) {
//        OrderLineResponse orderLine = orderLineService.findById(id);
//        System.out.println(orderLine);
//        return new ResponseEntity<>(orderLine, HttpStatus.OK);
//    }
}
