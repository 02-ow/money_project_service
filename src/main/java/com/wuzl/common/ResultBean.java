package com.wuzl.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBean<T> {

    private long code;
    private String message;
    private T data;

    /**
     * 成功(无结果)
     */
    public static <T> ResultBean<T> success() {
        return new ResultBean<T>(200, "success", null);
    }
    /**
     * 成功
     */
    public static <T> ResultBean<T> success(T data) {
        return new ResultBean<T>(200, "success", data);
    }

    /**
     * 失败(无结果)
     */
    public static <T> ResultBean<T> error(String message) {
        return new ResultBean<T>(500, "success", null);
    }

    /**
     * 失败
     */
    public static <T> ResultBean<T> error(String message, T data) {
        return new ResultBean<T>(500, "success", data);
    }
}
