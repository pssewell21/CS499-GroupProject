/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.time.LocalTime;

/**
 *
 * @author pssewell21
 */
public class Constellation extends CelestialObject
{
    public double rightAscension;
    
    public double declination;
    
    public double distance;
    
    public double absoluteMagnitude;
            
    public Constellation(String name, double rightAscension, double declination)
    {
        this.name = name;
        this.rightAscension = rightAscension;
        this.declination = declination;
    }
    
    @Override
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
        
        double decimalHours = greenwichSiderealTime.getHour() + (greenwichSiderealTime.getMinute() / 60.0) + (greenwichSiderealTime.getSecond() / (60.0 * 60));
        
        // Longitude passed is negative if west of Greenwich and will be subtracted in this case
        double hourAngleDegrees = (decimalHours - rightAscension) * 15 + longitude;        
        
//        System.out.println("hourAngleDegrees = " + hourAngleDegrees);
        
        double hourAngleRadians = hourAngleDegrees * Math.PI / 180;
        double declinationRadians = declination * Math.PI / 180;
        double latitudeRadians = latitude * Math.PI / 180;
        
        double elevationRadisns = Math.asin(
                (Math.cos(hourAngleRadians) * Math.cos(declinationRadians) * Math.cos(latitudeRadians))
                + (Math.sin(declinationRadians) * Math.sin(latitudeRadians)));
        double azimuthRadians = Math.atan2(
                -1 * Math.sin(hourAngleRadians),
                (Math.tan(declinationRadians) * Math.cos(latitudeRadians))
                        - (Math.sin(latitudeRadians) * Math.cos(hourAngleRadians)));  
        double azimuthDegrees = azimuthRadians * (180 / Math.PI);
        double elevationDegrees = elevationRadisns * (180 / Math.PI); 
        
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
    } 
}
