package com.jolt.stompy.repository;

import com.jolt.stompy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findUsersByProjectsId(int id);
}
