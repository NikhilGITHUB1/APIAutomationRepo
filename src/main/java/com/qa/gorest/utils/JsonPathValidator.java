package com.qa.gorest.utils;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.fwexception.APIFrameworkException;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class JsonPathValidator {

    public String getResponseAsString(Response response){
        return response.getBody().asString();
    }

    public <T> T read(Response response,String jsonPath){
        String jsonResponse = getResponseAsString(response);
        try{
            return JsonPath.read(jsonResponse,jsonPath);
        } catch (PathNotFoundException e) {
            e.printStackTrace();
            throw new APIFrameworkException(jsonPath + "is not found");
        }
    }

    public <T> List<T> readList(Response response, String jsonPath) {
        String jsonResponse = getResponseAsString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        }
        catch (PathNotFoundException e){
            e.printStackTrace();
            throw new APIFrameworkException(jsonPath + " is not found");
        }
    }


    public <T> List<Map<String,T>> readListOfMaps(Response response,String jsonPath) {
        String jsonResponse = getResponseAsString(response);
        try {
            return JsonPath.read(jsonResponse, jsonPath);
        } catch (PathNotFoundException e) {
            throw new APIFrameworkException(jsonPath + " is not found");
        }
    }




}
