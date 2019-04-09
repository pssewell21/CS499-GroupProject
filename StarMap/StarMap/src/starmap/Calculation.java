/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        
        double julianDate = baselineJulianDate + duration.toDays();
        
//        System.out.println("julianDate = " + julianDate);
        
        double decimalHours = Calculation.getDecimalHours(dateTime.getHour(), 
                dateTime.getMinute(), dateTime.getSecond());
        
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
  
    public static LocalTime getGreenwichSiderealTime(LocalDateTime dateTime)
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
    
    public static double getDecimalCoordinate(int degrees, int minutes, double seconds, String direction) throws Exception
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
        
        if (seconds < 0 || seconds > 59.999999)
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
        
        if (direction.equalsIgnoreCase("West") || direction.equalsIgnoreCase("South"))
        {
            longitude *= -1;
        }
        
        return longitude;
    }
    
    public static double getDecimalHours(int hour, int minute, int second)
    {
        double decimalHours = hour + (minute / 60.0) + (second / (60.0 * 60));
        
        return decimalHours;
    }
    
    public static double getRadiansFromDegrees(double degrees)
    {
        return Math.toRadians(degrees);
    }
    
    public static double getDegreesFromRadians(double radians)
    {
        return Math.toDegrees(radians);
    }
}
