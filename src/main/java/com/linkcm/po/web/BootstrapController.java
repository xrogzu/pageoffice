package com.linkcm.po.web;

import com.linkcm.po.domain.Doc;
import com.linkcm.po.domain.User;
import com.linkcm.po.repository.UserRepo;
import com.linkcm.po.util.DocEditor;
import com.linkcm.po.util.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:junbinzhong@linkcm.com">发送邮件</a>
 * @createDate : 2017/2/4 10:00
 * @description :
 */
@Controller
public class BootstrapController {

    @Autowired
    private UserRepo userRepo;
    private static final String ERROR_MSG_KEY = "errorMsg";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute(ERROR_MSG_KEY) String errorMsg, Model model) {
        model.addAttribute(ERROR_MSG_KEY, errorMsg);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password,
                        RedirectAttributes redirectAttributes, HttpSession session) {
        User user = userRepo.login(username, password);
        if (user == null) {
            String errorMsg = "用户名/密码错误";
            redirectAttributes.addFlashAttribute(ERROR_MSG_KEY, errorMsg);
            return "redirect:/login";
        }
        session.setAttribute(Global.SESSION_USER_KEY, user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute(Global.SESSION_USER_KEY);
        DocEditor.LOCK.lock();
        try {
            // 登出的时候必须将该用户打开的所有编辑状态的文档去掉
            if (user != null) {
                DocEditor.removeEditingDoc(user.getUsername());
            }
        } finally {
            DocEditor.LOCK.unlock();
        }
        session.removeAttribute(Global.SESSION_USER_KEY);
        session.invalidate();
        return "redirect:/login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpSession session, RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute(Global.SESSION_USER_KEY);
        if (user == null) {
            String errorMsg = "用户尚未登陆，请先登录";
            redirectAttributes.addFlashAttribute(ERROR_MSG_KEY, errorMsg);
            return "redirect:/login";
        }
        Doc doc = user.getDoc();
        if (doc == null) {
            String errorMsg = "用户" + user.getUsername() + "尚未拥有可访问的文档，请联系管理员授予文档访问权限";
            redirectAttributes.addFlashAttribute(ERROR_MSG_KEY, errorMsg);
            return "redirect:/login";
        }
        if (doc.isEditable()) {
            return "redirect:/index/edit";
        } else {
            return "redirect:/index/read/only";
        }
    }

}
