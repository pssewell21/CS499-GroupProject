/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetsdriver;
import java.io.*;
import java.lang.Math;
import java.math.RoundingMode;
import java.util.Calendar;
import java.text.DecimalFormat;
//import java.lang.*;


/**
 *
 * @author dinabrown
 */
public class Planet {
    
    public final static double RADS = Math.PI / 180.0;
    public final static double DEGS = 180 / Math.PI;
    private String name;
    private double Lscal;
    private double Lprop;
    private double Ascal;
    private double Aprop;
    private double Escal;
    private double Eprop;
    private double Iscal;
    private double Iprop;
    private double Wscal;
    private double Wprop;
    private double Oscal;
    private double Oprop;
    
    
    /* Constructor */
    public Planet(String new_name, double new_lscal, double new_lprop, 
                                   double new_ascal, double new_aprop,
                                   double new_escal, double new_eprop,
                                   double new_iscal, double new_iprop,
                                   double new_wscal, double new_wprop,
                                   double new_oscal, double new_oprop
    )
    {
        name = new_name;
        Lscal = new_lscal;
        Lprop = new_lprop;
        Ascal = new_ascal;
        Aprop = new_aprop;
        Escal = new_escal;
        Eprop = new_eprop;
        Iscal = new_iscal;
        Iprop = new_iprop;
        Wscal = new_wscal;
        Wprop = new_wprop;
        Oscal = new_oscal;
        Oprop = new_oprop;
        
    }
    /**************************************************************************
     * 
     * METHOD: true_anomaly()
     * 
     * DESCRIPTION: calculates the true anomaly from the mean anomaly
     * @param e this is the Eccentricity for a given planet
     * @return calculated true anomaly
     * 
    ***************************************************************************/
    public double true_anomaly(double M, double e)
    {   
        double E, E1, V;
        
        E = M + e * Math.sin(M) * (1.0 + e * Math.cos(M));
        
        do{
            E1 = E;
            E = E1 - (E1 - e * Math.sin(E1) - M) / (1 - e * Math.cos(E1));
        } while(Math.abs(E - E1) > (0.000000000001)); //1.0e-12
        
        V = 2 * Math.atan(Math.sqrt((1 + e) / (1 - e)) * Math.tan(0.5 * E));
        
        if(V < 0) V = V + (2* Math.PI);

        return V;
        
    } // End True_Anomoly()
    
    /**************************************************************************
     * 
     * METHOD: mod2Pi()
     * 
     * DESCRIPTION: Takes an angle over 360 degrees and converts it to an
     *              equivalent angle on the interval [0,360]
     *              example: 450 degrees is 90 degrees on the interval [0,360]
     * 
     * @param X Angle that will be converted to a new value.
     * @return an angle in radians
    ***************************************************************************/
    public double mod2Pi(double X)
    {
        // Angle X is already in Radians when it's calculuated in this function:
        double A, B;
        
        B = X / (2 * Math.PI);
        A = (2 * Math.PI) * (B - Math.abs(Math.floor(B)));
        
        if(A < 0) A = (2 * Math.PI) + A;

        return A; //returns the value in Radians
        
    } // End mod2Pi()
    
