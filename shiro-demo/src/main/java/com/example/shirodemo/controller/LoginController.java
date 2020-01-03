package com.example.shirodemo.controller;

import cn.hutool.crypto.SecureUtil;
import com.example.shirodemo.service.UserService;
import com.example.shirodemo.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 穆繁强
 * @date 2020/1/2
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String redirectIndex() {
        return "redirect:/index";
    }

    @GetMapping("/403")
    public String forbid() {
        return "error";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String userName, String password, HttpSession session, Model model) {
        password = MD5Utils.encrypt(userName, password);
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            session.setAttribute("user", userService.findByUserName(userName));
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", e.getMessage());
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", e.getMessage());
            return "login";
        } catch (LockedAccountException e) {
            model.addAttribute("msg", e.getMessage());
            return "login";
        } catch (AuthenticationException e) {
            model.addAttribute("msg", "认证失败");
            return "login";
        }


    }
}
