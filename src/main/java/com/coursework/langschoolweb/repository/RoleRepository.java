package com.coursework.langschoolweb.repository;

import com.coursework.langschoolweb.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
