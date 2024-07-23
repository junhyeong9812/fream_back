package com.kream.root.admin.service;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.admin.repository.AdminMemberRepository;

import com.kream.root.entity.Delivery;
import com.kream.root.entity.Orders;
import com.kream.root.order.repository.DeliveryRepository;
import com.kream.root.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminMemberService {
    @Autowired
    private AdminMemberRepository adminMemberRepository;

    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;


    public List<UserListDTO> getAllUsers() {
        return adminMemberRepository.findUserAll();
    }
    public List<Orders> getAllOrders() {
        return adminMemberRepository.findOrdersAll();
    }
    public List<Delivery> getAllDeliveries() {
        return adminMemberRepository.findDeliveryAll();
    }
    @Transactional
    public void updateDeliveries(List<Delivery> deliveries) {
        adminMemberRepository.updateDeliveries(deliveries);
    }
    @Transactional
    public void refundOrder(Long orderId) {
        Orders order = ordersRepository.findById(orderId).orElse(null);
        if (order != null) {
            // 환불 처리 로직을 여기에 추가합니다.
            // 예를 들어, order의 상태를 '환불됨'으로 변경하는 등의 작업을 합니다.
            order.setStatus("refunded");
            ordersRepository.save(order);
        }
    }
//    @Transactional
//    public void updateDeliveries(List<Delivery> deliveries) {
//        for (Delivery delivery : deliveries) {
//            Delivery existingDelivery = deliveryRepository.findById(delivery.getDeliveryId()).orElse(null);
//            if (existingDelivery != null) {
//                existingDelivery.setCourierName(delivery.getCourierName());
//                existingDelivery.setTrackingNumber(delivery.getTrackingNumber());
//                existingDelivery.setDeliveryStatus("배송중"); // 배달 상태를 "배송중"으로 설정
//                deliveryRepository.save(existingDelivery);
//            }
//        }
//    }

    @Transactional//이렇게 따로 설정하면 우선권을 가져서 디폴트값 펄스가 존재
    public String join(UserListDTO user){
        //같은 이름의 중복 회원 확인
        validateDuplicateAdmin(user);
        //회원 정보 저장
        adminMemberRepository.save(user);

        return user.getUserId();
    }
    private void validateDuplicateAdmin(UserListDTO user) {
        //중복 시 EXCEPTIO
        List<UserListDTO> findAdmins = adminMemberRepository.findByName(user.getUserId());
        //동일한 이름을 가진 맴버 목록을가져온다.
        if(!findAdmins.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }//동일한 이름을 가진 맴버가 존재할 경우 throw를 통해 익셉션을 생성하고
        //새로운 익셉션을 생성한다.


    }


    //회원 단일 조회
//    @Transactional(readOnly = true)
    public UserListDTO findOne(Long ULID){
        return adminMemberRepository.findOne(ULID);

    }
    //정보 업데이트
    @Transactional
    public UserListDTO updateAdmin(Long id, UserListDTO user) {
        UserListDTO existingUser = adminMemberRepository.findOne(id);
        if (existingUser == null) {
            throw new IllegalStateException("해당 회원이 존재하지 않습니다.");
        }
        UserListDTO updatedUser = new UserListDTO.Builder()
                .ulid(existingUser.getUlid())
                .userId(user.getUserId())
                .userPw(user.getUserPw())
                .userName(user.getUsername())
                .age(user.getAge())
                .phone(user.getPhone())
                .email(user.getEmail())
                .profileUrl(user.getProfileUrl())
                .profileName(user.getProfileName())
                .userSize(user.getUserSize())
                .userBio(user.getUserBio())
                .receiveEmail(user.getReceiveEmail())
                .receiveMessage(user.getReceiveMessage())
                .blockedProfiles(user.getBlockedProfiles())
                .favoriteProducts(user.getFavoriteProducts())
                .roles(user.getRoles())
                .build();

        return adminMemberRepository.update(updatedUser);
    }

    public List<Delivery> getDeliveriesByStatus(String status) {
        return deliveryRepository.findByDeliveryStatus(status);
    }
}
