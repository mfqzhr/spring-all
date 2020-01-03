package com.example.shirodemo.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 穆繁强
 * @date 2020/1/3
 */

@Data
public class Permission implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * url
     */
    private String url;

    /**
     * name
     */
    private String name;

    private static final long serialVersionUID = 1L;
}