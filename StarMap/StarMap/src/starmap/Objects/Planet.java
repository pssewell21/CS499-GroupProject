/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.time.LocalTime;

/**
 * DESCRIPTION: This object contains the calculated planet elements. The elements
 *              are then used to determine the position of 9 major planets.
 * 
 * @author pssewell21 and dinabrown
 */
public class Planet extends CelestialObject
{   
    public final static double RADS = Math.PI / 180.0;
    public final static double DEGS = 180 / Math.PI;
    
    /* Planet variables to determine planet elements */
    public double lscal;
    public double lprop;
    public double ascal;
    public double aprop;
    public double escal;
    public double eprop;
    public double iscal;
    public double iprop;
    public double wscal;
    public double wprop;
    public double oscal;
    public double oprop;
    
     /**************************************************************************
     * 
     * DESCRIPTION: Constructor for class Planet
     * 
     * @param name the given name of a planet
     * @param lscal used to calculate the mean longitude of a planet
     * @param lprop used to calculate the mean longitude of a planet
     * @param ascal used to calculate the semi-major axis of orbit for a planet
     * @param aprop used to calculate the semi-major axis of orbit for a planet
     * @param escal used to calculate the eccentricity of orbit for a planet
     * @param eprop used to calculate the eccentricity axis of orbit for a planet
     * @param iscal used to calculate the inclination of a planet
     * @param iprop used to calculate the inclination of a planet
     * @param wscal used to calculate the argument of perihelion of a planet
     * @param wprop used to calculate the argument of perihelion of a planet
     * @param oscal used to calculate the longitude of ascending node of a planet
     * @param oprop used to calculate the longitude of ascending node of a planet
     * 
    ***************************************************************************/
    public Planet(String name, double lscal, double lprop, double ascal, 
                 double aprop, double escal, double eprop, double iscal,
                 double iprop, double wscal, double wprop, double oscal, 
                 double oprop)
    {
        this.name = name;
        this.lscal = lscal;
        this.lprop = lprop;
        this.ascal = ascal;
        this.aprop = aprop;
        this.escal = escal;
        this.eprop = eprop;
        this.iscal = iscal;
        this.iprop = iprop;
        this.wscal = wscal;
        this.wprop = wprop;
        this.oscal = oscal;
        this.oprop = oprop;
        
    } // End Planet()
    
     /**************************************************************************
     * 
     * METHOD: true_anomaly()
     * 
     * DESCRIPTION: calculates the true anomaly from the mean anomaly
     * @param M
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
        
    } // End true_anomoly()
    
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
     * 
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
     * 
     * METHOD: convertRightAscensionToHrsMinSec()
     * 
     * DESCRIPTION: converts Right Ascension to HOURS, MINUTES, SECCONDS
     * 
     * @param RA Given Right Ascension
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
        minutes = (int) ((Dec - degrees) * 60.0);
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
     * @return az
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
        double meanLongitude;
        double semiMajorAxis;
        double eccentricityOfOrbit; 
        double inclination;
        double longitudeAscNode;
        double perihelion;
        
        // Coordinates - Heliocentric, Geocentric, ecliptic, Equatorial
        double xH, yH, zH;
        double xG, yG, zG;
        double ecl;
        double xEq, yEq, zEq;
        double rightAsc, declination, distance;
        
        /* 
        *  cy = JD/36525 
        *  a = semimajor axis               --> a = Ascal + Aprop*cy
        *  e = eccentricity                 --> e = Escal + Eprop * cy
        *  i = inclination                  --> i = ( Iscal - Iprop * cy / 3600) * RADS
        *  ? = argument of perihelion       --> ? = (Wscal + Wprop * cy / 3600) * RADS
        *  Í = longitude of ascending node  --> Í = (Oscal - Oprop * cy / 3600) * RADS
        *  L = mean longitude of the planet --> L=Mod2Pi ((Lscal + Lprop * cy / 3600) * RADS)
        */
        
        // TODO: Remove this when doing stuff for real
        double jd = 2458540; //2458534.5; //Julian Day        
        double cy = jd/36525;
        
        meanLongitude = mod2Pi((lscal + lprop * cy / 3600) * RADS); // In Radians = 1.7429177410226784
        
        if (name.equalsIgnoreCase("Mercury") 
                || name.equalsIgnoreCase("Venus")
                || name.equalsIgnoreCase("Jupiter")
                || name.equalsIgnoreCase("Uranus"))
        {
            semiMajorAxis = ascal + aprop * cy;       //not in Radians            
        }
        else
        {
            semiMajorAxis = ascal - aprop * cy;       //not in Radians 
        }
        
        if (name.equalsIgnoreCase("Mercury") 
                || name.equalsIgnoreCase("Mars")
                || name.equalsIgnoreCase("Neptune")
                || name.equalsIgnoreCase("Pluto"))
        {
            eccentricityOfOrbit = escal + eprop * cy; //not in radians         
        }
        else
        {
            eccentricityOfOrbit = escal - eprop * cy; //not in radians
        }
        
        if (name.equalsIgnoreCase("Saturn") 
                || name.equalsIgnoreCase("Pluto"))
        {
            inclination = ( iscal + iprop * cy / 3600) * RADS;        
        }
        else
        {
            inclination = ( iscal - iprop * cy / 3600) * RADS;
        }
        
        if (name.equalsIgnoreCase("Mercury") 
                || name.equalsIgnoreCase("Earth")
                || name.equalsIgnoreCase("Mars")
                || name.equalsIgnoreCase("Jupiter")
                || name.equalsIgnoreCase("Uranus"))
        {
            longitudeAscNode = (oscal + oprop * cy / 3600) * RADS;       
        }
        else
        {
            longitudeAscNode = (oscal - oprop * cy / 3600) * RADS;
        }
        
        if (name.equalsIgnoreCase("Jupiter"))
        {
            perihelion = (wscal + wprop * cy / 3600) * RADS;     
        }
        else
        {
            perihelion = (wscal - wprop * cy / 3600) * RADS;
        }   

        System.out.println(String.format("Planet name:              " + name +
                 "\nMean Longitude:           " + meanLongitude + "°" +
                 "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                 "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                 "\nInclination:              " + inclination + "°" +
                 "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                 "\nArgument of Perihelion:   " + perihelion));
    }
    
    @Override
     /**************************************************************************
     * 
     * METHOD: calculateHorizionCoordinates()
     * 
     * DESCRIPTION: 
     * @param latitude
     * @param longitude
     * @param greenwhichSiderealTime
     * 
    ***************************************************************************/
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime)
    {    
        //need to implement
        
    } // End calculateHorizonCoordinates()
    
} // End class Planet
