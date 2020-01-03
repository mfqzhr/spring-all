package com.example.shirodemo.service;

import com.example.shirodemo.bean.UserOnline;

import java.util.List;

/**
 * @author 穆繁强
 * @date 2020/1/3
 */
public interface SessionService {

    /**
     * 获取在线用户的信息
     *
     * @return
     */
    List<UserOnline> list();

    /**
     * 踢出用户
     *
     * @param sessionId
     * @return
     */
    boolean forceLogout(String sessionId);


}
