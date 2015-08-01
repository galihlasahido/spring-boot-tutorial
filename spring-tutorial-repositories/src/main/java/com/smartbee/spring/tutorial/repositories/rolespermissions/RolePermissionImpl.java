package com.smartbee.spring.tutorial.repositories.rolespermissions;


import com.smartbee.spring.tutorial.domain.RolesPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by galihlasahido on 10/5/14.
 */
@Service
public class RolePermissionImpl implements RolePermissionService {

    @Autowired(required = true)
    private RolePermissioneRepository repository;

    @Resource
    private Environment env;

    @Transactional
    @Override
    public RolesPermission save(RolesPermission dto) {
        return repository.save(dto);
    }

    @Transactional
    @Override
    public void delete(RolesPermission dto) {
        if (dto == null || dto.getId() == null) {
            return;
        }

        repository.delete(dto);

    }

    @Override
    public RolesPermission findRolesPermissionById(String id) {
        if (id==null) {
            return null;
        }

        return repository.findOne(id);
    }

    @Override
    public Page<RolesPermission> findAll(Pageable pageable) {
        if(pageable == null){
            pageable = new PageRequest(0, Integer.valueOf(env.getProperty("apps.paging.perpage")));
        }
        return repository.findAll(pageable);
    }

    @Override
    public List<RolesPermission> findAll() {
        return repository.findAll();
    }

}
