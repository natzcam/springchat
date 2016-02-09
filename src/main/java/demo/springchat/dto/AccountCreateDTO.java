/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.springchat.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

/**
 * @author nathaniel camomot
 */
@ScriptAssert(lang = "javascript", script = "_this.password === _this.passwordRepeat")
public class AccountCreateDTO {

    @Pattern(regexp = "^[a-z]+[a-z0-9_-]{3,20}$")
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
