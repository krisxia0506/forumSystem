package forum.beans;

/**
 * Created on 2022-10-27 23:02
 *
 * @author Xia Jiayi
 */
public class Theme implements java.io.Serializable {
    private Integer id;
    private String themeTitle;
    private String themeIntroduction;

    public Theme() {
    }

    public Theme(Integer id, String themeTitle, String themeIntroduction) {
        this.id = id;
        this.themeTitle = themeTitle;
        this.themeIntroduction = themeIntroduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public String getThemeIntroduction() {
        return themeIntroduction;
    }

    public void setThemeIntroduction(String themeIntroduction) {
        this.themeIntroduction = themeIntroduction;
    }
}
