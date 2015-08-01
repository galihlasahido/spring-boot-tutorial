package com.smartbee.spring.tutorial.repositories.permission;

import com.smartbee.spring.tutorial.domain.Permission;
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
public class PermissionImpl implements PermissionService {
    @Autowired(required = true)
    private PermissionRepository repository;

    @Resource
    private Environment env;

    @Transactional
    @Override
    public void save(Permission dto) {
        repository.save(dto);
    }

    @Transactional
    @Override
    public void delete(Permission dto) {
        if (dto == null || dto.getId() == null) {
            return;
        }

        repository.delete(dto);
    }

    @Override
    public Permission findPermissionById(String id) {
        if (id==null) {
            return null;
        }

        return repository.findOne(id);
    }

    @Override
    public Page<Permission> findAll(Pageable pageable) {
        if(pageable == null){
            pageable = new PageRequest(0, Integer.valueOf(env.getProperty("apps.paging.perpage")));
        }
        return repository.findAll(pageable);
    }

    @Override
    public List<Permission> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Permission> searchPermission(Pageable pageable, String mode, String value) {
        Page<Permission> page = null;
        if(mode==null) {
            return null;
        }

        if(value==null) {
            return null;
        }

        switch (mode) {
            case"label":
            default:
                page = repository.getPermissionByLabelIgnoreCaseContaining(pageable, value);
                break;
        }

        return page;

    }

    @Override
    public Permission findPermissionByLabel(String label) {
        if (label==null) {
            return null;
        }

        return repository.getPermissionByLabelIgnoreCase(label);
    }

    @Override
    public Permission findPermissionByValue(String value) {
        if (value==null) {
            return null;
        }

        return repository.getPermissionByValueIgnoreCase(value);
    }

    @Override
    public Permission findPermissionByValueAndNotThisId(String value, String id) {
        if (value==null) {
            return null;
        }

        if (id==null) {
            return null;
        }

        return repository.findPermissionByValueIgnoreCaseAndIdNot(value, id);

    }
}
