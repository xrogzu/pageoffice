package com.linkcm.po.web;

import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/3 16:49
 * @description :
 */
public class BaseController {

    protected PageOfficeCtrl create(HttpServletRequest request) {
        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        poCtrl.setServerPage(request.getContextPath() + "/poServer");
        poCtrl.setSaveFilePage(request.getContextPath() + "/save/file");
        return poCtrl;
    }

    protected String webPath() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
    }

    protected String webPath(String dir) {
        if (!dir.startsWith("/")) {
            dir = '/' + dir;
        }
        return ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(dir);
    }

}
