/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import javafx.util.Pair;

// Coordinates are 34° 43' 8.0904'' N, 86° 38' 47.3532'' W

/**
 *
 * @author pssewell21
 */
public class Calculation 
{
    // Gregorian Calendar adopted Oct. 15, 1582 (2299161)
    public static int JGREG = 15 + 31 * (10 + 12 * 1582);
  
    private static LocalTime getGreenwichSiderealTime()
    {
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Get Julian date
        
        // Reference: https://www.rgagnon.com/javadetails/java-0506.html
        int year = currentDateTime.getYear();
        int month = currentDateTime.getMonth().ordinal();
        int day = currentDateTime.getDayOfYear();
        
        int hour = currentDateTime.getHour();
        int minute = currentDateTime.getMinute();
        int second = currentDateTime.getSecond();
        
        double decimalHour = hour + (minute / 60) + (second / (60 * 60));
        
        int julianYear = year;
        int julianMonth = month;
        
        if (year < 0)
        {
            julianYear++;
        }
        
        if (month > 2)
        {
            julianMonth++;
        }
        else
        {
            julianYear--;
            julianMonth += 13;
        }
        
        double julianDate = Math.floor(365.25 * julianYear) 
                + Math.floor(30.6001 * julianMonth)
                + day
                + 1720995.0;
        
        if (day + 31 * (month + 12 * year) >= JGREG)
        {
            // change over to gregorian calendar
            int adjustmentValue = (int)(0.01 * julianYear);
            julianDate += 2 - adjustmentValue + (0.25 * adjustmentValue);
        }
        
        julianDate = Math.floor(julianDate);
        julianDate += (decimalHour / 24);
        
        System.out.println("Julian date = " + julianDate);
        
        // Convert
        double con = (julianDate - 2415020) / 36525;
        double stuff = (6.6460656 + (2400.051 * con) + (0.00002581 * con * con));
        double thing = ((stuff / 24) - Math.floor(stuff / 24)) * 24;
        
        int gstHour = (int)Math.floor(thing);
        int gstMinute = (int)Math.floor((thing - Math.floor(thing)) * 60);
        int gstSecond = (int)((thing - Math.floor(thing)) * 60 - gstMinute) * 60;
        
        LocalTime gstTime = LocalTime.of(gstHour, gstMinute, gstSecond);
        
        System.out.println("GST Time = " + gstTime);
        
        
        
//        int dayMultiplier = 236;
//        int hourMultiplier = 10;
//        
//        //LocalDateTime baselineDate = LocalDateTime.of(2004, Month.JANUARY, 1, 0, 0, 0);
//        // GST at midnight on 1 JAN 2004
//        LocalTime baselineGstTime = LocalTime.of(6, 39, 58);        
//        
//        // Use current dateTime now
//        // TODO: Update this to accept parametes from the UI
//        LocalDateTime currentDate = LocalDateTime.now();
//        //LocalDateTime currentDate = LocalDateTime.of(2019, Month.FEBRUARY, 9, 17, 5, 0);
//        
//        // Get the number of days we need to add time for
//        int currentDayOfYear = currentDate.getDayOfYear();
//        int dayDifference = currentDayOfYear - 1;
////        Duration dateDifference = Duration.between(baselineDate, currentDate);
////        int dayDifference = (int) dateDifference.toDays();
//                
//        // Get the number of hours we need to add time for
//        int currentHourOfDay = currentDate.getHour();
//        
//        System.out.println("dayDifference = " + dayDifference);
//        System.out.println("dayDifference * dayMultiplier = " + dayDifference * dayMultiplier);
//        System.out.println("currentHourOfDay = " + currentHourOfDay);
//        System.out.println("currentHourOfDay * hourMultiplier = " + currentHourOfDay * hourMultiplier);
//        
//        // Add the multiplied seconds values to the baseline time to get the GST
//        LocalTime greenwichSiderealTime = baselineGstTime.minusSeconds(dayDifference * dayMultiplier).plusSeconds(currentHourOfDay * hourMultiplier);
//        
//        System.out.println("currentTime = " + currentDate);
//        System.out.println("greenwichSiderealTime = " + greenwichSiderealTime);
//        //
//        return greenwichSiderealTime;
        
        
        
        
        
        
        
        // Probably garbage, but keeping for now in case any of it is useful after discussion with Dr. Coleman - PS
//        double dayMultiplier = 236.0;
//        int hourMultiplier = 10;
//        
//        LocalDateTime baselineDate = LocalDateTime.of(2004, Month.JANUARY, 1, 0, 0, 0);
//        // GST at midnight on 1 JAN 2004
//        LocalTime baselineGstTime = LocalTime.of(6, 39, 58);
//        
//        // Use current dateTime now
//        // TODO: Update this to accept parametes from the UI
//        //LocalDateTime currentDate = LocalDateTime.now();
//        LocalDateTime currentDate = LocalDateTime.of(2019, Month.FEBRUARY, 9, 17, 5, 0);
//        
//        // Get the number of days we need to add time for
//        //int currentDayOfYear = currentDate.getDayOfYear();
//        //int dayDifference = currentDayOfYear - 1;
//        Duration dateDifference = Duration.between(baselineDate, currentDate);
//        int dayDifference = (int) dateDifference.toDays();
//                
//        // Get the number of hours we need to add time for        
//        //int hourDifference = currentDate.getHour();
//        LocalDateTime currentDateNoTime = LocalDateTime.of(2019, Month.FEBRUARY, 9, 0, 0, 0);
//        Duration dateDifferenceNoTime = Duration.between(baselineDate, currentDateNoTime);
//        int hourDifference = (int) dateDifferenceNoTime.toHours();
//        
//        LocalTime timeToAdd = currentDate.toLocalTime().minusSeconds(hourDifference * hourMultiplier);
//        int seconds = (timeToAdd.getHour() * 60 * 60) + (timeToAdd.getMinute() * 60) + timeToAdd.getSecond();
//        //Duration durationToAdd = Duration.ofSeconds(seconds);
//        
//        System.out.println("dayDifference = " + dayDifference);
//        System.out.println("dayDifference * dayMultiplier = " + dayDifference * dayMultiplier);
//        System.out.println("hourDifference = " + hourDifference);
//        System.out.println("hours adjustment = " + seconds);
//        
//        // Add the multiplied seconds values to the baseline time to get the GST
//        LocalTime greenwichSiderealTime = baselineGstTime.minusSeconds(Math.round(dayDifference * dayMultiplier)).plusSeconds(seconds);
//        //LocalTime greenwichSiderealTime = baselineGstTime.plusSeconds(Math.round(dayDifference * dayMultiplier)).minusSeconds(seconds);
//        
//        System.out.println("currentTime = " + currentDate);
//        //System.out.println("greenwichSiderealTime = " + greenwichSiderealTime);
//        
//        return greenwichSiderealTime;
        return LocalTime.NOON;
    }
    
