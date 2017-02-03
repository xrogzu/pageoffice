package com.linkcm.po.web;

import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/3 17:23
 * @description :
 */
@Controller
@RequestMapping("/excel/v2")
public class ExcelV2Controller extends BaseController {

    private Map<String, Boolean> fileIsEditing = new ConcurrentHashMap<>();

    @RequestMapping(value = "/open/{username:\\w+}", method = RequestMethod.GET)
    public String open(HttpServletRequest request, Model model,
                       @PathVariable("username") String username) {
        PageOfficeCtrl excelCtrl = this.create(request);

        excelCtrl.addCustomToolButton("全屏显示", "fullScreen", 4);
        excelCtrl.addCustomToolButton("关闭全屏", "cancelFullScreen", 4);
        excelCtrl.addCustomToolButton("保存文件", "saveFile", 11);
        excelCtrl.addCustomToolButton("启动编辑", "edit", 11);

        excelCtrl.setMenubar(false);

        model.addAttribute("docName", "数据源.xlsx");
        model.addAttribute("docPath", "/office/数据源.xlsx");
        model.addAttribute("username", username); // 当前浏览的用户名称

        String documentPath = Paths.get(webPath("/office/数据源.xlsx")).toUri().toString();
        excelCtrl.webOpen(documentPath, OpenModeType.xlsReadOnly, username);

        excelCtrl.setTagId("excelCtrl");

        return "openExcelV2";
    }

    private String decode(String param) {
        try {
            return URLDecoder.decode(URLDecoder.decode(param, "UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException ignore) {
            throw new RuntimeException(); // 不可能抛出的异常，因为 Java 内部必然实现了 UTF-8 编码
        }
    }

    private Lock lock = new ReentrantLock();

    @RequestMapping(value = "/edit/{username:\\w+}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, Model model,
                       @PathVariable("username") String username,
                       @RequestParam String docPath,
                       HttpServletResponse response,
                       PrintWriter out) {

        lock.lock();

        try {
            docPath = decode(docPath);
            Boolean isEditing = fileIsEditing.get(docPath);
            if (isEditing != null && isEditing) { // 正在编辑
                response.setContentType("application/json; charset=UTF-8");
                out.write("{\"error\":\"file is editing.\"}");
                out.close();
                return "";
            }

            PageOfficeCtrl excelCtrl = this.create(request);

            excelCtrl.addCustomToolButton("全屏显示", "fullScreen", 4);
            excelCtrl.addCustomToolButton("关闭全屏", "cancelFullScreen", 4);
            excelCtrl.addCustomToolButton("保存文件", "saveFile", 11);

            excelCtrl.setMenubar(false);
            String docName = docPath.substring(docPath.lastIndexOf('/') + 1);
            model.addAttribute("docName", docName);
            model.addAttribute("docPath", docPath);
            model.addAttribute("username", username); // 当前编辑的用户名称

            String documentPath = Paths.get(webPath(docPath)).toUri().toString();
            excelCtrl.webOpen(documentPath, OpenModeType.xlsNormalEdit, username);

            excelCtrl.setTagId("excelCtrl");
            return "openExcelV2";

        } finally {
            lock.unlock();
        }

    }

}
