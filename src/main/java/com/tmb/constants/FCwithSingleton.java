package com.tmb.constants;

import lombok.Getter;

@Getter
public class FCwithSingleton { //Single ton-->Single instance for an class at a particular point of time
    //creational design pattern

    private static FCwithSingleton INSTANCE=null;

    private FCwithSingleton(){}

    public static synchronized FCwithSingleton getInstance(){ //10 threads
        if(INSTANCE==null){
            INSTANCE=new FCwithSingleton();
        }
        return INSTANCE;
    }



    private  final String requestJsonFolderpath = System.getProperty("user.dir") + "/src/test/resources/jsons/";
    private  final String responseJsonFolderPath = System.getProperty("user.dir") + "/output/";

}
