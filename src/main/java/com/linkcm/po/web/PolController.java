package com.linkcm.po.web;

import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.PageOfficeLink;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Paths;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2017/2/3 20:36
 * @description :
 */
@Controller
@RequestMapping("/pol")
public class PolController extends BaseController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        //String link = PageOfficeLink.openWindow(request, request.getContextPath() + "/pol/open", "width:100%;height:100%;");
        // 第二个参数代表打开 PageOffice 的地址，不可以携带 contextPath，因为默认就会添加 contextPath，如果重复添加将会造成无法打开 PageOffice
        // 会自动添加 scheme://serverHost:serverPort/contextPath
        String link = PageOfficeLink.openWindow(request, "/pol/open", "width:100%;height:100%;");

        model.addAttribute("link", link);
        return "index";
    }

    @RequestMapping(value = "/open", method = RequestMethod.GET)
    public String open(HttpServletRequest request) {

        PageOfficeCtrl excelCtrl = this.create(request);


        String documentPath = Paths.get(webPath("/office/数据源.xlsx")).toUri().toString();
        excelCtrl.webOpen(documentPath, OpenModeType.xlsNormalEdit, "佚名");

        excelCtrl.setTagId("excelCtrl");
        return "pol";
    }

}
