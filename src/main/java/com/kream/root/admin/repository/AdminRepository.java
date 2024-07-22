package com.kream.root.admin.repository;

import com.kream.root.Login.model.UserListDTO;
import com.kream.root.admin.domain.Admin;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminRepository {
    private final EntityManager em;
    public void save(Admin admin) {
        em.persist(admin);
    }
    //단일조회
    public Admin findOne(Long id) {
        return em.find(Admin.class, id);
        //id값을 통해 맴버 정보 단일조회
    }
    //어드민 목록조회
    public List<Admin> findAll() {
        //이때는
        return em.createQuery("select m from Admin m", Admin.class)
                .getResultList();

    }


    public List<Admin> findByName(String name){
        //파라미터를 이름으로 넘겨서
        return em.createQuery("select m from Member m where m.USERID= :name",Admin.class)
                .setParameter("name",name).getResultList();
        //이렇게 하면 파라미터가 바인딩 되고 결과값을 리졀트 리스트를 통해 가져온다.
        //
    }
    //정보 업데이트
    public Admin update(Admin admin) {
        return em.merge(admin);
    }
}
