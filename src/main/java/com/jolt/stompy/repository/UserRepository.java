package com.jolt.stompy.repository;

import com.jolt.stompy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findUsersByProjectsId(int id);

    @Query(value = "SELECT * FROM users u WHERE u.email=:email", nativeQuery = true)
    User findByEmail(@Param("email") String email);
}
