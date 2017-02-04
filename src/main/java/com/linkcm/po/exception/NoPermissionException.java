package com.linkcm.po.exception;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 9:07
 * @description :
 */
public class NoPermissionException extends RuntimeException {

    public NoPermissionException() {
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }

}
