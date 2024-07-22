package com.kream.root.order.repository;

import com.kream.root.entity.RefundAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundAccountRepository extends JpaRepository<RefundAccount, Integer> {
    // Optional: 사용자 ULID를 통해 환불 계좌를 찾는 메서드
    RefundAccount findByUserUlid(int ulid);
}
