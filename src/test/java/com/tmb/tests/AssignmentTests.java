package com.tmb.tests;

import com.tmb.annotations.FrameworkAnnotation;
import com.tmb.pojo.Employee;
import com.tmb.reports.ExtentLogger;
import com.tmb.requestbuilder.RequestBuilder;
import com.tmb.utils.ApiUtils;
import com.tmb.utils.RandomUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static com.tmb.requestbuilder.RequestBuilder.*;
import static com.tmb.utils.RandomUtils.*;
import static org.assertj.core.api.Assertions.*;

public class AssignmentTests extends BaseTest{

    @Test
    @FrameworkAnnotation(author = "Amuthan",category = {"Assignment"})
    public void assignmentTest(){
        Response response = buildRequestForGetCalls().get("/employees");
        int size = response.jsonPath().getList("$").size();

        Employee employee = Employee.builder().setFname(getFirstName()).setLname(getLastName()).setId(getId()).build();

        RequestSpecification requestSpecification = buildRequestForPostCalls().body(employee);
        ExtentLogger.logRequest(requestSpecification);

        Response postResponse = requestSpecification.post("/employees");
        ExtentLogger.logResponse(postResponse.asString());

        assertThat(buildRequestForGetCalls().get("/employees").jsonPath().getList("$").size()).isEqualTo(size+1);

        employee.setFname("Default name");
        int id = employee.getId();
        Response putResponse = buildRequestForPostCalls().pathParam("id", id).body(employee).put("/employees/{id}");
        ApiUtils.storeStringAsJsonFile("putresponse.txt",putResponse);
        ExtentLogger.logResponse(putResponse.asString());

        buildRequestForGetCalls().pathParam("id",id).delete("/employees/{id}");

        assertThat(buildRequestForGetCalls().get("/employees").jsonPath().getList("$").size()).isEqualTo(size);


    }
}
