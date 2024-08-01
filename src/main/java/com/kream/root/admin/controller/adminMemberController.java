package com.kream.root.admin.controller;


import com.kream.root.Login.model.UserListDTO;
import com.kream.root.Login.repository.UserListRepository;
import com.kream.root.admin.domain.Admin;
import com.kream.root.admin.service.AdminMemberService;
import com.kream.root.entity.Delivery;
import com.kream.root.entity.Orders;
import com.kream.root.order.repository.DeliveryRepository;
import com.kream.root.order.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class adminMemberController {
    @Autowired
    AdminMemberService adminMemberService;

    @Autowired
    UserListRepository userListRepository;

    @Autowired
    OrdersRepository ordersRepository;


//    @GetMapping("/memberuser")
//    public List<UserListDTO> getAllUsers() {
//        System.out.println("adminUser = ");
//        return adminMemberService.getAllUsers();
//    }
    @GetMapping("/memberuser")
    public Page<UserListDTO> listUsers(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        size=size*2;
        Pageable pageable = PageRequest.of(page, size);
        return userListRepository.findAll(pageable);
    }
    @GetMapping("/modifyAdmin/{id}")
    public ResponseEntity<UserListDTO> getAdmin(@PathVariable Long id) {
        UserListDTO user = adminMemberService.findOne(id);
        return ResponseEntity.ok(user);
    }
//    @GetMapping("/orders")
//    public List<Orders> getAllOrders() {
//        return adminMemberService.getAllOrders();
//    }
@GetMapping("/orders")
public Page<Orders> getOrders(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return ordersRepository.findAll(pageable); // 모든 주문을 페이지네이션된 형태로 가져오는 서비스 메소드
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
