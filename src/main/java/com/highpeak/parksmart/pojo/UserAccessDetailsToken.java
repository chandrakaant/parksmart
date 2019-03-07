package com.highpeak.parksmart.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

public class UserAccessDetailsToken extends PreAuthenticatedAuthenticationToken {

    /**
     *
     */
    private static final long serialVersionUID = 8956754276168428489L;
    private Integer userId;
    private String email;
    private String role;

    public UserAccessDetailsToken(Object aPrincipal, Object aCredentials,
                                  Collection<? extends GrantedAuthority> anAuthorities )
    {
        super(aPrincipal, aCredentials, anAuthorities);
    }

    public void setToken( String token )
    {
        setDetails(token);
    }

    public String getToken()
    {
        return (String) getDetails();
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }
}
