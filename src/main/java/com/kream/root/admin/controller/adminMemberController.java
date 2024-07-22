package com.kream.root.admin.controller;


import com.kream.root.Login.model.UserListDTO;
import com.kream.root.admin.domain.Admin;
import com.kream.root.admin.service.AdminMemberService;
import com.kream.root.entity.Delivery;
import com.kream.root.entity.Orders;
import com.kream.root.order.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class adminMemberController {
    @Autowired
    AdminMemberService adminMemberService;



    @GetMapping("/memberuser")
    public List<UserListDTO> getAllUsers() {
        System.out.println("adminUser = ");
        return adminMemberService.getAllUsers();
    }
    @GetMapping("/modifyAdmin/{id}")
    public ResponseEntity<UserListDTO> getAdmin(@PathVariable Long id) {
        UserListDTO user = adminMemberService.findOne(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/orders")
    public List<Orders> getAllOrders() {
        return adminMemberService.getAllOrders();
    }

    @GetMapping("/deliveries")
    public List<Delivery> getAllDeliveries() {
        return adminMemberService.getAllDeliveries();
    }
    @PutMapping("/modifyDelivery")
    public ResponseEntity<String> modifyDeliveries(@RequestBody List<Delivery> deliveries) {
        adminMemberService.updateDeliveries(deliveries);
        return ResponseEntity.ok("업데이트 완료");
    }
    @PutMapping("/refundOrder")
    public ResponseEntity<String> refundOrder(@RequestBody Long orderId) {
        adminMemberService.refundOrder(orderId);
        return ResponseEntity.ok("환불 완료");
    }
    @GetMapping("/deliveriesAlam")
    public ResponseEntity<List<Delivery>> getDeliveriesByStatus(@RequestParam String status) {
        List<Delivery> deliveries = adminMemberService.getDeliveriesByStatus(status);
        return ResponseEntity.ok(deliveries);
    }

}
