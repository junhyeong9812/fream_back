package com.kream.root.admin.repository;


import com.kream.root.Login.model.UserListDTO;

import com.kream.root.entity.Delivery;
import com.kream.root.entity.Orders;
import com.kream.root.order.repository.DeliveryRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminMemberRepository {
    private final EntityManager em;
    public void save(UserListDTO user) {
        em.persist(user);
    }
    //단일조회
    public UserListDTO findOne(Long id) {
        return em.find(UserListDTO.class, id);
        //id값을 통해 맴버 정보 단일조회
    }

    //유저 목록조회
    public List<UserListDTO> findUserAll() {
        //이때는
        return em.createQuery("select m from UserListDTO m", UserListDTO.class)
                .getResultList();

    }
    public List<Orders> findOrdersAll() {
        //이때는
        return em.createQuery("select m from Orders m", Orders.class)
                .getResultList();

    }
    public List<Delivery> findDeliveryAll() {
        //이때는
        return em.createQuery("select m from Delivery m", Delivery.class)
                .getResultList();

    }

    public List<UserListDTO> findByName(String name){
        //파라미터를 이름으로 넘겨서
        return em.createQuery("select m from UserListDTO m where m.Username= :name",UserListDTO.class)
                .setParameter("name",name).getResultList();
        //이렇게 하면 파라미터가 바인딩 되고 결과값을 리졀트 리스트를 통해 가져온다.
        //
    }
    //정보 업데이트
    public UserListDTO update(UserListDTO user) {
        return em.merge(user);
    }
    @Transactional
    public void updateDeliveries(List<Delivery> deliveries) {
        for (Delivery delivery : deliveries) {
            Delivery existingDelivery = em.find(Delivery.class, delivery.getDeliveryId());
            if (existingDelivery != null) {
                existingDelivery.setCourierName(delivery.getCourierName());
                existingDelivery.setTrackingNumber(delivery.getTrackingNumber());
                existingDelivery.setDeliveryStatus("배송중"); // 배달 상태를 "배송중"으로 설정
                em.merge(existingDelivery);
            }
        }
    }



}
