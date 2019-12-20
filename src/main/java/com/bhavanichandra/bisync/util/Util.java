package com.bhavanichandra.bisync.util;

public class Util {

    public static <T extends Enum<T>> String getLowerCaseValue(Enum<T> type) {
        return type.toString().toLowerCase();
    }

}
