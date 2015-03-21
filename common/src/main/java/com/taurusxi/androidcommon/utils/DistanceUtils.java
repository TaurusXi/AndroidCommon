package com.taurusxi.androidcommon.utils;

/**
 * Created by DELL on 2014/12/11.
 */
public class DistanceUtils {
    private final static double RAD = Math.PI / 180.0;
    private final static double EARTH_RADIUS = 6378137;

    public static String getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = lat1 * RAD;
        double radLat2 = lat2 * RAD;
        double radLng1 = lng1 * RAD;
        double radLng2 = lng2 * RAD;
        double distance = Math.acos(Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLng1 - radLng2) + Math.sin(radLat1) * Math.sin(radLat2)) * EARTH_RADIUS;
        int distanceInt = (int) (Math.round(distance * 10000) / 10000);
        if (distanceInt <= 0) {
            return "";
        } else {
            StringBuilder result = new StringBuilder();
            result.append("离你 ");
            if (distanceInt < 1000) {
                result.append(distanceInt);
                result.append(" ");
            } else if (distanceInt < 1000 * 10) {
                int km = distanceInt / 1000;
                int pecent = (distanceInt - km * 1000) / 100;
                result.append(km + "." + pecent + " 千");
            } else {
                int km = distanceInt / 1000;
                result.append(km + " 千");
            }
            result.append("米/");
            return result.toString();
        }

    }
}
