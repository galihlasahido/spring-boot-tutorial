package com.smartbee.spring.tutorial.repositories.users;

import com.smartbee.spring.tutorial.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by galihlasahido on 10/5/14.
 */
@Component
public interface UserService {
    public void save(Users dto);
    public void delete(Users dto);
    public Users findUsersById(Long id);
    public Users findUsersByUsername(String username);
    public Users findUsersByUsernameAndNotThisId(String username, Long id);
    Page<Users> findAllUsers(Pageable pageable);
    Page<Users> searchUsers(Pageable pageable, String mode, String value);
    public void updatePassword(String password, Long id);
    public void updateProfile(Users dto);
    public List<Map<String, Object>> getSomething();
}
