package com.kream.root.order.controller;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.entity.AddressBook;
import com.kream.root.entity.Orders;
import com.kream.root.order.DTO.OrderDTO;
import com.kream.root.order.PaymentInfo;
import com.kream.root.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/create")
    public String createOrder(@RequestBody PaymentInfo paymentInfo) {
        System.out.println("paymentInfo = " + paymentInfo);
        try {
            orderService.handlePayment(paymentInfo);
            return "Order created successfully";
        } catch (Exception e) {
            return "Failed to create order: " + e.getMessage();
        }
    }

    @GetMapping("/user/{userId}")
    public UserListDTO getUserById(@PathVariable String userId) {
        return orderService.getUserById(userId);
    }

    @GetMapping("/address/{userId}")
    public List<AddressBook> getAddressBooksByUserId(@PathVariable int userId) {
        return orderService.getSortedAddressBooksByUserId(userId);
    }
    @PutMapping("/refundOrder")
    public ResponseEntity<String> refundOrder(@RequestBody Map<String, Long> request) {
        
        Long orderId = request.get("orderId");
        System.out.println("orderId = " + orderId);
        try {
            orderService.cancelPayment(orderId);
            return ResponseEntity.ok("환불 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to cancel payment: " + e.getMessage());
        }
    }
//    @GetMapping("/member/orders")
//    public List<Orders> getAllOrders() {
//        return orderService.getAllOrders();
//    }
@GetMapping("/buy")
public ResponseEntity<List<OrderDTO>> getOrders(Authentication authentication) {
    // Authentication 객체에서 UserDetails를 추출하여 사용자 ID를 얻습니다.
    String userId = ((UserDetails) authentication.getPrincipal()).getUsername();
    List<OrderDTO> ordersList = orderService.getOrdersByUserId(userId);
    return ResponseEntity.ok(ordersList);
}

}
