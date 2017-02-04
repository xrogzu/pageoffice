package com.linkcm.po.util;

import java.util.UUID;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 9:42
 * @description :
 */
public abstract class Global {

    public static final String SESSION_USER_KEY = "loginUser";

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getSessionUserKey() {
        return SESSION_USER_KEY;
    }

}
