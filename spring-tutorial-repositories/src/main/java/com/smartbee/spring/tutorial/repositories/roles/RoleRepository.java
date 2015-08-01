package com.smartbee.spring.tutorial.repositories.roles;

import com.smartbee.spring.tutorial.domain.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by galihlasahido on 10/5/14.
 */
public interface RoleRepository extends JpaRepository<Roles, String> {
    public Roles findRolesByIdIgnoreCase(String username);
    public Page<Roles> getRolesByNameIgnoreCaseContaining(Pageable pageable, String username);
    public Page<Roles> getRolesByIdIgnoreCaseContaining(Pageable pageable, String fullname);
    public Roles findRolesByIdAndIdNotAllIgnoreCase(String username, String id);

}
