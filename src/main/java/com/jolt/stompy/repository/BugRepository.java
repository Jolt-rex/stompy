package com.jolt.stompy.repository;

import com.jolt.stompy.model.Bug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugRepository extends JpaRepository<Bug, Integer> {


}
