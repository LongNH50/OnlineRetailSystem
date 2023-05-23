package miu.edu.onlineRetailSystem.controller;

import miu.edu.onlineRetailSystem.contract.CustomerErrorResponse;
import miu.edu.onlineRetailSystem.contract.OrderResponse;
import miu.edu.onlineRetailSystem.contract.OrderStatusResponse;
import miu.edu.onlineRetailSystem.exceptionHandlers.CustomerException;
import miu.edu.onlineRetailSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
//    @Autowired
//    private OrderService orderService;
//
//    @GetMapping("/{customerId}/customer")
//    public ResponseEntity<?> findAllByCustomer(@PathVariable int customerId, Pageable pageable) {
//        return new ResponseEntity<>(orderService.findAllByCustomer(customerId, pageable), HttpStatus.OK);
//    }
//
//    @GetMapping("/{orderId}")
//    public ResponseEntity<?> getOrder(@PathVariable int orderId) {
//        OrderResponse orderResponse = orderService.getOrder(orderId);
//        if (orderResponse == null)
//            return new ResponseEntity<>(new CustomerErrorResponse("Order does not exist!"), HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
//    }
//
//    @PostMapping
//    public ResponseEntity<?> save(@RequestBody OrderResponse orderResponse) {
//        OrderResponse savedOrderResponse = orderService.save(orderResponse);
//
//        return new ResponseEntity<>(savedOrderResponse, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{orderId}")
//    public ResponseEntity<?> update(@PathVariable int orderId, @RequestBody OrderResponse orderResponse) {
//        OrderResponse modifiedOrderResponse = orderService.update(orderId, orderResponse);
//        if (modifiedOrderResponse == null) {
//            return new ResponseEntity<>(new CustomerErrorResponse("Order does not exist or it has already been placed!"), HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(modifiedOrderResponse, HttpStatus.OK);
//    }
//
//    @GetMapping("/{orderId}/place")
//    public ResponseEntity<?> placeOrder(@PathVariable int orderId) {
//        try {
//            OrderResponse orderResponse = orderService.placeOrder(orderId);
//            if (orderResponse != null)
//                return new ResponseEntity<>(orderResponse, HttpStatus.OK);
//
//        } catch (CustomerException e) {
//            return new ResponseEntity<>(new CustomerErrorResponse(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        return new ResponseEntity<>(new CustomerErrorResponse("Order does not exist!"), HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping("/{orderId}/update-status")
//    public ResponseEntity<?> updateStatus(@PathVariable int orderId, OrderStatusResponse orderStatusResponse) {
//        OrderResponse orderResponse = orderService.updateStatus(orderId, orderStatusResponse);
//        if (orderResponse == null)
//            return new ResponseEntity<>(new CustomerErrorResponse("Order does not exist!"), HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
//    }
}
