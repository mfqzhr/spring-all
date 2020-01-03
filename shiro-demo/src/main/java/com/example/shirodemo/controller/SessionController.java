package com.example.shirodemo.controller;

import com.example.shirodemo.bean.UserOnline;
import com.example.shirodemo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 穆繁强
 * @date 2020/1/3
 */
@Controller
@RequestMapping("/online")
public class SessionController {
    @Autowired
    SessionService sessionService;

    @RequestMapping("index")
    public String online() {
        return "online";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<UserOnline> list() {
        return sessionService.list();
    }

    /*@ResponseBody
    @RequestMapping("forceLogout")
    public ResponseBo forceLogout(String id) {
        try {
            sessionService.forceLogout(id);
            return ResponseBo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("踢出用户失败");
        }
    }*/
}
