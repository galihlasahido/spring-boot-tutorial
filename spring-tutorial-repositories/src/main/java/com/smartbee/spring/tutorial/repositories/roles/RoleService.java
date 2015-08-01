package com.smartbee.spring.tutorial.repositories.roles;

import com.smartbee.spring.tutorial.domain.Roles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by galihlasahido on 10/3/14.
 */
@Component
public interface RoleService {
    public void save(Roles dto);
    public void delete(Roles dto);
    public Roles findRolesById(String id);
    Page<Roles> findAll(Pageable pageable);
    List<Roles> findAll();

    public Roles findRolesByName(String name);
    public Roles findRolesByNameAndNotThisId(String username, String id);
    Page<Roles> findAllRoles(Pageable pageable);
    Page<Roles> searchRoles(Pageable pageable, String mode, String value);

}