package com.github.shmvanhouten.addressbook.util;

public class CoalesceMaxUtil {
    public static String coalesceMax(String column){
        return "coalesce(MAX(" + column + " ), 0)";
    }
}
