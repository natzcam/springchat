/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.springchat.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

/**
 * @author nathaniel camomot
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Pattern(regexp = "^[a-z]+[a-z\\.]{3,20}$")
    @Column(name = "username", unique = true, nullable = false, length = 255)
    private String username;

    @Pattern(regexp= "^(?=\\S+$).{4,}$")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Size(min = 1)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    @URL
    @Column(name = "pic", nullable = false, length = 255)
    private String pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = new HashSet<>(roles);
    }

    public void setRoles(String[] roleArray) {
        roles.clear();
        Collections.addAll(roles, roleArray);
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
