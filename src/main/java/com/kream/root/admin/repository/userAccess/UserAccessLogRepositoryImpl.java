package com.kream.root.admin.repository.userAccess;

import com.kream.root.admin.domain.UserAccessLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserAccessLogRepositoryImpl implements UserAccessLogRepository {
    @PersistenceContext
    private EntityManager em;
//    @PersistenceContext:
//    JPA의 표준 애노테이션으로,
//    Spring에서 JPA EntityManager를 주입하는 데 사용됩니다.
//    이 방법은 더 명확하게 JPA 컨텍스트를 주입받는다는 것을 나타냅니다.
//    @Autowired:
//    Spring의 일반적인 의존성 주입 애노테이션으로,
//    JPA뿐만 아니라 다른 Spring 빈도 주입받을 수 있습니다.


    @Transactional
    @Override
    public void save(UserAccessLog userAccessLog) {
        em.persist(userAccessLog);
    }

    @Override
    public UserAccessLog findOne(Long id) {
        return em.find(UserAccessLog.class, id);
    }

    @Override
    public List<UserAccessLog> findAll() {
        return em.createQuery("SELECT u FROM UserAccessLog u", UserAccessLog.class).getResultList();
    }

    @Override
    public List<UserAccessLog> findByUserId(String userId) {
        return em.createQuery("SELECT u FROM UserAccessLog u WHERE u.userId = :userId", UserAccessLog.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
