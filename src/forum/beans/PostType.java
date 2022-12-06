package forum.beans;

/**
 * Created on 2022-10-27 23:02
 *
 * @author Xia Jiayi
 */
public class PostType implements java.io.Serializable {
    private Integer id;
    private String postType;
    private String typeIntroduction;

    public PostType() {
    }

    public PostType(Integer id, String postType, String typeIntroduction) {
        this.id = id;
        this.postType = postType;
        this.typeIntroduction = typeIntroduction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getTypeIntroduction() {
        return typeIntroduction;
    }

    public void setTypeIntroduction(String typeIntroduction) {
        this.typeIntroduction = typeIntroduction;
    }
}
