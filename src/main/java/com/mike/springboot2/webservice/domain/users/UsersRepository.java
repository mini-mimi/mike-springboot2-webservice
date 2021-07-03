package com.mike.springboot2.webservice.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {

    @Query("SELECT u FROM Users u WHERE u.users_email = ?1 AND u.users_id = ?2")
    Optional<Users> findByEmailAndId(String users_email, String users_id);
}
