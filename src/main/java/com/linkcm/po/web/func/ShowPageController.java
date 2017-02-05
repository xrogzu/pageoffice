package com.linkcm.po.web.func;

import com.linkcm.po.web.BaseController;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 18:07
 * @description :
 */
@Controller
@RequestMapping(value = "/sp")
public class ShowPageController extends BaseController {

    @Override
    protected PageOfficeCtrl create(HttpServletRequest request) {
        PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
        poCtrl.setServerPage(request.getContextPath() + "/poServer");
        poCtrl.setSaveFilePage(request.getContextPath() + "/sp/save/file");
        return poCtrl;
    }

    @RequestMapping(value = "/open", method = RequestMethod.GET)
    public String open(HttpServletRequest request) {
        PageOfficeCtrl wordCtrl = this.create(request);
        wordCtrl.setTitlebar(false);
        wordCtrl.setMenubar(false);

        wordCtrl.addCustomToolButton("保存", "saveFile", 1);

        String documentPath = Paths.get(webPath("/office/测试.docx")).toUri().toString();
        wordCtrl.webOpen(documentPath, OpenModeType.docNormalEdit, "佚名");

        wordCtrl.setTagId("wordCtrl");

        return "func/word";
    }


    @RequestMapping(value = "/save/file", method = RequestMethod.POST)
    public void saveFile(HttpServletRequest request, HttpServletResponse response) {
        FileSaver fileSaver = new FileSaver(request, response);

        String msg = "中文";
        Charset GB18030 = Charset.forName("GB18030");
        Charset UTF8 = UTF_8;
        Charset ISO88591 = ISO_8859_1;
        String separator = "---";
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder
                // 1、UTF-8 ==> GB18030
                .append(new String(msg.getBytes(UTF8), GB18030))
                .append(separator)
                // 2、UTF-8 ==> ISO88591
                .append(new String(msg.getBytes(UTF8), ISO88591))
                .append(separator)
                // 3、UTF-8 ==> UTF-8
                .append(new String(msg.getBytes(UTF8), UTF8))
                .append(separator)

                // 4、GB18030 ==> ISO88591
                .append(new String(msg.getBytes(GB18030), ISO88591))
                .append(separator)
                // 5、GB18030 ==> UTF8
                .append(new String(msg.getBytes(GB18030), UTF8))
                .append(separator)
                // 6、GB18030 ==> GB18030
                .append(new String(msg.getBytes(GB18030), GB18030))
                .append(separator)

                // 7、ISO88591 ==> GB18030
                .append(new String(msg.getBytes(ISO88591), GB18030))
                .append(separator)
                // 8、ISO88591 ==> UTF8
                .append(new String(msg.getBytes(ISO88591), UTF8))
                .append(separator)
                // 9、ISO88591 ==> ISO88591
                .append(new String(msg.getBytes(ISO88591), ISO88591))
                .append(separator);

        fileSaver.setCustomSaveResult(msgBuilder.toString());
        fileSaver.close();
    }


}
