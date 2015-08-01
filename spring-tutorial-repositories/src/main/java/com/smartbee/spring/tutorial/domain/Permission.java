package com.smartbee.spring.tutorial.domain;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * Created by TheArtOfMovement on 6/29/15.
 */

@Entity
@Table(name = "permission")
@SelectBeforeUpdate(value = false)
public class Permission {

    @Id
    @Column(name = "id_permission",unique = true)
    private String id;
    private String label;
    private String value;

    @OneToMany(mappedBy="permission", fetch = FetchType.LAZY)
    private List<RolesPermission> rolesPermissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<RolesPermission> getRolesPermissions() {
        return rolesPermissions;
    }

    public void setRolesPermissions(List<RolesPermission> rolesPermissions) {
        this.rolesPermissions = rolesPermissions;
    }
}
