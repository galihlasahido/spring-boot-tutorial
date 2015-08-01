package com.smartbee.spring.tutorial.domain;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

/**
 * Created by TheArtOfMovement on 6/29/15.
 */

@Entity
@Table(name = "users")
@SelectBeforeUpdate(value = false)
public class Users {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", unique = true)
    private Long id;
    private String username;
    private String password;
    private Boolean active;
    private String fullname;
    private String createdby;

    @ManyToOne
    @JoinColumn(name="id_roles")
    private Roles roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

}
