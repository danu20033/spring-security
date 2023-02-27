package com.danushka.test.springsecurityclient.repository;

import com.danushka.test.springsecurityclient.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
