package com.example.courseEnrollment.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("message")
    private String msg;

    @JsonProperty("status")
    private String status;

    @JsonProperty("result")
    private Object response;

    public Response(){

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public static Response createResponse(String msg, String status, Object ob){
        Response response = new Response();
        response.setResponse(ob);
        response.setMsg(msg);
        response.setStatus(status);
        return response;

    }
}
