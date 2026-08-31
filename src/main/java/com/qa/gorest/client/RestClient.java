package com.qa.gorest.client;

import com.github.fge.jsonschema.core.processing.ProcessorSelectorPredicate;
import com.github.fge.jsonschema.core.report.MessageProvider;
import com.qa.gorest.fwexception.APIFrameworkException;
import com.qa.gorest.pojo.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.when;

public class RestClient {

    private Properties prop;
    private String baseURI;

    public RestClient(Properties prop,String baseURI){
        this.prop=prop;
        this.baseURI=baseURI;
    }

    private void addAuthorizationHeader(RequestSpecBuilder specBuilder){
        specBuilder.addHeader("Authorization","Bearer "+ prop.getProperty("tokenId"));
    }

    private void setRequestContentType(RequestSpecBuilder specBuilder,String contentType){
        switch(contentType.toLowerCase().trim()){
            case "json":
                specBuilder.setContentType(ContentType.JSON);
                break;
            case "xml":
                specBuilder.setContentType(ContentType.XML);
            case "text":
                specBuilder.setContentType(ContentType.TEXT);
            case "urlencoded":
                specBuilder.setContentType(ContentType.URLENC);
            case "multipart":
                specBuilder.setContentType(ContentType.URLENC);
            default:
                throw new APIFrameworkException("CONTENT TYPE IS INCORRECT:"+contentType);
             }
    }

    private RequestSpecification createRequestSpec(boolean includeAuth){
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.setBaseUri(baseURI);
        if(includeAuth){
            addAuthorizationHeader(specBuilder);
        }

        return specBuilder.build();

    }

    private RequestSpecification createRequestSpec(Map<String,String> headersMap,
                                                   Map<String,?> queryParams,
                                                   boolean includeAuth)
        {
            RequestSpecBuilder specBuilder = new RequestSpecBuilder();
            specBuilder.setBaseUri(baseURI);
            if(headersMap!=null){
                specBuilder.addHeaders(headersMap);
            }
            if(queryParams!=null){
                specBuilder.addQueryParams(queryParams);
            }
            if(includeAuth){
                addAuthorizationHeader(specBuilder);
            }

            return specBuilder.build();
        }

     private RequestSpecification createRequestSpec(Object requestBody,String contentType,boolean includeAuth){

        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
            specBuilder.setBaseUri(baseURI);
            if(requestBody!=null){
                specBuilder.setBody(requestBody);
            }
            if(contentType!=null){
                setRequestContentType(specBuilder,contentType);
            }
            if(includeAuth){
                addAuthorizationHeader(specBuilder);
            }
            return specBuilder.build();

     }

     public Response get(String serviceUrl, boolean log, boolean includeAuth){
        if(log){
            return RestAssured
                    .given(createRequestSpec(includeAuth))
                    .log().all().
                        when().
                            get(serviceUrl);
        }
         return RestAssured
                 .given(createRequestSpec(includeAuth)).
                 when().
                 get(serviceUrl);
     }

     public Response get(String serviceUrl,
                         Map<String,String>headersMap,
                         Map<String,?>queryParams,
                         boolean log,
                         boolean includeAuth){
        if(log) {
            return RestAssured
                    .given(createRequestSpec(headersMap, queryParams, includeAuth)).
                        log().all().
                        when()
                        .get(serviceUrl);
        }
         return RestAssured
                 .given(createRequestSpec(headersMap, queryParams, includeAuth)).
                 when()
                 .get(serviceUrl);
     }

public Response post(String serviceUrl,
                     Object requestBody,
                     String contentType,
                     boolean log,
                     boolean includeAuth){
        if(log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).
                    log().all().
                    when().
                    post(serviceUrl);
        }

    return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).
            when().
            post(serviceUrl);
    }

public Response put(String serviceUrl,
                         Object requestBody,
                         String contentType,
                         boolean log,
                         boolean includeAuth){
        if(log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).
                    log().all().
                    when().
                    put(serviceUrl);
        }

        return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).
                when().
                put(serviceUrl);
    }

    public Response patch(String serviceUrl,
                        Object requestBody,
                        String contentType,
                        boolean log,
                        boolean includeAuth){
        if(log) {
            return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).
                    log().all().
                    when().
                    patch(serviceUrl);
        }

        return RestAssured.given(createRequestSpec(requestBody, contentType, includeAuth)).
                when().
                patch(serviceUrl);
    }

    public Response delete(String serviceUrl,boolean includeAuth,boolean log){
        if(log){
            return RestAssured.given(createRequestSpec(includeAuth)).log().all().
                    when().
                        delete(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(includeAuth)).
                when().
                delete(serviceUrl);
    }

    public String getAccessToken(String serviceUrl,String grantType,String clientId,String clientSecret){

        return RestAssured.given().baseUri(baseURI)
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("grant_type",grantType)
                .formParam("client_id",clientId)
                .formParam("client_secret",clientSecret)
                .when()
                .post(serviceUrl)
                .then()
                .log().all()
                .extract().path("access_token");


    }

//    public Response post(String serviceUrl,Object requestBody,String contentType,boolean log,boolean auth){
//
//        if(log){
//            return RestAssured.given(createRequestSpec(requestBody,contentType,auth)).
//                    when().post(serviceUrl);
//        }
//        return RestAssured.given(createRequestSpec(requestBody,contentType,auth)).
//                when().log().all().post(serviceUrl);
//       }


//    public ProcessorSelectorPredicate<MessageProvider, MessageProvider> post(String gorestEndpoint, User user, String json, boolean b, boolean b1) {
//    }
}
