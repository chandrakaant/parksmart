package com.ckp.parksmart.util;

/**
 * Created by saralmohan on 3/1/18.
 */

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class NullEmptyUtils {

    private NullEmptyUtils() {
    }

    /**
     * Checks whether the given string is null or empty
     *
     * @param val
     * @return
     */
    public static boolean isNullorEmpty(String val) {
        return isNull(val) || val.trim().isEmpty();
    }

    /**
     * Checks whether the given List is null or empty
     *
     * @param val
     * @return
     */
    public static boolean isNullorEmpty(List<?> val) {
        return isNull(val) || val.isEmpty();
    }

    /**
     * Checks whether the given Collection is null or empty
     *
     * @param val
     * @return
     */
    public static boolean isNullorEmpty(Collection<?> val) {
        return isNull(val) || val.isEmpty();
    }

    /**
     * Checks whether the given Number is null or empty
     *
     * @param val
     * @return
     */
    public static boolean isNullorEmpty(Number val) {
        return isNull(val) || val.doubleValue() == 0.0D || val.intValue() == 0;
    }

    /**
     * Checks whether the given Map is null or empty
     *
     * @param val
     * @return
     */
    public static boolean isNullorEmpty(Map<?, ?> val) {
        return isNull(val) || val.isEmpty();
    }

    /**
     * Checks whether the given string is null or empty or "empty"
     *
     * @param val
     * @return
     */
    public static boolean isNullorEmptyString(String val) {
        return isNull(val) || val.isEmpty() || val.trim().equalsIgnoreCase("empty");
    }

    /**
     * Checks whether the given string is null or empty or "empty" or "null"
     *
     * @param val
     * @return
     */
    public static boolean isNullorEmptyOrNullString(String val) {
        return isNull(val) || val.isEmpty() || val.trim().equalsIgnoreCase("empty")
                || val.trim().equalsIgnoreCase("null");
    }

    /**
     * Checks whether the given Object is null
     *
     * @param val
     * @return
     */
    public static boolean isNull(Object val) {
        return val == null;
    }

}
