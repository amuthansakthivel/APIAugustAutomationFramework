package com.tmb.tests;

import com.tmb.annotations.FrameworkAnnotation;
import com.tmb.reports.ExtentLogger;
import com.tmb.reports.ExtentManager;
import com.tmb.requestbuilder.RequestBuilder;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GetTests extends BaseTest {

    @Test
    @FrameworkAnnotation(author = {"Amuthan","Sachin"},category = {"Smoke","Get Call"})
    public void getEmployeesDetails(){


        Response response = RequestBuilder
                .buildRequestForGetCalls()
                .get("/employees");//class or config.properties  //hamcrest

        ExtentLogger.logResponse(response.asPrettyString());

        assertThat(response.getStatusCode()) //Proxy
                .isEqualTo(200);

        assertThat(response.jsonPath().getList("$").size())
                .isPositive()
                .as("Validating the size of the employee array").isGreaterThan(30);
    }

    @Test(dataProvider = "getData")
    @FrameworkAnnotation(author = {"Amuthan"},category = {"Regression","Get Call"})
    public void getEmployeeDetail(Integer id, String lastname){
        Response response = RequestBuilder
                .buildRequestForGetCalls()
                .pathParam("id",id)
                .get("/employees/{id}");//class or config.properties  //hamcrest

        ExtentLogger.logResponse(response.asPrettyString());

        assertThat(response.getStatusCode()) //Proxy
                .isEqualTo(200);

        assertThat(response.jsonPath().getString("last_name"))
                .as("Comparing the last_name node in the response").isEqualTo(lastname)
                .hasSizeBetween(3,20);
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {2,"Eschweiler"},
                {350,"Funk"},
                {729,"Swaniawski"},
        };
    }
}
