package com.smartbee.spring.tutorial.repositories.rolespermissions;

import com.smartbee.spring.tutorial.domain.RolesPermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by galihlasahido on 10/3/14.
 */
@Component
public interface RolePermissionService {
    public RolesPermission save(RolesPermission dto);
    public void delete(RolesPermission dto);
    public RolesPermission findRolesPermissionById(String id);
    Page<RolesPermission> findAll(Pageable pageable);
    List<RolesPermission> findAll();

}