    /***************************************************************************
     * METHOD: calculate()
     * 
     * DESCRIPTION: calculates all of the Planets orbital elements
     * @param new_value
     * 
    ***************************************************************************/
    public void calculate(int new_value)
    {
        double JD = 2458540; //2458534.5; //Julian Day
        double cy = JD/36525;
        
        /* Planet orbital elements */
        double meanLongitude;
        double semiMajorAxis;
        double eccentricityOfOrbit; 
        double inclination;
        double longitudeAscNode;
        double perihelion;
        
        /* Coordinates - Heliocentric, Geocentric, ecliptic, Equatorial */
        double xH, yH, zH;
        double xG, yG, zG;
        double ecl;
        double xEq, yEq, zEq;
        double rightAsc, declination, distance;
        
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        
        /* 
        *  cy = JD/36525 
        *  a = semimajor axis               --> a = Ascal + Aprop*cy
        *  e = eccentricity                 --> e = Escal + Eprop * cy
        *  i = inclination                  --> i = ( Iscal - Iprop * cy / 3600) * RADS
        *  ? = argument of perihelion       --> ? = (Wscal + Wprop * cy / 3600) * RADS
        *  Í = longitude of ascending node  --> Í = (Oscal - Oprop * cy / 3600) * RADS
        *  L = mean longitude of the planet --> L=Mod2Pi ((Lscal + Lprop * cy / 3600) * RADS)
        */
        
        String output;
        System.out.println("JD = " + JD);
        System.out.println("cy = " + cy);
        System.out.println("RADS = " + RADS);
        System.out.println();
                
        switch(new_value)
       {
            /* Mercury */
            case 1:
                meanLongitude = mod2Pi((Lscal + Lprop * cy / 3600) * RADS);
                semiMajorAxis = Ascal + Aprop * cy;
                eccentricityOfOrbit = Escal + Eprop * cy;
                inclination = ((Iscal - Iprop * cy) / 3600) * RADS;
                perihelion = (Wscal + Wprop * cy / 3600) * RADS;
                longitudeAscNode = ((Oscal - Oprop * cy) / 3600) * RADS;
                
                output = String.format("Planet name:              " + name +
                         "\nMean Longitude:           " + meanLongitude + "°" +
                         "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                         "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                         "\nInclination:              " + inclination + "°" +
                         "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                         "\nArgument of Perihelion:   " + perihelion + " °\n");
                System.out.println(output);
                break;
        /* Venus */
            case 2:
                meanLongitude = mod2Pi((Lscal + Lprop * cy / 3600) * RADS);
                semiMajorAxis = Ascal + Aprop * cy;
                eccentricityOfOrbit = Escal - Eprop * cy;
                inclination = ( Iscal - Iprop * cy / 3600) * RADS;
                perihelion = ( Wscal - Wprop * cy / 3600) * RADS;
                longitudeAscNode = (Oscal - Oprop * cy / 3600) * RADS;
                
                output = String.format("Planet name:              " + name +
                         "\nMean Longitude:           " + meanLongitude + "°" +
                         "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                         "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                         "\nInclination:              " + inclination + "°" +
                         "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                         "\nArgument of Perihelion:   " + perihelion + " °\n");
                System.out.println(output);
                break;
        /* Earth/Sun */
            case 3:
                meanLongitude = mod2Pi((Lscal + Lprop * cy / 3600) * RADS); // In Radians = 1.7429177410226784
                semiMajorAxis = Ascal - Aprop * cy;       //not in Radians
                eccentricityOfOrbit = Escal - Eprop * cy; //not in radians
                inclination = ( Iscal - Iprop * cy / 3600) * RADS;
                longitudeAscNode = (Oscal - Oprop * cy / 3600) * RADS;
                perihelion = (Wscal + Wprop * cy / 3600) * RADS;
                
                
                output = String.format("\nPlanet name:                              " + name +
                         "\n1) Mean Longitude (in Degrees):           " + Math.toDegrees(meanLongitude) + "°" +
                         "\n2) Mean Longitude (in Radians):           " + meanLongitude + "\n" +
                         "\n3) Semi-major Axis:                       " + semiMajorAxis + " AU" + "\n" +
                         "\n4) Eccentricity Of Orbit:                 " + eccentricityOfOrbit +
                         "\n5) Eccentricity Of Orbit (in Radians):    " + Math.toRadians(eccentricityOfOrbit) + "\n" +
                         "\n6) Inclination (in Degrees):              " + Math.toDegrees(inclination) + "°" +
                         "\n7) Inclination (in Radians):              " + inclination + "\n" +
                         "\n8) Argument of Perihelion (in Degrees):   " + Math.toDegrees(perihelion) + "°" +
                         "\n9) Argument of Perihelion (in Radians):   " + perihelion + "\n" + 
                         "\n10) Longitude Ascending Node (in Degrees): " + Math.toDegrees(longitudeAscNode) + "°" +
                         "\n11) Longitude Ascending Node (in Radians): " + longitudeAscNode +"\n");
                System.out.println(output);
                
                /* Step 3: Calculate the position of the Earth in its orbit */
                double mEarth = mod2Pi(meanLongitude - perihelion);
                System.out.println("mEarth (in Radians) = " + mEarth);//df.format(mEarth)
                
                double vEarth = true_anomaly(mEarth, Math.toRadians(eccentricityOfOrbit));
                System.out.println("vEarth = " + vEarth);
                
                double rEarth = semiMajorAxis * (1 - Math.pow(eccentricityOfOrbit, 2)) / (1 + eccentricityOfOrbit * Math.cos(vEarth));
                System.out.println("rEarth = " + rEarth);
                
                /* Step 4: Calculate the heliocentric rectangular coordinates of Earth */
                double xEarth = rEarth * Math.cos(vEarth + perihelion);
                double yEarth = rEarth * Math.sin(vEarth + perihelion);
                double zEarth = 0.0;
                System.out.println("xEarth = " + xEarth + "\nyEarth = " + yEarth + "\nzEarth = " + zEarth);
                
                /* Step 6: Calcuate the heliocentric rectangular coordinates of the planet */
                xH = yH = zH = 0.0;
                System.out.println("Converting to HELIO Rec coords:");
                System.out.println("xH = " + xH + "\nyH = " + yH + "\nzH = " + zH);
                
                /* Step 7: Convert to geocentric rectangular coordinates */
                xG = xH - xEarth;
                yG = yH - yEarth;
                zG = zH - zEarth;
                System.out.println("Converting to GEO Rec Coords:");
                System.out.println("xG = " + xG + "\nyG = " + yG + "\nzG = " + zG);
                
                /* Step 8: Rotate around X axis from ecliptic to equatorial coordinates */
                ecl = 23.439281 * RADS; //value is in Radians
                
                xEq = xG;
                yEq = yG * Math.cos(ecl) - zG * Math.sin(ecl);
                zEq = zG;
                System.out.println("Rotating X axis from ecliptic to equatorial coordinates:");
                System.out.println("ecl = " + ecl + "\nxEquatorial = "  + xEq + "\nyEquatorial = " + yEq);
                System.out.println("zEquatorial = " + zEq +"\n");
                
                /* Step 9: Calculate right ascension and declination from rectangular equatorial coordinates: */
                rightAsc = mod2Pi(Math.atan2(yEq, xEq)) * DEGS; // right ascension is in degrees
                declination = Math.atan(zEq / Math.sqrt(Math.pow(xEq, 2) + Math.pow(yEq, 2))) * DEGS;
                distance = Math.sqrt(Math.pow(xEq, 2) + Math.pow(yEq, 2) + Math.pow(zEq, 2));
                
                System.out.println("Right Ascension = " + rightAsc + "\nDeclination = " + declination + "\nDistance = " + distance);
                
                break;
        /* Mars */
            case 4:
                meanLongitude =  mod2Pi((Lscal + Lprop * cy / 3600) * RADS);
                semiMajorAxis = Ascal - Aprop * cy;
                eccentricityOfOrbit = Escal + Eprop * cy;
                inclination = (Iscal - Iprop * cy / 3600) * RADS;
                perihelion = ( Wscal + Wprop * cy / 3600) * RADS;
                longitudeAscNode = (Oscal - Oprop * cy / 3600) * RADS;
                
                output = String.format("Planet name:              " + name +
                         "\nMean Longitude:           " + meanLongitude + "°" +
                         "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                         "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                         "\nInclination:              " + inclination + "°" +
                         "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                         "\nArgument of Perihelion:   " + perihelion + " °\n");
                System.out.println(output);
                //System.out.println("90 degrees = " + mod2Pi(1.5708));
                System.out.println("450 degrees = " + mod2Pi(7.85398)); // 450 degrees to radians is 7.85398, 1.5708 rads - (90)
                //System.out.println("540 degrees = " + mod2Pi(9.42478)); // 540 degrees to radians is 9.42478, 3.1416 rads - (180)
                
                
                /* Step 5: Calculate the position of the planet in its' orbit */ //all PLANETS except Earth/Sun
                double mPlanet = mod2Pi(meanLongitude - perihelion);
                //System.out.println("mPlanet (in Radians) = " + mPlanet);
                double vPlanet = true_anomaly(mPlanet, Math.toRadians(eccentricityOfOrbit));
                //System.out.println("vPlanet = " + vPlanet);
                double rPlanet = semiMajorAxis * (1 - Math.pow(eccentricityOfOrbit, 2)) / (1 + eccentricityOfOrbit * Math.cos(vPlanet));
                //System.out.println("rPlanet = " + rPlanet);
                
                /* Step 6: Calculate the heliocentric rectangular coordinates of the planet */
                xH = rPlanet * (Math.cos(longitudeAscNode) * Math.cos(vPlanet + perihelion - longitudeAscNode) - Math.sin(longitudeAscNode) * Math.sin(vPlanet + perihelion - longitudeAscNode) * Math.cos(inclination));
                yH = rPlanet * (Math.sin(longitudeAscNode) * Math.cos(vPlanet + perihelion - longitudeAscNode) - Math.cos(longitudeAscNode) * Math.sin(vPlanet + perihelion - longitudeAscNode) * Math.cos(inclination));;
                zH = rPlanet * (Math.sin(vPlanet + perihelion - longitudeAscNode) * Math.sin(inclination));
                
                
                
                break;
        /* Jupiter */
            case 5:
                meanLongitude = mod2Pi((Lscal + Lprop * cy / 3600) * RADS);
                semiMajorAxis = Ascal + Aprop * cy;
                eccentricityOfOrbit = Escal - Eprop * cy;
                inclination = ( Iscal - Iprop * cy / 3600) * RADS;
                perihelion = (Wscal + Wprop * cy / 3600) * RADS;
                longitudeAscNode = ( Oscal + Oprop * cy / 3600) * RADS;
                
                output = String.format("Planet name:              " + name +
                         "\nMean Longitude:           " + meanLongitude + "°" +
                         "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                         "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                         "\nInclination:              " + inclination + "°" +
                         "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                         "\nArgument of Perihelion:   " + perihelion + " °\n");
                System.out.println(output);
                break;
        /* Saturn */
            case 6:
                meanLongitude = mod2Pi((Lscal + Lprop * cy / 3600) * RADS);
                semiMajorAxis = Ascal - Aprop * cy;
                eccentricityOfOrbit = Escal - Eprop * cy;
                inclination = ( Iscal + Iprop * cy / 3600) * RADS;
                perihelion = (Wscal - Wprop * cy / 3600) * RADS;
                longitudeAscNode = ( Oscal - Oprop * cy / 3600) * RADS;
                
                output = String.format("Planet name:              " + name +
                         "\nMean Longitude:           " + meanLongitude + "°" +
                         "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                         "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                         "\nInclination:              " + inclination + "°" +
                         "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                         "\nArgument of Perihelion:   " + perihelion + " °\n");
                System.out.println(output);
                break;
        /* Uranus */
            case 7:
                meanLongitude = mod2Pi((Lscal + Lprop * cy / 3600) * RADS);
                semiMajorAxis = Ascal + Aprop * cy;
                eccentricityOfOrbit = Escal - Eprop * cy;
                inclination = ( Iscal - Iprop * cy / 3600) * RADS;
                perihelion = ( Wscal + Wprop * cy / 3600) * RADS;
                longitudeAscNode = (Oscal + Oprop * cy / 3600) * RADS;
                
                output = String.format("Planet name:              " + name +
                         "\nMean Longitude:           " + meanLongitude + "°" +
                         "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                         "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                         "\nInclination:              " + inclination + "°" +
                         "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                         "\nArgument of Perihelion:   " + perihelion + " °\n");
                System.out.println(output);
                break;
        /* Neptune */
            case 8:
                meanLongitude = mod2Pi((Lscal + Lprop * cy / 3600) * RADS);
                semiMajorAxis = Ascal - Aprop * cy;
                eccentricityOfOrbit = Escal + Eprop * cy;
                inclination = ( Iscal - Iprop * cy / 3600) * RADS;
                perihelion = (Wscal - Wprop * cy / 3600) * RADS;
                longitudeAscNode  = ( Oscal - Oprop * cy / 3600) * RADS;
                
                output = String.format("Planet name:              " + name +
                         "\nMean Longitude:           " + meanLongitude + "°" +
                         "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                         "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                         "\nInclination:              " + inclination + "°" +
                         "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                         "\nArgument of Perihelion:   " + perihelion + " °\n");
                System.out.println(output);
                break;
        /* Pluto */
            case 9:
                meanLongitude = mod2Pi((Lscal + Lprop * cy / 3600) * RADS);
                semiMajorAxis = Ascal - Aprop * cy;
                eccentricityOfOrbit = Escal + Eprop * cy;
                inclination = ( Iscal + Iprop * cy / 3600) * RADS;
                perihelion = (Wscal - Wprop * cy / 3600) * RADS;
                longitudeAscNode  = (Oscal - Oprop * cy / 3600) * RADS;
                
                output = String.format("Planet name:              " + name +
                         "\nMean Longitude:           " + meanLongitude + "°" +
                         "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                         "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                         "\nInclination:              " + inclination + "°" +
                         "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                         "\nArgument of Perihelion:   " + perihelion + " °\n");
                System.out.println(output);
                break;
            default:
                break;
        }
        
    } // End of calculate()
    
    /*************************************************************************** 
    *  NAME: convertRightAscensionToHrsMinSec()
    *
    *  DESCRIPTION: converts Right Ascension to HOURS, MINUTES, SECCONDS
    *  
    *  @param RA Given Right Ascension
    *
    ***************************************************************************/
    public void convertRightAscensionToHrsMinSec(double RA)
    {
        int hours, minutes, seconds;
        
        hours = (int) (RA / 15.0);
        minutes =  (int) ((RA / 15.0) - hours * 60.0);
        seconds = (int) (((((RA / 15.0) - hours) * 60.0) - minutes) * 60.0);
        
        System.out.println("RA in Hours = " + hours);
        System.out.println("RA in Minutes = " + minutes);
        System.out.println("RA in Seconds = " + seconds);
        
    } // End convertRightAscensionToHrsMinSec()
    
    /*************************************************************************** 
    *  NAME: convertDeclinationToDegMinSec()
    *
    *  DESCRIPTION: converts Declination to DEGREES, MINUTES, SECCONDS
    *  
    *  @param Dec Given Declination value
    *
    ***************************************************************************/
    public void convertDeclinationToDegMinSec(double Dec)
    {
        int degrees, minutes, seconds;
        
        degrees = (int) (Dec);
        minutes =  (int) ((Dec - degrees) * 60.0);
        seconds = (int) ((((Dec - degrees) * 60.0) - minutes) * 60.0);
        
        System.out.println("Dec in Degrees = " + degrees);
        System.out.println("Dec in Minutes = " + minutes);
        System.out.println("Dec in Seconds = " + seconds);
        
    } // End convertRightAscensionToDegMinSec()
    
    /***************************************************************************
     * METHOD: calculateAltitudeAzimuthOfPlanet()
     * 
     * DESCRIPTION: calculates Altitude and Azimuth of a Planet.
     * 
     * @param lat Given latitude
     * @param lon Given longitude
     * @param RA  Given Right Ascension
     * @param Dec Given Declination
     * 
     ***************************************************************************/
    public double calculateAltitudeAzimuthOfPlanet(double lat, double lon, double RA, double Dec)
    {
        int hourAngle;
        double decRad, latRad, hrRad, sin_alt, alt, cos_az, az;
        
        //if(lat is in the SOUTHERN HEMISPHERE )
        lat = lat * -1.0;
        
        //if(long is given as West)
        lon = lon * -1.0;
        
        //hourAngle = meanSiderealTime() - RA;
        hourAngle = 1; //hourAngle for right now is 1
        
        if(hourAngle < 0) hourAngle += 360;
        
        // Convert Degrees to Radians
        decRad = Math.toRadians(Dec);
        latRad = Math.toRadians(lat);
        hrRad = Math.toRadians(hourAngle);
        
        // Calculate altitude in Radians
        sin_alt = (Math.sin(decRad) * Math.sin(latRad)) + (Math.cos(decRad) * Math.cos(latRad) * Math.cos(hrRad));
        alt = Math.sin(sin_alt);
        
        //Calculate azimuth in Radians
        try{
            cos_az = (Math.sin(decRad) - Math.sin(alt) * Math.sin(latRad)) / (Math.cos(alt) * Math.cos(latRad));
            az = Math.acos(cos_az);
        }catch (Exception e){
            az = 0;
        }
        
        // Convert altitude and azimuth to Degrees
        alt = alt * Math.toDegrees(alt);
        az = alt * Math.toDegrees(alt);
        
        if(Math.sin(hrRad) > 0.0)
            az = 360.0 - az;
        
        return az;
        
    } // End of calculateAltitudeAzimuthOfPlanet()
}