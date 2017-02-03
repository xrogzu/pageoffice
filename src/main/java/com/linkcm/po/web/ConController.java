package com.linkcm.po.web;

import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.PageOfficeLink;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Paths;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2017/2/3 20:05
 * @description :
 */
@Controller
@RequestMapping("/con")
public class ConController extends BaseController {

    @RequestMapping(value = "/open/{username:\\w+}", method = RequestMethod.GET)
    public String open(HttpServletRequest request, HttpServletResponse response,
                       Model model, @PathVariable("username") String username) {
        PageOfficeCtrl excelCtrl = this.create(request);
        excelCtrl.setMenubar(false);
        excelCtrl.setOfficeToolbars(false);

        excelCtrl.addCustomToolButton("编辑", "edit", 3);

        model.addAttribute("docName", "数据源.xlsx");
        model.addAttribute("docPath", "/office/数据源.xlsx");
        model.addAttribute("username", username); // 当前浏览的用户名称

        String documentPath = Paths.get(webPath("/office/数据源.xlsx")).toUri().toString();
        excelCtrl.webOpen(documentPath, OpenModeType.xlsReadOnly, username);

        excelCtrl.setTagId("excelCtrl");

        return "openExcelV3";
    }

    @RequestMapping(value = "/prepare/edit/{username:\\w+}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model,
                       @PathVariable("username") String username) {
        String link = PageOfficeLink.openWindow(request, "/con/edit/" + username + "?docPath=12b", "width:100%;height:100%;");
        //String link = PageOfficeLink.openWindow(request, "/con/edit/" + username, "width:100%;height:700px;");
        model.addAttribute("link", link);
        //model.addAttribute("username", username);
        return "prepareEditExcel";
    }

    @RequestMapping(value = "/edit/{username:\\w+}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, HttpServletResponse response,
                       Model model, @RequestParam String docPath,
                       @PathVariable("username") String username) {
        PageOfficeCtrl excelCtrl = this.create(request);
        excelCtrl.setMenubar(false);

        excelCtrl.addCustomToolButton("保存", "saveFile", 1);

        model.addAttribute("docName", "数据源.xlsx");
        model.addAttribute("docPath", "/office/数据源.xlsx");
        model.addAttribute("username", username); // 当前浏览的用户名称

        excelCtrl.setTimeSlice(5);

        String documentPath = Paths.get(webPath("/office/数据源.xlsx")).toUri().toString();
        excelCtrl.webOpen(documentPath, OpenModeType.xlsNormalEdit, username);

        excelCtrl.setTagId("excelCtrl");
        return "editExcelV3";
    }

}
