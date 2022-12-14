package forum.beans;

/**
 * Created on 2022-10-19 14:58
 *
 * @author Xia Jiayi
 */
public class User implements java.io.Serializable {
    private Integer id;
    private Integer role;
    private String username;
    private String password;
    private String gender;
    private String nickname;
    private String resume;
    private String level;

    public User() {
    }

    public User(Integer id, Integer role, String username, String password, String gender, String resume, String level) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.resume = resume;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
