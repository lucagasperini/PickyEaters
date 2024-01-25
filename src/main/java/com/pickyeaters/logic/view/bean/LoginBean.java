package com.pickyeaters.logic.view.bean;

public class LoginBean {
    private String email;
    private String password;

    public LoginBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
