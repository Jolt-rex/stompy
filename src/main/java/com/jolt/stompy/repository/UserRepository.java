package com.jolt.stompy.repository;

import com.jolt.stompy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findUsersByProjectsId(int id);

    @Query(value = "SELECT * FROM users u WHERE u.email=:email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value="DELETE u, pu FROM users u INNER JOIN projects_users pu WHERE u.user_id=pu.user_id AND u.user_id=:id", nativeQuery = true)
    void deleteById(@Param("id") int id);
}
