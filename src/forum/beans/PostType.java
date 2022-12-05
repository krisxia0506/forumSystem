package forum.beans;

/**
 * Created on 2022-10-27 23:02
 *
 * @author Xia Jiayi
 */
public class PostType implements java.io.Serializable {
    private Integer postTypeId;
    private String postType;

    public PostType() {
    }

    public PostType(Integer postTypeId, String postType) {
        this.postTypeId = postTypeId;
        this.postType = postType;
    }

    public Integer getPostTypeId() {
        return postTypeId;
    }

    public void setPostTypeId(Integer postTypeId) {
        this.postTypeId = postTypeId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }
}
