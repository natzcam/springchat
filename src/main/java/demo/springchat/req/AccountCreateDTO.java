/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.springchat.req;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.ScriptAssert;

/**
 * @author nathaniel camomot
 */
@ScriptAssert(lang = "javascript", script = "_this.password === _this.passwordRepeat")
public class AccountCreateDTO {

    @Pattern(regexp = "^[a-z]+[a-z\\.]{3,20}$")
    private String username;

    @Pattern(regexp= "^(?=\\S+$).{4,}$")
    private String password;

    @Pattern(regexp= "^(?=\\S+$).{4,}$")
    private String passwordRepeat;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
}
