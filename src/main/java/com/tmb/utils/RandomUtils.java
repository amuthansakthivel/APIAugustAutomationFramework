package com.tmb.utils;

public final class RandomUtils {

    //business layer --> all the business level changes

    private RandomUtils(){}

    public static int getId(){
        return FakerUtils.getNumber(1000,2000);
    }
    public static String getFirstName(){
        return FakerUtils.getFirstName().toLowerCase();
    }
    public static String getLastName(){
        return FakerUtils.getLastName().toLowerCase();
    }

}
