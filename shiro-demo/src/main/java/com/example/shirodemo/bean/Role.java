package com.example.shirodemo.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 穆繁强
 * @date 2020/1/3
 */

@Data
public class Role implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * memo
     */
    private String memo;

    private static final long serialVersionUID = 1L;
}