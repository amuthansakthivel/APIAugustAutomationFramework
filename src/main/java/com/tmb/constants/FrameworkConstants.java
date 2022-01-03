package com.tmb.constants;


import lombok.Getter;

public class FrameworkConstants {

    //If it non static --> Class level

    private static @Getter final String requestJsonFolderpath = System.getProperty("user.dir") + "/src/test/resources/jsons/";
    private static @Getter final String responseJsonFolderPath = System.getProperty("user.dir") + "/output/";
    private static @Getter final String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";



}
