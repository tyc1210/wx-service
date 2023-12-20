package com.tyc.wx.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2023-12-11 15:09:31
 */
@Data
public class PageReq {
    @NotNull(message = "page is null")
    private Integer page;
    @NotNull(message = "size is null")
    private Integer size;
}
