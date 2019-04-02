/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.time.LocalTime;
import starmap.Calculation;

/**
 *
 * @author pssewell21
 */
public class Moon extends CelestialObject
{
    public double rightAscension;
    public double declination;            
    
    public double moonMeanLongitude;
    public double sunMeanAnomaly;
    public double moonMeanAnomaly;
    public double moonMeanElongation;
    public double moonMeanDistance;
    public double eccentricity;
    
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
     * DESCRIPTION: gets the calculated values for each planet
     * @param julianDate 
     * //@return calculated values
     * 
    ***************************************************************************/
    public void getIntermediateValues(double julianDate)
    {
        // Lunar phases calculation data goes here:
        //??
        
        
        //Lunar Location calculation:
        double t = (julianDate - 2415020.0) / 36525; //43549.4361111
        System.out.print("******************************");
        System.out.println("\nLunar Location calculations:");
        System.out.println("******************************");
        System.out.println("t = " + t);        
        
        //Angles:
        moonMeanLongitude = 270.434164 + (481267.8831 * t);
        sunMeanAnomaly = 358.475833 + (35999.0498 * t);
        moonMeanAnomaly = 296.104608 + (477198.8491 * t);
        moonMeanElongation = 350.737486 + (445267.1142 * t);
        moonMeanDistance = 11.250889 + (483202.0251 * t);
        eccentricity = 1 - (0.002495 * t) - (0.00000752 * t * t);
        
        System.out.println("moonMeanLongitude  = " + moonMeanLongitude);
        System.out.println("sunMeanAnomaly     = " + sunMeanAnomaly);
        System.out.println("moonMeanAnomaly    = " + moonMeanAnomaly);
        System.out.println("moonMeanElongation = " + moonMeanElongation);
        System.out.println("moonMeanDistance   = " + moonMeanDistance);
        System.out.println("eccentricity       = " + eccentricity);
        
        // Angles converted to Radians
        // L'
        double moonMeanLongitudeRadians = Calculation.getRadiansFromDegrees(moonMeanLongitude);
        // M
        double sunMeanAnomalyRadians = Calculation.getRadiansFromDegrees(sunMeanAnomaly);
        // M'
        double moonMeanAnomalyRadians = Calculation.getRadiansFromDegrees(moonMeanAnomaly);
        // D - angular distance east of the Sun at any time.
        double moonMeanElongationRadians = Calculation.getRadiansFromDegrees(moonMeanElongation);
        // F
        double moonMeanDistanceRadians = Calculation.getRadiansFromDegrees(moonMeanDistance);
        
        // Calculates the Moon's geocentric latitude and longitude
        double geocentricLongitudeRadians = moonMeanLongitudeRadians 
                + (6.288750 * Math.sin(moonMeanAnomalyRadians))
                + (1.274018 * Math.sin((2 * moonMeanElongationRadians) - moonMeanAnomalyRadians))
                + (0.658309 * Math.sin(2 * moonMeanElongationRadians))
                + (0.213616 * Math.sin(2 * moonMeanAnomalyRadians))
                - (0.185596 * Math.sin(sunMeanAnomalyRadians) * eccentricity)
                - (0.114336 * Math.sin(2 * moonMeanDistanceRadians))
                + (0.058793 * Math.sin((2 * moonMeanElongationRadians) - (2 * moonMeanAnomalyRadians)))
                + (0.057212 * Math.sin((2 * moonMeanElongationRadians) - sunMeanAnomalyRadians - moonMeanAnomalyRadians) * eccentricity)
                + (0.053320 * Math.sin((2 * moonMeanElongationRadians) + moonMeanAnomalyRadians))
                + (0.045874 * Math.sin((2 * moonMeanElongationRadians) - sunMeanAnomalyRadians) * eccentricity);
        
        double geoentricLatitudeRadians = (5.128189 * Math.sin(moonMeanDistanceRadians))
                + (0.280606 * Math.sin(moonMeanAnomalyRadians + moonMeanDistanceRadians))
                + (0.277693 * Math.sin(moonMeanAnomalyRadians - moonMeanDistanceRadians))
                + (0.173238 * Math.sin((2 * moonMeanElongationRadians) - moonMeanDistanceRadians))
                + (0.055413 * Math.sin((2 * moonMeanElongationRadians) + moonMeanDistanceRadians - moonMeanAnomalyRadians))
                + (0.046272 * Math.sin((2 * moonMeanElongationRadians) - moonMeanDistanceRadians - moonMeanAnomalyRadians))
                + (0.032573 * Math.sin((2 * moonMeanElongationRadians) + moonMeanDistanceRadians))
                + (0.017198 * Math.sin((2 * moonMeanAnomalyRadians) + moonMeanDistanceRadians))
                + (0.009267 * Math.sin((2 * moonMeanElongationRadians) + moonMeanAnomalyRadians - moonMeanDistanceRadians))
                + (0.008823 * Math.sin((2 * moonMeanAnomalyRadians) - moonMeanDistanceRadians));
        
        double geocentricLongitude = Calculation.getDegreesFromRadians(geocentricLongitudeRadians);
        double geoentricLatitude = Calculation.getDegreesFromRadians(geoentricLatitudeRadians);
        
        System.out.println("\nGEO LAT and LON:");
        System.out.println("geoentricLatitude = " + geoentricLatitude);
        System.out.println("geocentricLongitude = " + geocentricLongitude);
        System.out.println("Right Ascension (before): " + rightAscension);
        
        //Calculte the Right Ascension for Moon
        if (geocentricLongitude < 0)
        {
            rightAscension = (geocentricLongitude + 360) / 15;
        }
        else
        {
            rightAscension = geocentricLongitude / 15;
        }        
        
        declination = geoentricLatitude;    
        
        System.out.println("rightAscension = " + rightAscension);
        System.out.println("declination = " + declination);
    } // End getIntermediate()
    
    @Override
    /**************************************************************************
     *
     * METHOD: calculateHorizonCoordinates()
     * 
     * DESCRIPTION: gets the calculated values for each planet
     * 
     * @param latitude
     * @param longitude
     * @param greenwichSiderealTime
     * 
    ***************************************************************************/
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime) throws Exception
    {

//        if (rightAscension < 0 || rightAscension > 24)
//        {
//            throw new Exception("Invalid value of " + rightAscension + " for rightAscension passed into Star.calculateHorizonCoordinates");
//        }
        
        System.out.println("HorCords: rightAscension = " + rightAscension); //38297.97343336875
        System.out.println("HorzCords: Declination = " + declination);
//        if (declination < -90 || declination > 90)
//        {
//            throw new Exception("Invalid value of " + declination + " for declination passed into Star.calculateHorizonCoordinates");
//        }

//        if (rightAscension < 0 || rightAscension > 24)
//        {
//            throw new Exception("Invalid value of " + rightAscension + " for rightAscension passed into Moon.calculateHorizonCoordinates");
//        }
//        
//        if (declination < -90 || declination > 90)
//        {
//            throw new Exception("Invalid value of " + declination + " for declination passed into Moon.calculateHorizonCoordinates");
//        }

        
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
    }
}
