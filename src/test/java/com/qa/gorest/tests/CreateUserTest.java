package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHTTPStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CreateUserTest extends BaseTest {

    @BeforeClass
    public void setUp(){
        restClient = new RestClient(prop,baseURI);
    }

    @DataProvider
    public Object[][] data(){
        return new Object[][]{
                {"nikhil","male","active"},
                {"kalyani","female","inactive"}
        };
    }

    @Test(dataProvider = "data")
    public void createUserTest(String username,String gender,String status){

        User user = new User(username, StringUtils.getRandomEmailID(),gender,status);
        restClient.post(GOREST_ENDPOINT,user,"json",true,true).
               then().log().all().assertThat().statusCode(APIHTTPStatus.CREATED_201.getCode());
    }

}
