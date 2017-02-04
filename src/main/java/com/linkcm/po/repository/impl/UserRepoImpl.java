package com.linkcm.po.repository.impl;

import com.linkcm.po.domain.Doc;
import com.linkcm.po.domain.User;
import com.linkcm.po.repository.UserRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 9:30
 * @description :
 */
@Repository("userRepo")
public class UserRepoImpl implements UserRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String insertSql = "INSERT INTO users(id, username, password) VALUES (?,?,?);";
    private static final String deleteSql = "DELETE FROM users WHERE id = ?;";
    private static final String updateSql = "UPDATE users SET username = ?, users.password = ? WHERE id = ?;";
    private static final String selectOneSql = "SELECT * FROM users LEFT JOIN user_doc ON(users.id = user_doc.user_id) " +
            "LEFT JOIN documents ON(user_doc.doc_id = documents.id) WHERE users.id = ?;";
    private static final String selectByUsernameSql = "SELECT * FROM users LEFT JOIN user_doc ON(users.id = user_doc.user_id) " +
            "LEFT JOIN documents ON(user_doc.doc_id = documents.id) WHERE users.username = ?;";
    private static final String loginSql = "SELECT * FROM users LEFT JOIN user_doc ON(users.id = user_doc.user_id) " +
            "LEFT JOIN documents ON(user_doc.doc_id = documents.id) WHERE username = ? AND password = ?;";
    private static final String selectAllSql = "SELECT * FROM users LEFT JOIN user_doc ON(users.id = user_doc.user_id) " +
            "LEFT JOIN documents ON(user_doc.doc_id = documents.id);";

    @Override
    public int insert(User user) {
        return jdbcTemplate.update(insertSql, user.getId(), user.getUsername(), user.getPassword());
    }

    @Override
    public int delete(String id) {
        return jdbcTemplate.update(deleteSql, id);
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(updateSql, user.getUsername(), user.getPassword(), user.getId());
    }

    @Override
    public User selectOne(String id) {
        try {
            return jdbcTemplate.queryForObject(selectOneSql, new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User selectByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject(selectByUsernameSql, new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User login(String username, String password) {
        try {
            return jdbcTemplate.queryForObject(loginSql, new UserRowMapper(), username, password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> selectAll() {
        return jdbcTemplate.query(selectAllSql, new UserRowMapper());
    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("user_id"));
            user.setUsername(rs.getString("username"));
            user.setNickname(rs.getString("nickname"));
            user.setPassword(rs.getString("password"));
            String docId = rs.getString("doc_id");
            if (StringUtils.isEmpty(docId)) {
                return user;
            }
            Doc doc = new Doc();
            doc.setId(docId);
            doc.setDocName(rs.getString("doc_name"));
            doc.setDocEnName(rs.getString("doc_name_en"));
            doc.setDocPath(rs.getString("doc_path"));
            doc.setEditable(rs.getBoolean("editable"));
            user.setDoc(doc);
            return user;
        }

    }

}
