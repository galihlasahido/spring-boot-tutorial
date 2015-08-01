package com.smartbee.spring.tutorial.repositories.roles;


import com.smartbee.spring.tutorial.domain.Roles;
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
public class RoleImpl implements RoleService {

    @Autowired(required = true)
    private RoleRepository repository;

    @Resource
    private Environment env;

    @Transactional
    @Override
    public void save(Roles dto) {
        repository.save(dto);

    }

    @Transactional
    @Override
    public void delete(Roles dto) {
        if (dto == null || dto.getId() == null) {
            return;
        }

        repository.delete(dto);

    }

    @Override
    public Roles findRolesById(String id) {
        if (id==null) {
            return null;
        }

        return repository.findOne(id);
    }

    @Override
    public Page<Roles> findAll(Pageable pageable) {
        if(pageable == null){
            pageable = new PageRequest(0, Integer.valueOf(env.getProperty("apps.paging.perpage")));
        }
        return repository.findAll(pageable);
    }

    @Override
    public List<Roles> findAll() {
        return repository.findAll();
    }

    @Override
    public Roles findRolesByName(String name) {
        if(name==null) return null;
        return repository.findRolesByIdIgnoreCase(name);
    }

    @Override
    public Roles findRolesByNameAndNotThisId(String name, String id) {
        if(name==null) return null;
        return repository.findRolesByIdAndIdNotAllIgnoreCase(name, id);
    }

    @Override
    public Page<Roles> findAllRoles(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Roles> searchRoles(Pageable pageable, String mode, String value) {
        Page<Roles> page = null;
        if(mode==null) {
            return null;
        }

        if(value==null) {
            return null;
        }

        switch (mode) {
            case"name":
            default:
                page = repository.getRolesByNameIgnoreCaseContaining(pageable,value);
                break;
            case"id":
                page = repository.getRolesByIdIgnoreCaseContaining(pageable,value);
                break;
        }

        return page;    }

}
