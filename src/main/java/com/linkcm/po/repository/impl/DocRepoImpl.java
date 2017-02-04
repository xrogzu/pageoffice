package com.linkcm.po.repository.impl;

import com.linkcm.po.domain.Doc;
import com.linkcm.po.domain.User;
import com.linkcm.po.repository.DocRepo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 10:40
 * @description :
 */
@Repository("docRepo")
public class DocRepoImpl implements DocRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final String historyLogSql = "INSERT INTO doc_history(doc_id, modifier, modifier_name, modified_time, bak_path) VALUES (?,?,?,?,?)";

    @Override
    public int insert(Doc doc) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int update(Doc doc) {
        return 0;
    }

    @Override
    public Doc selectOne(String id) {
        return null;
    }

    @Override
    public Doc selectByDocEnName(String docEnName) {
        return null;
    }

    @Override
    public Doc selectByDocPath(String docPath) {
        return null;
    }

    @Override
    public List<Doc> selectAll() {
        return null;
    }

    @Override
    public int historyLog(User modifier, DateTime modifiedTime, String bakPath) {
        return jdbcTemplate.update(historyLogSql,
                modifier.getDoc().getId(),
                modifier.getId(),
                modifier.getUsername(),
                modifiedTime.toDate(),
                bakPath);
    }

}
