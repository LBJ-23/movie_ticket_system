package com.yyc.config;

/**
 * 定义返回前台数据包装类
 * @param <T>
 */

public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static Result success(){
        Result result = new Result<>();
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>(data);
        result.setCode("200");
        result.setMsg("success");
        return result;
    }

    public static <T> Result<T> successSoon(String code,T data){
        Result<T> result = new Result<>(data);
        result.setCode(code);
        result.setMsg("success");
        return result;
    }

    public static Result error(String code,String msg){
        Result result = new Result();
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }
}
