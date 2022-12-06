package forum.dao;

import forum.beans.Reply;

import java.util.ArrayList;

/**
 * Created on 2022-11-21 10:37
 *
 * @author Xia Jiayi
 */
public interface ReplyDAO {
    public boolean insert(Reply reply);

    public ArrayList<Reply> getByNewsId(String postId);

    public ArrayList<Reply> getByUsername(String username);

    public ArrayList<Reply> getAll();

    public boolean deleteById(String id);
    public boolean deleteByNewsId(String id);

    public ArrayList<Reply> getTop5();
}
