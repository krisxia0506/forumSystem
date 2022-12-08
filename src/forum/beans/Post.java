package forum.beans;

/**
 * Created on 2022-10-27 10:26
 *
 * @author Xia Jiayi
 */
public class Post implements java.io.Serializable {
    private Integer id;
    private String title;
    private String content;
    private String author;
    private String postTime;
    private String keyword;
    private String note;
    private String theme;
    private String hits;

    public Post() {
    }

    public Post(String title, String content, String author, String postTime, String keyword, String note) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.postTime = postTime;
        this.keyword = keyword;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }
}
