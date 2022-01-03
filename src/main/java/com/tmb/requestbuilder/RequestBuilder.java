package com.tmb.requestbuilder;

import com.tmb.enums.PropertiesType;
import com.tmb.reports.RequestLogger;
import com.tmb.utils.PropertyUtils;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.PrintStream;

import static io.restassured.RestAssured.*;

public final class RequestBuilder { //extend this class

    private RequestBuilder(){}

    public static RequestSpecification buildRequestForGetCalls(){
        return given()
                .baseUri(PropertyUtils.getValue(PropertiesType.BASEURL))
                .log()
                .all();
    }
    public static RequestSpecification buildRequestForPostCalls(){
        return buildRequestForGetCalls()
                .contentType(ContentType.JSON);
    }
}
