package com.smartbee.spring.tutorial.repositories.permission;


import com.smartbee.spring.tutorial.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by galihlasahido on 6/9/14.
 */
@Component
public interface PermissionService {
    public void save(Permission dto);
    public void delete(Permission dto);
    public Permission findPermissionById(String id);
    Page<Permission> findAll(Pageable pageable);
    List<Permission> findAll();
    Page<Permission> searchPermission(Pageable pageable, String mode, String value);
    public Permission findPermissionByLabel(String username);
    public Permission findPermissionByValue(String username);
    public Permission findPermissionByValueAndNotThisId(String value, String id);
}