package com.example.shirodemo.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author 穆繁强
 * @date 2020/1/2
 */

@Data
public class User implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * userName
     */
    private String userName;

    /**
     * password
     */
    private String password;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * Status
     */
    private String status;

    private static final long serialVersionUID = 1L;
}