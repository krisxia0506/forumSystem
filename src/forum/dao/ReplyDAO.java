package forum.dao;

import forum.beans.Reply;

import java.util.ArrayList;

/**
 * Created on 2022-11-21 10:37
 *
 * @author Xia Jiayi
 */
public interface ReplyDAO {
    public boolean insertReply(Reply reply);

    public ArrayList<Reply> getByPostId(String postId);

    public ArrayList<Reply> getByUserId(String userId);

    public ArrayList<Reply> getAll();

    public boolean deleteById(String id);

    public ArrayList<Reply> getHotReply();
}
