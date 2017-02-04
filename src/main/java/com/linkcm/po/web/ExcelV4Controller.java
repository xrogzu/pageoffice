package com.linkcm.po.web;

import com.linkcm.po.util.JodaUtil;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2017/2/4 8:22
 * @description :
 */
@Controller
@RequestMapping("/excel/v4")
public class ExcelV4Controller extends BaseController {

    @RequestMapping(value = "/open/{username:\\w+}", method = RequestMethod.GET)
    public String readOnly(HttpServletRequest request, Model model,
                           @PathVariable("username") String username) {
        PageOfficeCtrl excelCtrl = this.create(request);
        excelCtrl.setOfficeToolbars(false);
        excelCtrl.setMenubar(false);

        excelCtrl.addCustomToolButton("编辑", "edit", 3);

        model.addAttribute("docName", "数据源.xlsx");
        model.addAttribute("docPath", "/office/数据源.xlsx");
        model.addAttribute("username", username); // 当前浏览的用户名称

        String documentPath = Paths.get(webPath("/office/数据源.xlsx")).toUri().toString();
        excelCtrl.webOpen(documentPath, OpenModeType.xlsReadOnly, username);

        excelCtrl.setTagId("excelCtrl");

        return "openExcelV4";
    }

    private final Map<String, String> fileEditing = new ConcurrentHashMap<>();
    private static final Lock LOCK = new ReentrantLock();

    private String decode(String param) {
        try {
            return URLDecoder.decode(URLDecoder.decode(param, "UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException ignore) {
            throw new RuntimeException(); // 不可能抛出的异常，因为 Java 内部必然实现了 UTF-8 编码
        }
    }

    @RequestMapping(value = "/prepare/edit/{username:\\w+}", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public String prepare(HttpServletRequest request, HttpSession session,
                          @RequestParam String docPath, Model model,
                          @PathVariable("username") String username) {
        LOCK.lock();
        try {
            String uriDocPath = docPath;
            docPath = decode(docPath);
            String editing = fileEditing.get(docPath);
            if (editing != null && !username.equals(editing)) {
                return "{\"error\":\"当前文档正被" + username + "编辑！请稍后重试\"}";
            }
            fileEditing.put(docPath, username);

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String accessToken = uuid + '_' + JodaUtil.timestamp() + '_' + username;
            session.setAttribute("accessToken", accessToken);
            model.addAttribute("accessToken", accessToken);
            return "{\"success\":\"可以访问\", \"url\": \"" + request.getContextPath() + "/edit/" + username + "?docPath=" + uriDocPath + "\"}";
        } finally {
            LOCK.unlock();
        }
    }

    @RequestMapping(value = "/edit/{username:\\w+}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, HttpSession session,
                       @RequestParam String docPath, String accessToken,
                       @PathVariable("username") String username) {
        String sessionAccessToken = session.getAttribute("accessToken").toString();
        if (!StringUtils.equals(sessionAccessToken, accessToken)) {
            throw new RuntimeException();
        }

        return "editExcelV4";

    }


}
