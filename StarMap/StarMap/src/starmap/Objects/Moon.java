/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.lang.Math;
import java.text.DecimalFormat;
import java.time.LocalTime;
import starmap.Calculation;

/**
 *
 * @author pssewell21
 */
public class Moon extends CelestialObject
{
    public final static double RADS = Math.PI / 180.0;
    public double rightAscension;
    public double declination;            
    
    public double L_angle;
    public double M_angle;
    public double Mp_angle;
    public double D_angle;
    public double F_angle;
    public double ecc;
    
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
    public void moon_getIntermediateValues(double julianDate)
    {
        // Lunar phases calculation data goes here:
        double new_moon, first_quarter, full_moon, last_quarter;
        double t_lunar, k_lunar;
        
        //year = getYear();
        double year = 2019.087;
        double JD;
        
        k_lunar = (int) ((year - 1900.0) * 12.3685);
        t_lunar = k_lunar / 1236.85;
        
        //julianDate = (k_lunar + 0.25);
        
        //Julian Day of a given phase:
        JD = 2415020.75933 + (29.53058868 * k_lunar) + (0.0001178 * Math.pow(t_lunar, 2))
                + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
                - ((0.009173 * RADS) * Math.pow(t_lunar, 2))))); 
//        
//        // New moon calculations
//        new_moon = Math.ceil(JD); //Next new moon
//        new_moon = Math.floor(JD);
//        
//        System.out.print("******************************");
//        System.out.println("\nLunar Phases calculations:");
//        System.out.println("******************************");
//        System.out.println("Julian Date = " + julianDate);
//        System.out.println("JD (moon) = " + JD);
//        System.out.println("new moon = " + new_moon);
        
        //Lunar Location calculation:
        DecimalFormat df = new DecimalFormat("#.######");
        DecimalFormat df_two = new DecimalFormat("#.##########");
        julianDate = 2444214.5;
        double t = (julianDate - 2415020.0) / 36525; //43549.4361111
        
        System.out.print("\n******************************");
        System.out.println("\nLunar Location calculations:");
        System.out.println("******************************");
        System.out.println("t = " + Math.round(t));  
        System.out.println("julian day " + julianDate);
        
        //Angles:
        L_angle = 270.434164 + (481267.8831 * t);
        M_angle = 358.475833 + (35999.0498 * t);
        Mp_angle = 296.104608 + (477198.8491 * t);
        D_angle = 350.737486 + (445267.1142 * t);
        F_angle = 11.250889 + (483202.0251 * t);
        ecc = 1 - (0.002495 * t) - (0.00000752 * t * t);
        
        
        
        System.out.println("moonMeanLongitude (L')  = " + L_angle);
        System.out.println("sunMeanAnomaly (M)     = " + M_angle);
        System.out.println("moonMeanAnomaly (M')    = " + Mp_angle);
        System.out.println("moonMeanElongation (D)  = " + D_angle);
        System.out.println("moonMeanDistance (F)   = " + F_angle);
        System.out.println("eccentricity       = " + df.format(ecc));
        
        // Angles converted to Radians
        
        double L_RADS = Math.toRadians(L_angle); // L'
        double M_RADS = Math.toRadians(M_angle); // M
        double Mp_RADS = Math.toRadians(Mp_angle); //M'
        double D_RADS = Math.toRadians(D_angle); // D - angular distance east of the Sun at any time.
        double F_RADS = Math.toRadians(F_angle); // F
        
        System.out.println("\nLunar Angles after RADS:");
        System.out.println("L' = " + L_RADS);
        System.out.println("M = " + M_RADS);
        System.out.println("M' = " + Mp_RADS);
        System.out.println("D = " + D_RADS);
        System.out.println("F = " + F_RADS + "\n");
        //double geocentricLongitudeRadians = 
        
        // Calculates the Moon's geocentric latitude and longitude
        double geocentricLongitudeRadians = L_angle 
                + (6.288750 * Math.sin(Mp_angle))
                + (1.274018 * Math.sin((2 * D_angle) - Mp_angle))
                + (0.658309 * Math.sin(2 * D_angle))
                + (0.213616 * Math.sin(2 * Mp_angle))
                - (0.185596 * Math.sin(M_angle) * ecc)
                - (0.114336 * Math.sin(2 * F_angle))
                + (0.058793 * Math.sin((2 * D_angle) - (2 * Mp_angle)))
                + (0.057212 * Math.sin((2 * F_angle) - M_angle - Mp_angle) * ecc)
                + (0.053320 * Math.sin((2 * F_angle) + Mp_angle))
                + (0.045874 * Math.sin((2 * F_angle) - M_angle) * ecc);
        
//        double geoentricLatitudeRadians = (5.128189 * Math.sin(moonMeanDistanceRadians))
//                + (0.280606 * Math.sin(moonMeanAnomalyRadians + moonMeanDistanceRadians))
//                + (0.277693 * Math.sin(moonMeanAnomalyRadians - moonMeanDistanceRadians))
//                + (0.173238 * Math.sin((2 * moonMeanElongationRadians) - moonMeanDistanceRadians))
//                + (0.055413 * Math.sin((2 * moonMeanElongationRadians) + moonMeanDistanceRadians - moonMeanAnomalyRadians))
//                + (0.046272 * Math.sin((2 * moonMeanElongationRadians) - moonMeanDistanceRadians - moonMeanAnomalyRadians))
//                + (0.032573 * Math.sin((2 * moonMeanElongationRadians) + moonMeanDistanceRadians))
//                + (0.017198 * Math.sin((2 * moonMeanAnomalyRadians) + moonMeanDistanceRadians))
//                + (0.009267 * Math.sin((2 * moonMeanElongationRadians) + moonMeanAnomalyRadians - moonMeanDistanceRadians))
//                + (0.008823 * Math.sin((2 * moonMeanAnomalyRadians) - moonMeanDistanceRadians));
        
        double geocentricLongitude = Calculation.getDegreesFromRadians(geocentricLongitudeRadians);
        //double geoentricLatitude = Calculation.getDegreesFromRadians(geoentricLatitudeRadians);
        
        System.out.println("\nGEO LAT and LON:");
        System.out.println("geocentricLongitude = " + geocentricLongitude + "\n");
//        System.out.println("geoentricLatitude = " + geoentricLatitude);
//        System.out.println("geocentricLongitude = " + geocentricLongitude);
//        System.out.println("Right Ascension (before): " + rightAscension);
        
        //Calculte the Right Ascension for Moon
        if (geocentricLongitude < 0)
        {
            rightAscension = (geocentricLongitude + 360) / 15;
        }
        else
        {
            rightAscension = geocentricLongitude / 15;
        }        
        
        //declination = geoentricLatitude;    
        
        System.out.println("rightAscension = " + rightAscension);
        System.out.println("declination = " + declination);
    } // End getIntermediate()
    
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
