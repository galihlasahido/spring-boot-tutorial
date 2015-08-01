package com.smartbee.spring.tutorial.repositories.permission;

import com.smartbee.spring.tutorial.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by galihlasahido on 10/5/14.
 */
public interface PermissionRepository extends JpaRepository<Permission, String> {
    public Page<Permission> getPermissionByLabelIgnoreCaseContaining(Pageable pageable, String label);
    public Permission getPermissionByLabelIgnoreCase(String label);
    public Permission getPermissionByValueIgnoreCase(String label);
    public Permission findPermissionByValueIgnoreCaseAndIdNot(String value, String id);

}