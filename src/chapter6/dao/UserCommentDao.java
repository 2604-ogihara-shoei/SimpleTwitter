package chapter6.dao;

import static chapter6.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import chapter6.beans.UserComment;
import chapter6.exception.SQLRuntimeException;
import chapter6.logging.InitApplication;

public class UserCommentDao {
	/**
	    * ロガーインスタンスの生成
	    */
	    Logger log = Logger.getLogger("twitter");

	    /**
	    * デフォルトコンストラクタ
	    * アプリケーションの初期化を実施する。
	    */
    public UserCommentDao() {
        InitApplication application = InitApplication.getInstance();
        application.init();
    }


	public List<UserComment> select(Connection connection, int num) {

		log.info(new Object(){}.getClass().getEnclosingClass().getName() +
		" : " + new Object(){}.getClass().getEnclosingMethod().getName());

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("    comments.id as id, ");
			sql.append("    comments.text as text, ");
			sql.append("    comments.user_id as userId, ");
			sql.append("    comments.message_id as messageId, ");
			sql.append("    users.account as account, ");
			sql.append("    users.name as name, ");
			sql.append("    comments.created_date as created_date, ");
			sql.append("    comments.updated_date as updated_date ");
			sql.append("FROM comments ");
			sql.append("INNER JOIN users ");
			sql.append("ON comments.user_id = users.id ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();

			List<UserComment> comments = toUserComments(rs);

		    return comments;
        } catch (SQLException e) {
        	log.log(Level.SEVERE, new Object(){}.getClass().getEnclosingClass().getName() + " : " + e.toString(), e);
        	throw new SQLRuntimeException(e);
        } finally {
            close(ps);
        }
    }


    private List<UserComment> toUserComments(ResultSet rs) throws SQLException {

    	log.info(new Object(){}.getClass().getEnclosingClass().getName() +
		" : " + new Object(){}.getClass().getEnclosingMethod().getName());

		List<UserComment> userComments = new ArrayList<UserComment>();
		try {
			while (rs.next()) {
				UserComment userComment = new UserComment();
				userComment.setId(rs.getInt("id"));
				userComment.setText(rs.getString("text"));
				userComment.setUserId(rs.getInt("userId"));
				userComment.setMessageId(rs.getInt("messageId"));
				userComment.setCreatedDate(rs.getTimestamp("created_date"));
				userComment.setUpdatedDate(rs.getTimestamp("updated_date"));
				userComment.setName(rs.getString("name"));
				userComment.setAccount(rs.getString("account"));

				userComments.add(userComment);
			}
			return userComments;
		} finally {
			close(rs);
		}
    }
}
