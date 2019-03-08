package com.highpeak.parksmart.datastore.model;

import javax.persistence.*;

@Entity
@Table(name = "ps_user_role")
public class UserRoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ur_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ur_user_id", referencedColumnName = "u_id")
    private UserModel userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ur_role_id", referencedColumnName = "r_id")
    private RoleEntity roleId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserModel getUserId() {
        return userId;
    }

    public void setUserId(UserModel userId) {
        this.userId = userId;
    }

    public RoleEntity getRoleId() {
        return roleId;
    }

    public void setRoleId(RoleEntity roleId) {
        this.roleId = roleId;
    }
}


