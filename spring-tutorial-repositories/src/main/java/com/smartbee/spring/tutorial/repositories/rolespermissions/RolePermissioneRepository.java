package com.smartbee.spring.tutorial.repositories.rolespermissions;


import com.smartbee.spring.tutorial.domain.RolesPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by galihlasahido on 10/5/14.
 */
public interface RolePermissioneRepository extends JpaRepository<RolesPermission, String> {
}
