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
import static java.util.Calendar.MONTH;
import java.util.HashMap;
import java.util.Map;

// Coordinates for Tech Hall are 34° 43' 8.0904'' N, 86° 38' 47.3532'' W

/**
 *
 * @author pssewell21
 */
public class Calculation 
{
    // The julian date on JAN 1, 2000
    static double baselineJulianDate = 2451545.0;
    static LocalDateTime baselineDate = LocalDateTime.of(2000, 1, 1, 12, 0, 0);
    
    public static double getJulianDate(LocalDateTime dateTime)            
    {
        // Reference: https://aa.usno.navy.mil/faq/docs/GAST.php
        Duration duration = Duration.between(baselineDate, dateTime);
        
        double julianDate = 0;
        
        if (dateTime.getYear() >= 2000)
        {
            julianDate = baselineJulianDate + duration.toDays();
        }
        else
        {
            julianDate = baselineJulianDate - duration.toDays();
        }
        
//        System.out.println("julianDate = " + julianDate);
        
        double decimalHours = dateTime.getHour() + (dateTime.getMinute() / 60.0) + (dateTime.getSecond() / (60.0 * 60));
        
        // Adjust for hour of day
        if (decimalHours >= 12)
        {
            julianDate += ((decimalHours - 12) / 24.0) - 1;            
        }
        else
        {
            julianDate += ((decimalHours - 12) / 24.0) + 1;            
        }
        
//        System.out.println("julianDate with time adjustment = " + julianDate);
        
        return julianDate;
    }
  
    private static LocalTime getGreenwichSiderealTime(LocalDateTime dateTime)
    {        
        // Reference: https://aa.usno.navy.mil/faq/docs/GAST.php
        double julianDate = getJulianDate(dateTime);
        double d = julianDate - baselineJulianDate;
        
        double gstHours = (18.697374558 + 24.06570982441908 * d) % 24;
//        System.out.println("gstHours = " + gstHours);
        
        int hour = (int)Math.floor(gstHours);
//        System.out.println("hour = " + hour);        
        
        gstHours -= hour;
//        System.out.println("gstHours = " + gstHours);
        
        int minute = (int)Math.floor(gstHours * 60);
//        System.out.println("minute = " + minute);
        
        gstHours -= (minute / 60.0);
//        System.out.println("gstHours = " + gstHours);
        
        int second = (int)Math.floor(gstHours * 60 * 60);
//        System.out.println("second = " + second);
        
        return LocalTime.of(hour, minute, second);
    }
    
    public static double getDecimalCoordinate(int degrees, int minutes, int seconds, String direction) throws Exception
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
        
        if (!(direction.equalsIgnoreCase("East") 
                || direction.equalsIgnoreCase("West") 
                || direction.equalsIgnoreCase("North") 
                || direction.equalsIgnoreCase("South")))
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

        double seconds = hours * 60 * 60;
        
        return Duration.ofSeconds((int)Math.floor(seconds));
    }
        
    // Assuming we will only allow whole second values, we can easily shange this to double the precision is needed
    // TODO: Verify accuracy
    public static LocalTime getLocalSiderealTime(int degreesLong, 
                                                 int minutesLong, 
                                                 int secondsLong, 
                                                 String directionLong,
                                                 LocalDateTime dateTime) throws Exception
    {
        // Convert H M S to decimal coordinate value
        double longitude = getDecimalCoordinate(degreesLong, minutesLong, secondsLong, directionLong);
//        System.out.println("longitude for " + degrees + " " + minutes + " " + seconds + " " + direction + " = " + longitude);
        
        // Divide the result by 15.  Use 15.0 to force decimal precision if the first value is a whole number
        double hourOffset = longitude / 15.0;
        
        Duration timeOffset = getDurationFromDecimalHours(hourOffset);
        System.out.println("Offset of " + hourOffset + " hours = " + timeOffset);
        
        LocalTime greenwichSiderealTime = getGreenwichSiderealTime(dateTime);
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
     * @throws java.lang.Exception
     */
    public static Map<String, Double> getAzimuthAndElevation(double rightAscention, double declination, double latitude, LocalTime localSiderealTime) throws Exception
    {
        if (rightAscention < 0 || rightAscention > 24)
        {
            throw new Exception("Invalid value of " + rightAscention + " for rightAscention passed into Calculation.getAzimuthAndElevation");
        }
        
        if (declination < -90 || declination > 90)
        {
            throw new Exception("Invalid value of " + declination + " for declination passed into Calculation.getAzimuthAndElevation");
        }
        
        // Convert LST to decimal hours
        double decimalHours = localSiderealTime.getHour() + (localSiderealTime.getMinute() / 60) + (localSiderealTime.getSecond() / (60 * 60));
        
        // Compute object hours angle      
        double hourAngle = decimalHours - rightAscention;
        
        // Convert hours to degrees
        double hourAngleDegrees = hourAngle * 15;
        
        // convert degrees to radians
        double hourAngleRadians = hourAngleDegrees * Math.PI / 180;
        double declinationRadians = declination * Math.PI / 180;
        double latitudeRadians = latitude * Math.PI / 180;
        
        // Compute Azimuth in radians
        double azimuth = Math.atan((-1 * Math.sin(hourAngleRadians) * Math.cos(declinationRadians)) / ((Math.cos(latitudeRadians) * Math.sin(declinationRadians)) - (Math.sin(latitudeRadians) * Math.cos(declinationRadians) * Math.cos(hourAngleRadians))));
        
        // Compute Elevation in radians  
        double elevation = Math.asin((Math.sin(latitudeRadians) * Math.sin(declinationRadians)) + (Math.cos(latitudeRadians) * Math.cos(declinationRadians) * Math.cos(hourAngleRadians)));
        
        double azimuthDegrees = azimuth * (180 / Math.PI);
        double elevationDegrees = elevation * (180 / Math.PI); 
        
        if (azimuthDegrees < 0)
        {
            azimuthDegrees += 360;
        }
        
        if (azimuthDegrees > 360)
        {
            azimuthDegrees -= 360;
        }
        
        System.out.println("hourAngleDegrees = " + hourAngleDegrees);
        System.out.println("azimuthDegrees = " + azimuthDegrees);
        System.out.println("elevationDegrees = " + elevationDegrees);     
        
        Map<String, Double> map = new HashMap<>();
        map.put("Azimuth", azimuthDegrees);
        map.put("Elevation", elevationDegrees);
        
        return map;
    }
}
