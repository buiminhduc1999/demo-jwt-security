package com.example.demo.models.ins;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationRequest {

    @NotNull
    private int idRole;

    @NotNull
    private int idTeam;

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

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

}
