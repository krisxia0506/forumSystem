package forum.beans;

/**
 * Created on 2022-11-21 10:34
 *
 * @author Xia Jiayi
 */
public class Reply implements java.io.Serializable {
    private Integer id;
    private String content;
    private String author;
    private String level;
    private String replyTime;
    private Integer postId;

    private Post post;

    public Reply() {
    }

    public Reply(Integer id, String content, String author, String level, String replyTime, Integer postId, Post post) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.level = level;
        this.replyTime = replyTime;
        this.postId = postId;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
