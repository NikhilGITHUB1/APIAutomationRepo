package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHTTPStatus;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GetUserTest extends BaseTest {

    @BeforeClass
    public void getUserSetUp(){
        restClient = new RestClient(prop,baseURI);
    }

    @Test
    public void getUserTest(){
        restClient.get(GOREST_ENDPOINT,true,true)
                .then().log().all().assertThat().statusCode(200);
    }

    @Test
    public void getSingleUserTest(){
        int id = 8596300;
        Response res =restClient.get(GOREST_ENDPOINT+"/"+id,true,true);
        String name = res.then().extract().path("name");
        System.out.println("name for the "+id + " is "+name);

}

}
