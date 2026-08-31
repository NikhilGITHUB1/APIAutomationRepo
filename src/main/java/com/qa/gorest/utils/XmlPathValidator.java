package com.qa.gorest.utils;

import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.fwexception.APIFrameworkException;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class XmlPathValidator {

    private XmlPath getXmlPath(Response response){
        String resBody = response.body().asString();
        return new XmlPath(resBody);
    }

    public <T> T read(Response response,String xmlPathExpression) {
        XmlPath xmlPath = getXmlPath(response);

        try {
            return xmlPath.get(xmlPathExpression);
        }
        catch (PathNotFoundException e){
            e.printStackTrace();
            throw new APIFrameworkException(xmlPathExpression + " is not found");
        }
    }

    public <T> List<T> readList(Response response,String xmlPathExpression) {
        XmlPath xmlPath = getXmlPath(response);
        try {
            return xmlPath.getList(xmlPathExpression);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIFrameworkException(xmlPathExpression + " is not found");
        }
    }

    public <T> List<Map<String,T>> readListOfMaps(Response response,String xmlPathExpression){
        XmlPath xmlPath = getXmlPath(response);
        try{
            return xmlPath.getList(xmlPathExpression);
        }
        catch(PathNotFoundException e){
            e.printStackTrace();
            throw new APIFrameworkException(xmlPathExpression + "is not found");
        }

    }

}
