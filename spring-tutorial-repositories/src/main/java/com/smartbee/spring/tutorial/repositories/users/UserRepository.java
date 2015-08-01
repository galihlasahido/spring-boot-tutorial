package com.smartbee.spring.tutorial.repositories.users;

import com.smartbee.spring.tutorial.domain.Roles;
import com.smartbee.spring.tutorial.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by galihlasahido on 10/4/14.
 */
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u left join u.roles")
    public Page<Users> findAllWithQuery(Pageable pageable);
    public Users findUsersByUsernameIgnoreCase(String username);
    public Page<Users> getUsersByUsernameIgnoreCaseContaining(Pageable pageable, String username);
    public Page<Users> getUsersByFullnameIgnoreCaseContaining(Pageable pageable, String fullname);
    public Users findUsersByUsernameIgnoreCaseAndIdNot(String username, Long id);

    @Modifying
    @Query(value = "UPDATE Users u SET u.password = ?1 WHERE u.id = ?2 ")
    public void updatePassword(String password, Long id);

    @Modifying
    @Query(value = "UPDATE Users u SET u.username = ?1, u.fullname = ?2, u.roles = ?3, u.active = ?4 WHERE u.id = ?5")
    public void updateProfile(String username, String fullname, Roles roles, Boolean active, Long id);

    @Query(value = "SELECT u.id_user, u.username, u.password FROM Users u", nativeQuery = true)
    public List<Map<String, Object>> getSomething();

}