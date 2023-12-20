package com.tyc.wx.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-11 14:59:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSession {
    private Map<String, String> map;
    private Long timeStamp;
}
