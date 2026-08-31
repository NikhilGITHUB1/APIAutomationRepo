package com.qa.gorest.utils;

public class StringUtils {

    public static String getRandomEmailID(){
        return "api" + System.currentTimeMillis() + "@api.com";
    }

}
