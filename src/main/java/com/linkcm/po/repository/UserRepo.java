package com.linkcm.po.repository;

import com.linkcm.po.domain.User;

import java.util.List;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 9:29
 * @description :
 */
public interface UserRepo {

    int insert(User user);

    int delete(String id);

    int update(User user);

    User selectOne(String id);

    User selectByUsername(String username);

    User login(String username, String password);

    List<User> selectAll();

}
