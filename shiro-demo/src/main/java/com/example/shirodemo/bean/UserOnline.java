package com.example.shirodemo.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 穆繁强
 * @date 2020/1/3
 */
@Data
public class UserOnline implements Serializable {

    private static final long serialVersionUID = 3828664348416633856L;
    // session id
    private String id;
    // 用户id
    private String userId;
    // 用户名称
    private String username;
    // 用户主机地址
    private String host;
    // 用户登录时系统IP
    private String systemHost;
    // 状态
    private String status;
    // session创建时间
    private Date startTimestamp;
    // session最后访问时间
    private Date lastAccessTime;
    // 超时时间
    private Long timeout;
}