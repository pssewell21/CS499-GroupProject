/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap;

import java.time.LocalDateTime;
import java.time.LocalTime;

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
        LocalTime baselineGstTime = LocalTime.of(6, 39, 58);        
        
        LocalDateTime currentDate = LocalDateTime.now();
        int currentDayOfYear = currentDate.getDayOfYear();
        int dayDifference = currentDayOfYear - 1;
                
        int currentHourOfDay = currentDate.getHour();
        
        //System.out.println("dayDifference = " + dayDifference);
        //System.out.println("dayDifference * dayMultiplier = " + dayDifference * dayMultiplier);
        //System.out.println("currentHourOfDay = " + currentHourOfDay);
        //System.out.println("currentHourOfDay * hourMultiplier = " + currentHourOfDay * hourMultiplier);
        
        // Add the multiplied seconds values to the baseline time to get the GST - PS
        LocalTime greenwichSiderealTime = baselineGstTime.plusSeconds(dayDifference * dayMultiplier).plusSeconds(currentHourOfDay * hourMultiplier);
        
        //System.out.println("currentTime = " + currentDate);
        //System.out.println("greenwichSiderealTime = " + greenwichSiderealTime);
        
        return greenwichSiderealTime;
    }
    
    public static LocalTime getLocalSiderealTime(int degrees, int minutes, int seconds, String direction) throws Exception
    {
        if (!(direction.equals("East") || direction.equals("West")))
        {
            throw new Exception("Invalid direction value was passed to Calculation.getLocalSiderealTime");
        }
        
        
        return getGreenwichSiderealTime();
    }
}
