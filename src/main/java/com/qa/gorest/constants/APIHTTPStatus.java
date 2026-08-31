package com.qa.gorest.constants;

public enum APIHTTPStatus {

    OK_200(200,"OK"),
    DELETED_204(204, "Deleted"),
    CREATED_201(201, "Created");

    private final int code;
    private final String message;

    APIHTTPStatus(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

}
