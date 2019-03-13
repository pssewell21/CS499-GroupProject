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
public class Moon extends CelestialObject
{
    public double moonMeanLongitude;
    public double sunMeanAnomaly;
    public double moonMeanAnomaly;
    public double moonMeanElongation;
    public double moonMeanDistance;
    public double eccentricity;
        
    public Moon()
    {
        name = "Moon";
    }
    
    public void getIntermediateValues(double julianDate)
    {
        moonMeanLongitude = 270.434164 + (481267.8831 * julianDate);
        sunMeanAnomaly = 358.475833 + (35999.0498 * julianDate);
        moonMeanAnomaly = 296.104608 + (477198.8491 * julianDate);
        moonMeanElongation = 350.737486 + (445267.1142 * julianDate);
        moonMeanDistance = 11.250889 + (483202.0251 * julianDate);
        
        eccentricity = 1 - (0.002495 * julianDate) + (0.00000752 * julianDate * julianDate);
    }
    
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime)
    {
        
    }   
}
