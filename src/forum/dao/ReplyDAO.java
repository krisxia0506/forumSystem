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

    public ArrayList<Reply> getReplyByPostId(String postId);

    public ArrayList<Reply> getReplyByUserId(String userId);

    public ArrayList<Reply> getAll();

    public boolean deleteReplyById(String id);

    public ArrayList<Reply> getHotReply();
}
