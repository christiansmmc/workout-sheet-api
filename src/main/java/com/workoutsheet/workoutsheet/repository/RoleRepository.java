package com.workoutsheet.workoutsheet.repository;

import com.workoutsheet.workoutsheet.domain.Role;
import com.workoutsheet.workoutsheet.domain.User;
import com.workoutsheet.workoutsheet.domain.enumeration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}
