package com.myFood.order.domain.model.dto;

import java.io.Serializable;

/**
 *
 * @author reinaldo.locatelli
 */
public class UserPasswordDTO implements Serializable {

    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
