package forum.beans;

/**
 * Created on 2022-10-19 14:58
 *
 * @author Xia Jiayi
 */
public class User implements java.io.Serializable {
    private Integer id;
    private Integer roleId;
    private String username;
    private String password;
    private String gender;
    private String resume;

    public User() {
    }

    public User(Integer id, Integer roleId, String username, String password, String gender, String resume) {
        this.id = id;
        this.roleId = roleId;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.resume = resume;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
