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
 * OBJECT: Moon
 * 
 * DESCRIPTION: This is a child class of CelestialObject that calculates the
 *              moon phase and position based on given Julian date, latitude, 
 *              longitude, date and time.
 * 
 * DATE: 03-12-2019
 * 
 * @author Dina Brown and Patrick Sewell
 */
public class Moon extends CelestialBody
{
    
    /**
     *  GLOBAL VARIABLES
     */
    
    public double rightAscension;
    public double declination;
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
     * METHOD: moon_getIntermediateValues()
     * 
     * DESCRIPTION: gets the calculated values for the moon
     * 
     * @param greenwichDateTime
     * 
    ***************************************************************************/
    public void moon_getIntermediateValues(LocalDateTime greenwichDateTime)
    {
        // START MOON PHASE CALCULATIONS - PS
        // Reference: https://www.subsystems.us/uploads/9/8/9/4/98948044/moonphase.pdf
        double newMoonJulianDate = 2451549.5;
        double periodLength = 29.53;
        
        double greenwichJulianDate = Calculation.getJulianDate(greenwichDateTime);
        
        double dateDifference = greenwichJulianDate - newMoonJulianDate;
        
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
        
        // START MOON POSITION CALCULCATIONS
        
        // Reference: https://aa.quae.nl/en/reken/hemelpositie.html
        double baselineJulianDate = Calculation.getJulianDate(LocalDateTime.of(2000, 1, 1, 12, 0));
        
        dateDifference = greenwichJulianDate - baselineJulianDate;
        
//        System.out.println("greenwichJulianDate = " + greenwichJulianDate);
//        System.out.println("baselineJulianDate = " + baselineJulianDate);
//        System.out.println("dateDifference = " + dateDifference);
        
        double l = (218.316 + (13.176396 * dateDifference)) % 360.0;
        double m = (134.963 + (13.064993 * dateDifference)) % 360.0;
        double f = (93.272 + (13.229350 * dateDifference)) % 360.0;
        
//        System.out.println("l = " + l);
//        System.out.println("m = " + m);
//        System.out.println("f = " + f);
        
        double longitude = l + (6.289 * Math.sin(Calculation.getRadiansFromDegrees(m)));
        double latitude = 5.128 * Math.sin(Calculation.getRadiansFromDegrees(f));
        
//        System.out.println("longitude = " + longitude);
//        System.out.println("latitude = " + latitude);
//        System.out.println("distance = " + distance);

        final double epsilon = 23.4397;
        
        double latitideRadians = Calculation.getRadiansFromDegrees(latitude);
        double longitudeRadians = Calculation.getRadiansFromDegrees(longitude);
        double epsilonRadians = Calculation.getRadiansFromDegrees(epsilon);
        
        double rightAscensionRadians = Math.atan2((Math.sin(longitudeRadians) * Math.cos(epsilonRadians)) - (Math.tan(latitideRadians) * Math.sin(epsilonRadians)), Math.cos(longitudeRadians));
        double rightAscensionDegrees = Calculation.getDegreesFromRadians(rightAscensionRadians);
        
        if (rightAscensionDegrees < 0)
        {
            rightAscensionDegrees += 360;
        }
        
        rightAscension = rightAscensionDegrees / 15.0;
                                
        double declinationRadians = Math.asin((Math.sin(latitideRadians) * Math.cos(epsilonRadians)) 
                + (Math.cos(latitideRadians) * Math.sin(epsilonRadians) * Math.sin(longitudeRadians))); 
        declination = Calculation.getDegreesFromRadians(declinationRadians);
        
        //System.out.println("rightAscension in hours = " + rightAscension);
        //System.out.println("declination = " + declination);
        
        // END MOON POSITION CALCULCATIONS
    } // End moon_getIntermediateValues()
   
    @Override
    /**************************************************************************
     *
     * METHOD: calculateHorizonCoordinates()
     * 
     * DESCRIPTION: gets the coordinates and time information that will be used
     *              to plot Moon.
     * 
     * @param latitude the north/south direction of the surface of the earth.
     * @param longitude the east/west direction of the surface of the earth.
     * @param greenwichSiderealTime
     * 
    ***************************************************************************/
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime) throws Exception
    {
        if (rightAscension < 0 || rightAscension > 24)
        {
            throw new Exception("Invalid value of " + rightAscension + " for rightAscension passed into Moon.calculateHorizonCoordinates");
        }        
        if (declination < -90 || declination > 90)
        {
            throw new Exception("Invalid value of " + declination + " for declination passed into Moon.calculateHorizonCoordinates");
        }
        
        double decimalHours = greenwichSiderealTime.getHour() + (greenwichSiderealTime.getMinute() / 60.0) + (greenwichSiderealTime.getSecond() / (60.0 * 60));
        
        // Longitude passed is negative if west of Greenwich and will be subtracted in this case
        double hourAngleDegrees = (decimalHours - rightAscension) * 15 + longitude;        
                
        double hourAngleRadians = Calculation.getRadiansFromDegrees(hourAngleDegrees);
        double declinationRadians = Calculation.getRadiansFromDegrees(declination);
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
