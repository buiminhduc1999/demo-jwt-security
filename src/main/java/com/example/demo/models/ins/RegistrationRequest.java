package com.example.demo.models.ins;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationRequest {

    @NotNull
    @Size(min=3, max = 60)
    private String userName;

    @NotNull
    @Size(min = 6, max = 40)
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
