/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.util.Pair;

// Coordinates are 34° 43' 8.0904'' N, 86° 38' 47.3532'' W

/**
 *
 * @author pssewell21
 */
public class Calculation 
{
    private static LocalTime getGreenwichSiderealTime()
    {
        int dayMultiplier = 236;
        int hourMultiplier = 10;
        
        //LocalDateTime baselineDate = LocalDateTime.of(2004, Month.JANUARY, 1, 0, 0, 0);
        // GST at midnight on 1 JAN 2004
        LocalTime baselineGstTime = LocalTime.of(6, 39, 58);        
        
        // Use current dateTime now
        // TODO: Update this to accept parametes from the UI
        LocalDateTime currentDate = LocalDateTime.now();
        
        // Get the number of days we need to add time for
        int currentDayOfYear = currentDate.getDayOfYear();
        int dayDifference = currentDayOfYear - 1;
                
        // Get the number of hours we need to add time for
        int currentHourOfDay = currentDate.getHour();
        
        //System.out.println("dayDifference = " + dayDifference);
        //System.out.println("dayDifference * dayMultiplier = " + dayDifference * dayMultiplier);
        //System.out.println("currentHourOfDay = " + currentHourOfDay);
        //System.out.println("currentHourOfDay * hourMultiplier = " + currentHourOfDay * hourMultiplier);
        
        // Add the multiplied seconds values to the baseline time to get the GST
        LocalTime greenwichSiderealTime = baselineGstTime.plusSeconds(dayDifference * dayMultiplier).plusSeconds(currentHourOfDay * hourMultiplier);
        
        //System.out.println("currentTime = " + currentDate);
        //System.out.println("greenwichSiderealTime = " + greenwichSiderealTime);
        
        return greenwichSiderealTime;
    }
    
    private static double getDecimalCoordinate(int degrees, int minutes, int seconds, String direction) throws Exception
    {   
        // Valudate input
        if (degrees < -180 || degrees > 180)
        {
            throw new Exception("Invalid value of " + degrees + " for degrees passed into Calculation.getDecimalCoordinate");
        }
        
        if (minutes < 0 || minutes > 60)
        {
            throw new Exception("Invalid value of " + minutes + " for minutes passed into Calculation.getDecimalCoordinate");
        }
        
        if (seconds < 0 || seconds > 60)
        {
            throw new Exception("Invalid value of " + seconds + " for seconds passed into Calculation.getDecimalCoordinate");
        }
        
        if (!(direction.equalsIgnoreCase("East") || direction.equalsIgnoreCase("West")))
        {
            throw new Exception("Invalid direction value of " + direction + " was passed into Calculation.getDecimalCoordinate");
        } 

        // Include decimal on 1.0 value to force double precision for this math operation
        // 60 minutes in a degree
        double minutesValue = minutes * (1.0 / 60);
        // 60 seconds in a minute
        double secondsValue = seconds * (1.0 / (60 * 60));
        
        double longitude = degrees + minutesValue + secondsValue;
        
        if (direction.equalsIgnoreCase("West"))
        {
            longitude *= -1;
        }
        
        return longitude;
    }
    
    private static Duration getDurationFromDecimalHours(double hours) throws Exception
    {
        // Time offset can be + or - 12 hours
        if (hours < -12 || hours > 12)
        {
            throw new Exception("Invalid value of " + hours + " for hours passed into Calculation.getTimeFromDecimalHours");
        }
        
//        int hour = (int)Math.floor(hours);
//        
//        double remainder = hours - hour;        
//        
//        double minutes = remainder * 60;
//        int minute = (int)Math.floor(minutes);
//        
//        remainder = minutes - minute;
//        
//        double seconds = remainder * 60;
//        int second = (int)Math.floor(seconds);

        double seconds = hours * 60 * 60;
        
        return Duration.ofSeconds((int)Math.floor(seconds));
    }
        
    // Assuming we will only allow whole second values, we can easily shange this to double the precision is needed
    // TODO: Verify accuracy
    public static LocalTime getLocalSiderealTime(int degrees, int minutes, int seconds, String direction) throws Exception
    {
        // Convert H M S to decimal coordinate value
        double longitude = getDecimalCoordinate(degrees, minutes, seconds, direction);
        System.out.println("longitude for " + degrees + " " + minutes + " " + seconds + " " + direction + " = " + longitude);
        
        // Devide the result by 15.  Use 15.09 to force decimal precision if the first value is a whole number
        double hourOffset = longitude / 15.0;
        
        Duration timeOffset = getDurationFromDecimalHours(hourOffset);
        System.out.println("Offset of " + hourOffset + " hours = " + timeOffset);
        
        LocalTime greenwichSiderealTime = getGreenwichSiderealTime();
        System.out.println("Current Greenwich Sidereal Time: " + greenwichSiderealTime);
        
        if (direction.equalsIgnoreCase("West"))
        {
            return greenwichSiderealTime.minus(timeOffset);
        }
        else
        {
            return greenwichSiderealTime.plus(timeOffset);
        }
    }
    
    /**
     *
     * @param rightAscention
     * @param declination
     * @param latitude
     * @param localSiderealTime
     * @return
     */
//    public static double getAzimuthAndElevation(double rightAscention, double declination, double latitude, LocalTime localSiderealTime)
//    {
//        if (rightAscention < 0)
//        {
//            rightAscention += 360;
//        }
//        
//        double rightAscentionDecimalHours = rightAscention / 360.0;                
//        int rightAscentionHours = (int)Math.floor(rightAscentionDecimalHours);
//        int rightAscentionMinutes = (int)Math.round(60 * (rightAscentionDecimalHours - rightAscentionHours));
//        LocalTime rightAscentionTime = LocalTime.of(rightAscentionHours, rightAscentionMinutes);
//        
//        LocalTime hourAngle = localSiderealTime.minus(rightAscentionTime);
//        
//        return 0.0;
//    }
}
