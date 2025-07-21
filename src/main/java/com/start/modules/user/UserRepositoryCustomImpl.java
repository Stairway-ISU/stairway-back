package com.start.modules.user;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final EntityManager em;

    @Override
    public User findByEmailWithFilter(String email) {
        Session session = em.unwrap(Session.class);
        session.enableFilter("deletedFilter").setParameter("isDeleted", false);

        return em.createQuery(
                        "SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
