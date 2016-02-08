package com.util;

/**
 * Created by M-Sem on 06/12/2015.
 */
public class RESTResponse {

    private Long id;

    private String message;

    private Object object;

    public RESTResponse() {
    }

    public RESTResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public RESTResponse(Object object) {
        this.object = object;
    }

    public RESTResponse(Object object, String message) {
        this.object = object;
        this.message = message;
    }

    public RESTResponse(String message) {
        this.message = message;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
