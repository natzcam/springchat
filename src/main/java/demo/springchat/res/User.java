package demo.springchat.res;

/**
 * Created by nathaniel.a.camomot on 1/13/2016.
 */
public class User {

    private String username;
    private String pic;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}
