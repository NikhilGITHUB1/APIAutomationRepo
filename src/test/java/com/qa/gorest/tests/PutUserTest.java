package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PutUserTest extends BaseTest {

    int id = 8596299;
    String name;
    String gender;
    String status;
    String email;

    @BeforeClass
    public void setUp(){

        restClient = new RestClient(prop,baseURI);

    }

    @Test
    public void getId(){
        Response res = restClient.get(GOREST_ENDPOINT +"/"+id,true,true);
        name = res.then().extract().path("name");
        gender = res.then().extract().path("gender");
        status = res.then().extract().path("status");
        email = res.then().extract().path("email");
        System.out.println("current name is "+"for "+id+" "+name);
    }

    @Test
    public void putUserTest(){

        User user = new User("Rathore",email,gender,status);

        Response res  = restClient.put(GOREST_ENDPOINT+"/"+id,user,"json",true,true);
        String name = res.then().extract().path("name");
        System.out.println("updated name is "+"for "+id+" "+name);



    }


}
