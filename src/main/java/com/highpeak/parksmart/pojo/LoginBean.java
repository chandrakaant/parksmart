package com.highpeak.parksmart.pojo;

import javax.validation.constraints.NotNull;

public class LoginBean {

    @NotNull
    private String email;
    @NotNull
    private String password;

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail( String email )
    {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword( String password )
    {
        this.password = password;
    }
}
