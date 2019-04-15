/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.time.LocalTime;
import starmap.Calculation;

/**
 * OBJECT: Star
 * 
 * DESCRIPTION: This is a child class of CelestialObject that creates Star objects
 *              on an image grid based on right ascension, declination, distance,
 *              and magnitude.
 * 
 * DATE: 02-15-2019
 * 
 * @author pssewell21 (code) and Dina Brown (javadoc comments)
 */
public class Star extends CelestialBody
{
    /**
    *  GLOBAL VARIABLES
    */
    public double rightAscension;
    public double declination;
    public double distance;
    public double magnitude;
     
    /**************************************************************************
     *
     * DESCRIPTION: Constructor for class Star. Builds the Star object based on
     *              given parameters.
     * 
     * @param name this is the name of the star
     * @param rightAscension the east/west direction in the sky
     * @param declination the north/south direction in the sky
     * @param distance
     * @param magnitude the brightness of star object
     * 
    ***************************************************************************/
    public Star(String name, double rightAscension, double declination, double distance, double magnitude)
    {
        this.name = name;
        this.rightAscension = rightAscension;
        this.declination = declination;
        this.distance = distance;
        this.magnitude = magnitude;
    } // End Star()      
            
    @Override
     /**************************************************************************
     *
     * METHOD: calculateHorizonCoordinates()
     * 
     * DESCRIPTION: gets the coordinates and time information that will be used
     *              to plot Stars. The pseudocode for this function was provided 
     *              by Dr. Rick Coleman (UAH).
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
            throw new Exception("Invalid value of " + rightAscension + " for rightAscension passed into Star.calculateHorizonCoordinates");
        }
        
        if (declination < -90 || declination > 90)
        {
            throw new Exception("Invalid value of " + declination + " for declination passed into Star.calculateHorizonCoordinates");
        }
        
        double greenwichSiderealTimeDecimalHours = greenwichSiderealTime.getHour() + (greenwichSiderealTime.getMinute() / 60.0) + (greenwichSiderealTime.getSecond() / (60.0 * 60));
        
        // Longitude passed is negative if west of Greenwich and will be subtracted in this case
        double hourAngleDegrees = (greenwichSiderealTimeDecimalHours - rightAscension) * 15 + longitude;            
        
//        System.out.println("hourAngleDegrees = " + hourAngleDegrees);
        
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
        
//        System.out.println("azimuthDegrees = " + azimuthDegrees);
//        System.out.println("elevationDegrees = " + elevationDegrees);  
        
        if (azimuthDegrees < 0)
        {
            azimuthDegrees += 360;
        }
        
        if (azimuthDegrees > 360)
        {
            azimuthDegrees -= 360;
        }
        
//        System.out.println("hourAngleDegrees = " + hourAngleDegrees);

        azimuth = azimuthDegrees;
        elevation = elevationDegrees;
    } // End calculateHorizonCoordinates()
} // End Star Class
