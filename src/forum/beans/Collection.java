package forum.beans;

/**
 * Created on 2022-12-07 12:47
 *
 * @author Xia Jiayi
 */
public class Collection implements java.io.Serializable {
    private Integer id;
    private String userId;
    private String postId;
    private String date;
    private String postTitle;

    public Collection() {
    }

    public Collection(Integer id, String userId, String postId, String date, String postTitle) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.date = date;
        this.postTitle = postTitle;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return postTitle;
    }

    public void setTitle(String title) {
        this.postTitle = title;
    }
}
