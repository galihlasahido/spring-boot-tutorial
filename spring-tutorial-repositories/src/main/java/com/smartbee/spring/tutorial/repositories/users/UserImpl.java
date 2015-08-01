package com.smartbee.spring.tutorial.repositories.users;

import com.smartbee.spring.tutorial.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by galihlasahido on 10/5/14.
 */
@Service
public class UserImpl implements UserService {
    @Autowired(required = true)
    private UserRepository repository;

    @Transactional
    @Override
    public void save(Users dto) {
        repository.saveAndFlush(dto);
    }

    @Transactional
    @Override
    public void delete(Users dto) {
        if (dto == null || dto.getId() == null) {
            return;
        }

        repository.delete(dto);
    }

    @Override
    public Users findUsersById(Long id) {
        if (id==null) {
            return null;
        }

        return repository.findOne(id);
    }

    @Override
    public Users findUsersByUsername(String username) {
        if (username==null) {
            return null;
        }

        return repository.findUsersByUsernameIgnoreCase(username);
    }

    @Override
    public Users findUsersByUsernameAndNotThisId(String username, Long id) {
        if (username==null) {
            return null;
        }

        if (id==null) {
            return null;
        }

        return repository.findUsersByUsernameIgnoreCaseAndIdNot(username, id);
    }

    @Override
    public Page<Users> findAllUsers(Pageable pageable) {
        return repository.findAllWithQuery(pageable);
    }

    @Override
    public Page<Users> searchUsers(Pageable pageable, String mode, String value) {
        Page<Users> page = null;
        if(mode==null) {
            return null;
        }

        if(value==null) {
            return null;
        }

        switch (mode) {
            case"username":
                default:
                page = repository.getUsersByUsernameIgnoreCaseContaining(pageable,value);
                break;
            case"fullname":
                page = repository.getUsersByFullnameIgnoreCaseContaining(pageable,value);
                break;
        }

        return page;

    }

    @Transactional
    public void updatePassword(String password, Long id) {
        repository.updatePassword(password,id);
    }

    @Transactional
    public void updateProfile(Users dto) {
        repository.updateProfile(dto.getUsername(), dto.getFullname(), dto.getRoles(), dto.getActive(), dto.getId());
    }

    @Override
    public List<Map<String, Object>> getSomething() {
        return repository.getSomething();
    }
}