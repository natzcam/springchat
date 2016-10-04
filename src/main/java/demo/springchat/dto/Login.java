package demo.springchat.dto;

import org.hibernate.validator.constraints.NotEmpty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author user
 */
public class Login {
    public Login(){}
    
    public Login(String name, String password) {
        this.username = name;
        this.password = password;
    }

    @NotEmpty
    public String username;
    @NotEmpty
    public String password;
    public String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void clearPass() {
        this.password = null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
