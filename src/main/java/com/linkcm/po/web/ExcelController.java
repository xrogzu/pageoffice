package com.linkcm.po.web;

import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Paths;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/3 16:38
 * @description :
 */
@Controller
@RequestMapping("/excel")
public class ExcelController extends BaseController {

    @RequestMapping(value = "/open", method = RequestMethod.GET)
    public String openExcel(HttpServletRequest request, Model model) {
        PageOfficeCtrl excelCtrl = this.create(request);

        excelCtrl.addCustomToolButton("全屏显示", "fullScreen", 4);
        excelCtrl.addCustomToolButton("关闭全屏", "cancelFullScreen", 4);
        excelCtrl.addCustomToolButton("保存文件", "saveFile", 11);

        model.addAttribute("docName", "数据源.xls");
        model.addAttribute("docPath", "/office/数据源.xls");

        String documentPath = Paths.get(webPath("/office/数据源.xls")).toUri().toString();
        excelCtrl.webOpen(documentPath, OpenModeType.xlsNormalEdit, "佚名");

        excelCtrl.setTagId("excelCtrl");

        return "openExcel";
    }

    @RequestMapping(value = "/concurrency/{username:\\w+}", method = RequestMethod.GET)
    public String concurrencyOpenExcel(HttpServletRequest request, Model model,
                                       @PathVariable("username") String username) {
        PageOfficeCtrl excelCtrl = this.create(request);

        excelCtrl.setMenubar(false);

        excelCtrl.addCustomToolButton("全屏显示", "fullScreen", 4);
        excelCtrl.addCustomToolButton("关闭全屏", "cancelFullScreen", 4);
        excelCtrl.addCustomToolButton("保存文件", "saveFile", 11);

        model.addAttribute("docName", "数据源.xlsx");
        model.addAttribute("docPath", "/office/数据源.xlsx");
        model.addAttribute("username", username); // 当前编辑的用户名称

        // 设置文档并发访问——写的时间片[单位：分钟]
        // 不论是否有其他用户申请进行编辑操作，每个用户都只能对该文档编辑 X 分钟
        excelCtrl.setTimeSlice(1);

        String documentPath = Paths.get(webPath("/office/数据源.xlsx")).toUri().toString();
        excelCtrl.webOpen(documentPath, OpenModeType.xlsNormalEdit, username);

        excelCtrl.setTagId("excelCtrl");

        return "openExcel";
    }

}