    private static double getDecimalCoordinate(int degrees, int minutes, int seconds, String direction) throws Exception
    {   
        // Valudate input
        if (degrees < 0 || degrees > 180)
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
        
        // Devide the result by 15.  Use 15.0 to force decimal precision if the first value is a whole number
        double hourOffset = longitude / 15.0;
        
        Duration timeOffset = getDurationFromDecimalHours(hourOffset);
        System.out.println("Offset of " + hourOffset + " hours = " + timeOffset);
        
        LocalTime greenwichSiderealTime = getGreenwichSiderealTime();
        System.out.println("Current Greenwich Sidereal Time: " + greenwichSiderealTime);

        return greenwichSiderealTime.plus(timeOffset);
    }
    
    /**
     *
     * @param rightAscention
     * @param declination
     * @param latitude
     * @param localSiderealTime
     * @return
     */
    public static double getAzimuthAndElevation(double rightAscention, double declination, double latitude, LocalTime localSiderealTime) throws Exception
    {
        if (rightAscention < 0 || rightAscention > 24)
        {
            throw new Exception("Invalid value of " + rightAscention + " for rightAscention passed into Calculation.getAzimuthAndElevation");
        }
        
        if (declination < -90 || declination > 90)
        {
            throw new Exception("Invalid value of " + declination + " for declination passed into Calculation.getAzimuthAndElevation");
        }
        
//        double rightAscentionDecimalHours = rightAscention / 15.0;                
//        int rightAscentionHours = (int)Math.floor(rightAscentionDecimalHours);
//        int rightAscentionMinutes = (int)Math.round(60 * (rightAscentionDecimalHours - rightAscentionHours));
//        Duration rightAscentionTime = Duration.ofMinutes((rightAscentionHours * 60) + rightAscentionMinutes);

//        double rightAscentionHours = rightAscention / 15.0;
//        
//        Duration rightAscentionDuration = getDurationFromDecimalHours(rightAscentionHours);
//        
//        LocalTime hourAngle = localSiderealTime.minus(rightAscentionDuration)
        
        // Compute object hours angle
        double decimalHours = localSiderealTime.getHour() + (localSiderealTime.getMinute() / 60) + (localSiderealTime.getSecond() / (60 * 60));
        
        double lstDegrees = decimalHours * 15;
                
        double hourAngle = lstDegrees - rightAscention;
        
        // Compute Azimuth
        double azimuth = Math.atan((-1 * Math.sin(hourAngle) * Math.cos(declination)) / ((Math.cos(latitude) * Math.sin(declination)) - (Math.sin(latitude) * Math.cos(declination) * Math.cos(hourAngle))));
        
        // Compute Elevation
        
        double elevation = Math.asin((Math.sin(latitude) * Math.sin(declination)) + (Math.cos(latitude) * Math.cos(declination) * Math.cos(hourAngle)));
        
        System.out.println("hourAngle = " + hourAngle);
        System.out.println("azimuth = " + azimuth);
        System.out.println("elevation = " + elevation);
        
        return 0.0;
    }
}
