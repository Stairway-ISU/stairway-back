package com.start.modules.user;

public interface UserRepositoryCustom {
    User findByEmailWithFilter(String email);
}
