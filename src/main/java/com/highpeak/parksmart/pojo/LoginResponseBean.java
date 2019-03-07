package com.highpeak.parksmart.pojo;

public class LoginResponseBean {

    private Integer userId;
    private String emailId;
    private String roleName;

    public LoginResponseBean(Integer userId, String emailId, String roleName)
    {
        this.userId = userId;
        this.emailId = emailId;
        this.roleName = roleName;
    }

    public Integer getUserId()
    {
        return userId;
    }

    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId(String emailId)
    {
        this.emailId = emailId;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }
}
