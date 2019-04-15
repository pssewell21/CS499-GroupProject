/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.time.LocalTime;
import starmap.Calculation;

/**
 * OBJECT: Constellation
 * 
 * DESCRIPTION: This is a child class of CelestialObject that creates Constellation
 *              objects on an image grid based on right ascension, declination,
 *              distance, and absolute magnitude.
 * 
 * DATE: 02-10-2019
 * 
 * @author pssewell21 (code) and Dina Brown (javadoc)
 */
public class Constellation extends CelestialBody
{
     /**
     *  GLOBAL VARIABLES
     */
    public double rightAscension;
    public double declination;
    public double distance;
    public double absoluteMagnitude;
    
     /**************************************************************************
     *
     * DESCRIPTION: Constructor for class Constellation
     * 
     * @param name this is the name of the Constellation
     * @param rightAscension the east/west direction in the sky
     * @param declination the north/south direction in the sky
     * 
    ***************************************************************************/
    public Constellation(String name, double rightAscension, double declination)
    {
        this.name = name;
        this.rightAscension = rightAscension;
        this.declination = declination;
    } // End Constellation()
    
     /**************************************************************************
     *
     * METHOD: calculateHorizonCoordinates()
     * 
     * DESCRIPTION: gets the coordinates and time information that will be used
     *              to plot Constellation object.
     * 
     * @param latitude the north/south direction of the surface of the earth.
     * @param longitude the east/west direction of the surface of the earth.
     * @param greenwichSiderealTime
     * 
     * @throws Exception
     * 
    ***************************************************************************/
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime) throws Exception
    {
        if (rightAscension < 0 || rightAscension > 24)
        {
            throw new Exception("Invalid value of " + rightAscension + " for rightAscension passed into Constellation.calculateHorizonCoordinates");
        }
        
        if (declination < -90 || declination > 90)
        {
            throw new Exception("Invalid value of " + declination + " for declination passed into Constellation.calculateHorizonCoordinates");
        }
        
        double decimalHours = greenwichSiderealTime.getHour() + (greenwichSiderealTime.getMinute() / 60.0) + (greenwichSiderealTime.getSecond() / (60.0 * 60));
        
        // Longitude passed is negative if west of Greenwich and will be subtracted in this case
        double hourAngleDegrees = (decimalHours - rightAscension) * 15 + longitude;        
        
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
} // End Constellation Class
