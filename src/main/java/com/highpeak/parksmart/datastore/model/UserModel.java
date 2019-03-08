package com.highpeak.parksmart.datastore.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ps_user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int id;

    @Column(name = "u_name")
    private String userName;

    @Column(name = "u_email")
    private String email;

    @Column(name = "u_password")
    private String password;

    @Column(name = "u_created_date")
    private Timestamp createdDate;

    @Column(name = "u_modified_date")
    private Timestamp modifiedDate;

    @Column(name = "u_token_expiry_date")
    private Timestamp tokenExpiryDate;

    @Column(name = "u_refresh_token")
    private String refreshToken;

    @Column(name = "u_is_active")
    private boolean isActive;

    public boolean isActive()
    {
        return isActive;
    }

    public void setActive(boolean active)
    {
        isActive = active;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Timestamp getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate)
    {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate()
    {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    public Timestamp getTokenExpiryDate()
    {
        return tokenExpiryDate;
    }

    public void setTokenExpiryDate(Timestamp tokenExpiryDate)
    {
        this.tokenExpiryDate = tokenExpiryDate;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }
}
