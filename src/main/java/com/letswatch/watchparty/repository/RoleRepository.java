package com.letswatch.watchparty.repository;

import com.letswatch.watchparty.models.Role;
import com.letswatch.watchparty.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
