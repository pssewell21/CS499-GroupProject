/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.time.LocalDateTime;
import java.time.LocalTime;
import starmap.Calculation;

/**
 * DESCRIPTION: This is a child class of CelestialObject that calculates the
 *              moon phase and position based on given Julian date, latitude, 
 *              longitude, date and time.
 * 
 * DATE: 03-12-2019
 * 
 * @author Dina Brown and Patrick Sewell
 */
public class Moon extends CelestialObject
{
    
    /**
     *  GLOBAL VARIABLES
     */
    public final static double RADS = Math.PI / 180.0;
    
    public double g_JD;
    public int g_year;
    public double g_calander_year;
    public double g_calander_month;
    public double g_calander_day;
    public double g_rightAscension;
    public double g_declination;
    public String phase;
    
     /**************************************************************************
     *
     * DESCRIPTION: Constructor for class Moon
     * 
    ***************************************************************************/
    public Moon()
    {
        name = "Moon";
    } // End Moon()
    
     /**************************************************************************
     * 
     * METHOD: getIntermediateValues()
     * 
     * DESCRIPTION: gets the calculated moon phases, right ascension and declination
     * 
     * @param dateTime access the current year
     * @param latitude
     * @param longitude
     * 
    ***************************************************************************/
    public void moon_getIntermediateValues(double latitude, double longitude, LocalDateTime dateTime)
    {
        // TODO: Remove julian date argument from method signature - PS
        
        // START MOON PHASE CALCULATIONS - PS
        // Reference: https://www.subsystems.us/uploads/9/8/9/4/98948044/moonphase.pdf
        double newMoonJulianDate = 2451549.5;
        double periodLength = 29.53;
        
        double julianDate = Calculation.getJulianDate(dateTime);
        
        double dateDifference = julianDate - newMoonJulianDate;
        
        double numNewMoons = dateDifference / periodLength;
        
        //System.out.println("Number of new moons since " + newMoonJulianDate + " = " + numNewMoons);
        
        double amountOfCycleElapsed = numNewMoons - (int)Math.floor(numNewMoons);
        
        //System.out.println("Amount of cycle elapsed " + amountOfCycleElapsed); 
        
        if (amountOfCycleElapsed > 0.98 || amountOfCycleElapsed <= 0.02)
        {
            phase = "new";
        }
        else if (amountOfCycleElapsed > 0.02 && amountOfCycleElapsed <= 0.23)
        {
            phase = "waxing-crescent";
        }
        else if (amountOfCycleElapsed > 0.23 && amountOfCycleElapsed <= 0.27)
        {
            phase = "first-quarter";
        }
        else if (amountOfCycleElapsed > 0.27 && amountOfCycleElapsed <= 0.48)
        {
            phase = "waxing-gibbous";
        }
        else if (amountOfCycleElapsed > 0.48 && amountOfCycleElapsed <= 0.52)
        {
            phase = "full";
        }
        else if (amountOfCycleElapsed > 0.52 && amountOfCycleElapsed <= 0.73)
        {
            phase = "waning-gibbous";
        }
        else if (amountOfCycleElapsed > 0.73 && amountOfCycleElapsed <= 0.77)
        {
            phase = "last-quarter";
        }
        else if (amountOfCycleElapsed > 0.77 && amountOfCycleElapsed <= 0.98)
        {
            phase = "waning-crescent";
        }
        else
        {
            phase = "ERROR OCCURRED";
        }
        // END MOON PHASE CALCULATIONS
        
 
        //Lunar Location calculation:
        //Code used from http://www.geoastro.de/elevazmoon/basics/index.htm
        
        double T, eps;
        double X, Y, Z, R;
        
        T = (julianDate - 2451545.0) / 36525.0; // JD - J2000
        
        eps = 23.0 + (26.0/60.0) + (21.448/3600.0) - (46.8150*T+ 0.00059*T*T
                                                        - 0.001813*T*T*T)/3600;
        
        X = Math.cos(latitude)*Math.cos(longitude);
        Y = Math.cos(eps)*Math.cos(latitude)* Math.sin(longitude) 
                                            - Math.sin(eps)*Math.sin(latitude);
        Z = Math.sin(eps)*Math.cos(latitude)* Math.sin(longitude) 
                                            + Math.cos(eps)*Math.sin(latitude);
        R = Math.sqrt(1-Z*Z);
        
        //RA is in hrs, DEC is in degrees
        
        double arc_tan = Math.atan(Y/(X + R));
        
        if(arc_tan < 0)
            arc_tan = arc_tan + Math.PI;
        
        g_rightAscension = (24 / Math.PI) * arc_tan; // in hours
        g_declination = (180/Math.PI) * Math.asin(Math.sin(eps)* Math.cos(latitude)
                                            * Math.sin(longitude) + Math.cos(eps)
                                            * Math.sin(latitude)); // in degrees;
        
        System.out.println("Moon class: RA: " + g_rightAscension);
        System.out.println("Moon class: DEC: " + g_declination + "\n");
         
    } // End getIntermediateValues()
   
    @Override
    /**************************************************************************
     *
     * METHOD: calculateHorizonCoordinates()
     * 
     * DESCRIPTION: gets the coordinates and time information that will be used
     *              to plot Moon.
     * 
     * @param latitude
     * @param longitude
     * @param greenwichSiderealTime
     * 
    ***************************************************************************/
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime) throws Exception
    {

        if (g_rightAscension < 0 || g_rightAscension > 24)
        {
            throw new Exception("Invalid value of " + g_rightAscension + " for rightAscension passed into Star.calculateHorizonCoordinates");
        }
        

        if (g_declination < -90 || g_declination > 90)
        {
            throw new Exception("Invalid value of " + g_declination + " for declination passed into Star.calculateHorizonCoordinates");
        }
        
        double decimalHours = greenwichSiderealTime.getHour() + (greenwichSiderealTime.getMinute() / 60.0) + (greenwichSiderealTime.getSecond() / (60.0 * 60));
        
        // Longitude passed is negative if west of Greenwich and will be subtracted in this case
        double hourAngleDegrees = (decimalHours - g_rightAscension) * 15 + longitude;        
        
        
        double hourAngleRadians = Calculation.getRadiansFromDegrees(hourAngleDegrees);
        double declinationRadians = Calculation.getRadiansFromDegrees(g_declination);
        double latitudeRadians = Calculation.getRadiansFromDegrees(latitude);
        
        double elevationRadians = Math.asin(
                (Math.cos(hourAngleRadians) * Math.cos(declinationRadians) * Math.cos(latitudeRadians))
                + (Math.sin(declinationRadians) * Math.sin(latitudeRadians)));
        double azimuthRadians = Math.atan2(
                -1 * Math.sin(hourAngleRadians),
                (Math.tan(declinationRadians) * Math.cos(latitudeRadians))
                        - (Math.sin(latitudeRadians) * Math.cos(hourAngleRadians)));  
        
        double azimuthDegrees = Calculation.getDegreesFromRadians(azimuthRadians);
        double elevationDegrees = Calculation.getDegreesFromRadians(elevationRadians);
         
        
        if (azimuthDegrees < 0)
        {
            azimuthDegrees += 360;
        }
        
        if (azimuthDegrees > 360)
        {
            azimuthDegrees -= 360;
        }

        azimuth = azimuthDegrees;
        elevation = elevationDegrees;
        
    } // End calculateHorizonCoordinates()
} // End Moon Class