package com.linkcm.po.web;

import com.linkcm.po.util.FileUtils;
import com.linkcm.po.util.JodaUtil;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/3 14:41
 * @description :
 */
@Controller
public class PoController extends BaseController {

    @RequestMapping(value = "/openWord", method = RequestMethod.GET)
    public String readOnly(HttpServletRequest request, Model model) {
        PageOfficeCtrl ctrl = this.create(request);
        ctrl.setTitlebar(false); // 隐藏标题栏
        ctrl.setMenubar(false); // 隐藏菜单栏
        //ctrl.setAllowCopy(false); // 禁用复制/粘贴，实际上是禁用了 Ctrl C + Ctrl V，此时其他所有窗口都无法使用按功能键
        //ctrl.setCustomToolbar(false); // 隐藏自定义的工具栏
        //ctrl.setOfficeToolbars(false); // 隐藏 Office 工具栏
        // 自定义工具栏
        ctrl.addCustomToolButton("全屏显示", "fullScreen", 4);
        ctrl.addCustomToolButton("关闭全屏", "cancelFullScreen", 4);
        ctrl.addCustomToolButton("保存文件", "saveFile", 1);

        // 页面打开之后执行 JS 函数
        ctrl.setJsFunction_AfterDocumentOpened("afterOpened");

        // 把文档名称也发送到页面，之后用隐藏域存放，保存的时候顺便将该隐藏域发送到 fileSaver 中，
        // 这样可以调用 FileSaver#getFormField 得到隐藏域的值，之后用于文档保存，否则 FileSaver#getFileName 中文乱码
        model.addAttribute("docName", "测试.docx");
        model.addAttribute("docPath", "/office/测试.docx");

        String documentPath = Paths.get(webPath("/office/测试.docx")).toUri().toString();
        ctrl.webOpen(documentPath, OpenModeType.docNormalEdit, "佚名");

        ctrl.setTagId("wordDoc");

        return "openWord";
    }

    private final String dir = "F:/usr/pageoffice/";

    @RequestMapping(value = "/save/file", method = RequestMethod.POST)
    public void saveFile(HttpServletRequest request, HttpServletResponse response,
                         ServletOutputStream out) throws IOException {
        FileSaver fileSaver = new FileSaver(request, response);

        //String filename = fileSaver.getFileName(); // XXX.docx
        String filename = fileSaver.getFormField("docName"); // 我们自己传送过去的文档名称
        String extName = fileSaver.getFileExtName(); // .docx
        String name = filename.substring(0, filename.lastIndexOf(extName)); // XXX

        // 历史文档保存
        String docPath = fileSaver.getFormField("docPath");
        Path path = Paths.get(webPath(docPath));
        Path historyPath = Paths.get(dir, name + '_' + JodaUtil.timestamp() + "_bak" + extName);
        FileUtils.forceParentExists(historyPath);
        Files.copy(path, historyPath, StandardCopyOption.REPLACE_EXISTING);

/*
        out.print(new String("不允许执行保存操作".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        fileSaver.showPage(200, 300);
*/

        // 最新文档直接覆盖
        fileSaver.saveToFile(Paths.get(webPath("office"), filename).toString());
        fileSaver.close();
    }

}
