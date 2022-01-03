package com.tmb.tests;

import com.tmb.annotations.FrameworkAnnotation;
import com.tmb.constants.FrameworkConstants;
import com.tmb.pojo.Employee;
import com.tmb.reports.ExtentLogger;
import com.tmb.requestbuilder.RequestBuilder;
import com.tmb.utils.ApiUtils;
import com.tmb.utils.RandomUtils;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.tmb.constants.FCwithSingleton.getInstance;
import static com.tmb.utils.RandomUtils.*;
import static io.restassured.RestAssured.given;

public class PostTests extends BaseTest{

    @Test
    @FrameworkAnnotation(author = {"Sachin"},category = {"Smoke","Post Call"})
    public void postTestUsingPojo(){
        //Create an employee in the db using post call
        //construct using pojo and lombak builder

        Employee employee = Employee
                .builder()
                .setFname(getFirstName())
                .setLname(getLastName())
                .setId(getId()) //new Faker().idNumber().between()
                .build();

        RequestSpecification requestSpecification = given().baseUri("http://localhost:3000")
                .log()
                .all()
                .body(employee);
        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");
        response.prettyPrint();

        ExtentLogger.logResponse(response.asPrettyString());

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);

    }

    @Test
    @FrameworkAnnotation(author = {"Sachin"},category = {"Regression","Post Call"})
    public void postRequestUsingExternalFile(Method method){ //throws IOEXception
        String resource = ApiUtils
                .readJsonAndGetAsString(FrameworkConstants.getRequestJsonFolderpath()+"request.json")
                .replace("fname", RandomUtils.getFirstName())
                .replace("id",String.valueOf(RandomUtils.getId()));

        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(resource);

        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");
        response.prettyPrint();

        ExtentLogger.logResponse(response.asPrettyString());
        ApiUtils.storeStringAsJsonFile(FrameworkConstants.getResponseJsonFolderPath()+method.getName()+"response.json",response);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
    }

    @Test
    @FrameworkAnnotation
    public void postRequestUsingExternalFile1(Method method){ //throws IOEXception
        String resource = ApiUtils
                .readJsonAndGetAsString(
                        getInstance().getRequestJsonFolderpath() +"request.json")
                .replace("fname", RandomUtils.getFirstName())
                .replace("id",String.valueOf(RandomUtils.getId())); //arrange

        //Interface
        //

        RequestSpecification requestSpecification = RequestBuilder
                .buildRequestForPostCalls()
                .body(resource);

        ExtentLogger.logRequest(requestSpecification);
        Response response = requestSpecification.post("/employees");
        response.prettyPrint(); //actions


        ExtentLogger.logResponse(response.asPrettyString());

        ApiUtils
                .storeStringAsJsonFile(getInstance().getResponseJsonFolderPath()+method.getName()+"response.json",response);
        //response schema validation
        Assertions.assertThat(response.getStatusCode()).isEqualTo(201); //assertions



    }
}
