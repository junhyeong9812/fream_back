package com.kream.root.admin.repository;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.entity.SellerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SellerProductRepository extends JpaRepository<SellerProduct, Long> {
    List<SellerProduct> findByIsSold(char isSold);
    List<SellerProduct> findByUser(UserListDTO user);
}
