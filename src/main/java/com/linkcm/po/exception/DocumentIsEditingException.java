package com.linkcm.po.exception;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 9:05
 * @description :
 */
public class DocumentIsEditingException extends RuntimeException {

    public DocumentIsEditingException() {
    }

    public DocumentIsEditingException(String message) {
        super(message);
    }

    public DocumentIsEditingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentIsEditingException(Throwable cause) {
        super(cause);
    }

}
