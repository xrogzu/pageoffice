package com.linkcm.po.repository;

import com.linkcm.po.domain.Doc;
import com.linkcm.po.domain.User;
import org.joda.time.DateTime;

import java.util.List;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 10:39
 * @description :
 */
public interface DocRepo {

    int insert(Doc doc);

    int delete(String id);

    int update(Doc doc);

    Doc selectOne(String id);

    Doc selectByDocEnName(String docEnName);

    Doc selectByDocPath(String docPath);

    List<Doc> selectAll();

    int historyLog(User modifier, DateTime modifiedTime, String bakPath);

}
