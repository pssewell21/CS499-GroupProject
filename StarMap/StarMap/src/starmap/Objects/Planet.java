/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import starmap.Calculation;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.IOException;


/**
 * DESCRIPTION: This object contains the calculated planet elements. The elements
 *              are then used to determine the positions of 9 major planets.
 * 
 * Date: 02-10-2019
 * @author  Dina Brown and Patrick Sewell
 */
public class Planet extends CelestialObject
{   
    public final static double RADS = Math.PI / 180.0;
    public final static double DEGS = 180 / Math.PI;
    
    public int g_year;
    public int g_month;
    public int g_day;
    public double g_declination;
    public double g_rightAscension;
    
    public String g_RA_hour;
    public String g_RA_min;
    public String g_RA_sec;
    
    public String g_DEC_deg;
    public String g_DEC_min;
    public String g_DEC_sec;
    
    /* Planet variables to determine planet elements */
    public double g_lscal;
    public double g_lprop;
    public double g_ascal;
    public double g_aprop;
    public double g_escal;
    public double g_eprop;
    public double g_iscal;
    public double g_iprop;
    public double g_wscal;
    public double g_wprop;
    public double g_oscal;
    public double g_oprop;
    
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
        this.g_lscal = lscal;
        this.g_lprop = lprop;
        this.g_ascal = ascal;
        this.g_aprop = aprop;
        this.g_escal = escal;
        this.g_eprop = eprop;
        this.g_iscal = iscal;
        this.g_iprop = iprop;
        this.g_wscal = wscal;
        this.g_wprop = wprop;
        this.g_oscal = oscal;
        this.g_oprop = oprop;
        
    } // End Planet()
    
     /**************************************************************************
     * 
     * METHOD: planet_calculateMeanSiderealTime()
     * 
     * DESCRIPTION: calculates the mean sidereal time
     * 
     * @param new_longitude longitude value
     * @param new_time given time that is used to access hour, min, and sec 
     * @param year
     * @param month
     * @param day
     * 
     * @return calculated true anomaly
     * 
    ***************************************************************************/
    public double planet_meanSiderealTime (double new_longitude, LocalTime new_time, int year, int month, int day)// LocalDateTime new_date)
    {
        double a, b, c, d, jd, jt, mst;
        int hour, min, sec;
        
        hour = new_time.getHour();
        min = new_time.getMinute();
        sec = new_time.getSecond();
        
        // Adjust month and year if needed
        if(month <= 2)
        {
            year -= 1;
            month += 12;
        }
        
        a = Math.floor(year / 100.0);
        b = 2 - a + Math.floor(a / 4);
        c = Math.floor(365.25 * year);
        d = Math.floor(30.6001 * (month + 1));
        
        // Get days since J2000.0
        jd = b + c + d - 730550.5 + day + ((hour + (min / 60) + (sec / 3600)) / 24);
        // Get Julian centuries since J2000.0
        jt = jd / 36525.0;
        
        // Calculate initial Mean Sidereal Time (mst)
        mst = 280.46061837 + (360.98564736629 * jd) + (0.000387933 * Math.pow(jt, 2)) 
                                    - (Math.pow(jt, 3) / 38710000) + new_longitude;
        
        // Clip mst to range 0.0 to 360.0
        if(mst > 0.0)
        {
            while(mst > 360.0)
            {
                mst -= 360.0;
            }
            return mst;
        }
        else
        {
            while(mst < 0.0)
            {
                mst += 360.0;
            }
            return mst;        
        }
        
    } // End planet_calculateMeanSiderealTime()
    
     /**************************************************************************
     * 
     * METHOD: planet_true_anomaly()
     * 
     * DESCRIPTION: calculates the true anomaly from the mean anomaly
     * 
     * @param M
     * @param e this is the Eccentricity for a given planet
     * @return calculated true anomaly
     * 
    ***************************************************************************/
    public double planet_true_anomaly(double M, double e)
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
        
    } // End planet_true_anomoly()
    
     /**************************************************************************
     * 
     * METHOD: planet_mod2Pi()
     * 
     * DESCRIPTION: Takes an angle over 360 degrees and converts it to an
     *              equivalent angle on the interval [0,360]
     *              example: 450 degrees is 90 degrees on the interval [0,360]
     * 
     * @param X Angle that will be converted to a new value.
     * @return an angle in radians
     * 
    ***************************************************************************/
    public double planet_mod2Pi(double X)
    {
        // Angle X is already in Radians when it's calculuated in this function:
        double A, B;
        
        B = X / (2 * Math.PI);
        A = (2 * Math.PI) * (B - Math.abs(Math.floor(B)));
        
        if(A < 0) A = (2 * Math.PI) + A;

        return A; //returns the value in Radians
        
    } // End planet_mod2Pi()
    
    /***************************************************************************
     * 
     * METHOD: planet_convertRightAscensionToHrsMinSec()
     * 
     * DESCRIPTION: converts Right Ascension to HOURS, MINUTES, SECCONDS
     * 
     * @param RA Given Right Ascension
     * 
     ***************************************************************************/
    public void planet_convertRightAscensionToHrsMinSec(double RA)
    {
        int hours, minutes, seconds;
        
        hours = (int) (RA / 15.0);
        minutes =  (int) (((RA / 15.0) - hours) * 60.0);
        seconds = (int) (((((RA / 15.0) - hours) * 60.0) - minutes) * 60.0);
        
        g_RA_hour = Integer.toString(hours);
        g_RA_min = Integer.toString(minutes);
        g_RA_sec = Integer.toString(seconds);
        
        System.out.println("RA = " + RA);
        System.out.println("RA in Hours = " + hours);
        System.out.println("RA in Minutes = " + minutes);
        System.out.println("RA in Seconds = " + seconds);

    } // End convertRightAscensionToHrsMinSec()
    
    /*************************************************************************** 
    *  NAME: planet_convertDeclinationToDegMinSec()
    *
    *  DESCRIPTION: converts Declination to DEGREES, MINUTES, SECCONDS
    *  
    *  @param Dec Given Declination value
    *
    ***************************************************************************/
    public void planet_convertDeclinationToDegMinSec(double Dec)
    {
        int degrees, minutes, seconds;
        
        degrees = (int) (Dec);
        minutes = (int) ((Dec - degrees) * 60.0);
        seconds = (int) ((((Dec - degrees) * 60.0) - minutes) * 60.0);
        
        g_DEC_deg = Integer.toString(degrees);
        g_DEC_min = Integer.toString(minutes);
        g_DEC_sec = Integer.toString(seconds);
        
        System.out.println("DEC = " + Dec);
        System.out.println("Dec in Degrees = " + degrees);
        System.out.println("Dec in Minutes = " + minutes);
        System.out.println("Dec in Seconds = " + seconds);

        
    } // End convertRightAscensionToDegMinSec()
    
     /**************************************************************************
     * 
     * METHOD: planet_getIntermediateValues()
     * 
     * DESCRIPTION: gets the calculated values for each planet
     * @param julianDate 
     * @param dateTime given date that is used to set 
     * //@return calculated values
     * 
    ***************************************************************************/
    public void planet_getIntermediateValues(double julianDate, LocalDateTime dateTime)
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
        double distance;
        
        /* 
        *  cy = JD/36525 
        *  a = semimajor axis               --> a = Ascal + Aprop*cy
        *  e = eccentricity                 --> e = Escal + Eprop * cy
        *  i = inclination                  --> i = ( Iscal - Iprop * cy / 3600) * RADS
        *  w = argument of perihelion       --> w = (Wscal + Wprop * cy / 3600) * RADS
        *  N = longitude of ascending node  --> N = (Oscal - Oprop * cy / 3600) * RADS
        *  L = mean longitude of the planet --> L=Mod2Pi ((Lscal + Lprop * cy / 3600) * RADS)
        */
        
        // TODO: Remove this when doing stuff for real
        //double jd = 2458540; //2458534.5; //Julian Day 
        //double jd = 2458583.597916667;//Calculation.getJulianDate(dateTime); //2458583.597916667
        double cy = julianDate/36525; //67.31235181382614
        System.out.println("1) jd = " + julianDate);
        System.out.println("2) cy = " + cy + "\n");
        
        // This is used to set the Year, Month, and Day so that meanSideralTime()
        // can then access these global values in the getHorizonCoordinates() without
        // having to change the abstract method signature.
        g_year = dateTime.getDayOfYear();
        g_month = dateTime.getDayOfMonth();
        g_day = dateTime.getDayOfYear();
        
        double mEarth, vEarth, rEarth;
        double xEarth, yEarth, zEarth;
        double mPlanet, vPlanet, rPlanet;
        
        
        // Calculation of Major Planet Elements and Positions:
        meanLongitude = planet_mod2Pi((g_lscal + g_lprop * cy / 3600) * RADS); // In Radians = 1.7429177410226784
        
        if (name.equalsIgnoreCase("Mercury") 
                || name.equalsIgnoreCase("Venus")
                || name.equalsIgnoreCase("Jupiter")
                || name.equalsIgnoreCase("Uranus"))
        {
            semiMajorAxis = g_ascal + g_aprop * cy;       //not in Radians            
        }
        else
        {
            semiMajorAxis = g_ascal - g_aprop * cy;       //not in Radians 
        }
        
        if (name.equalsIgnoreCase("Mercury") 
                || name.equalsIgnoreCase("Mars")
                || name.equalsIgnoreCase("Neptune")
                || name.equalsIgnoreCase("Pluto"))
        {
            eccentricityOfOrbit = g_escal + g_eprop * cy; //not in radians         
        }
        else
        {
            eccentricityOfOrbit = g_escal - g_eprop * cy; //not in radians
        }
        
        if (name.equalsIgnoreCase("Saturn") 
                || name.equalsIgnoreCase("Pluto"))
        {
            inclination = (g_iscal + g_iprop * cy / 3600) * RADS;        
        }
        else
        {
            inclination = (g_iscal - g_iprop * cy / 3600) * RADS;
        }
        
        if (name.equalsIgnoreCase("Mercury") 
                || name.equalsIgnoreCase("Earth")
                || name.equalsIgnoreCase("Mars")
                || name.equalsIgnoreCase("Jupiter")
                || name.equalsIgnoreCase("Uranus"))
        {
            longitudeAscNode = (g_oscal + g_oprop * cy / 3600) * RADS;       
        }
        else
        {
            longitudeAscNode = (g_oscal - g_oprop * cy / 3600) * RADS;
        }
        
        if (name.equalsIgnoreCase("Jupiter"))
        {
            perihelion = (g_wscal + g_wprop * cy / 3600) * RADS;     
        }
        else
        {
            perihelion = (g_wscal - g_wprop * cy / 3600) * RADS;
        }   

//        System.out.println(String.format("Planet Info:\nPlanet name:              " + name +
//                 "\nMean Longitude:           " + meanLongitude + "°" +
//                 "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
//                 "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
//                 "\nInclination:              " + inclination + "°" +
//                 "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
//                 "\nArgument of Perihelion:   " + perihelion + "\n"));

        /* Step 3: Calculate the position of the Earth in its orbit */
        mEarth = planet_mod2Pi(meanLongitude - perihelion);        
        vEarth = planet_true_anomaly(mEarth, Math.toRadians(eccentricityOfOrbit));        
        rEarth = semiMajorAxis * (1 - Math.pow(eccentricityOfOrbit, 2)) / 
                                    (1 + eccentricityOfOrbit * Math.cos(vEarth));
            
        /* Step 4: Calculate the HELIOCENTRIC rectangular coordinates of Earth */
        xEarth = rEarth * Math.cos(vEarth + perihelion);
        yEarth = rEarth * Math.sin(vEarth + perihelion);
        zEarth = 0.0;
            
        /* Step 5: Calculate the position of the planet in its' orbit */
        mPlanet = planet_mod2Pi(meanLongitude - perihelion);
        vPlanet = planet_true_anomaly(mPlanet, Math.toRadians(eccentricityOfOrbit));
        rPlanet = semiMajorAxis * (1 - Math.pow(eccentricityOfOrbit, 2)) / (1 + eccentricityOfOrbit * Math.cos(vPlanet));
            
        if(name.equalsIgnoreCase("Earth"))
        {
            /* Step 6: Calcuate the heliocentric rectangular coordinates of the planet */
            xH = yH = zH = 0.0;
        }
        else
        {
            /* Step 6: Calculate the heliocentric rectangular coordinates of the planet */
            xH = rPlanet * (Math.cos(longitudeAscNode) * Math.cos(vPlanet + perihelion - longitudeAscNode) - Math.sin(longitudeAscNode) * Math.sin(vPlanet + perihelion - longitudeAscNode) * Math.cos(inclination));
            yH = rPlanet * (Math.sin(longitudeAscNode) * Math.cos(vPlanet + perihelion - longitudeAscNode) - Math.cos(longitudeAscNode) * Math.sin(vPlanet + perihelion - longitudeAscNode) * Math.cos(inclination));;
            zH = rPlanet * (Math.sin(vPlanet + perihelion - longitudeAscNode) * Math.sin(inclination));            
        }

        /* Step 7: Convert to geocentric rectangular coordinates */
        xG = xH - xEarth;
        yG = yH - yEarth;
        zG = zH - zEarth;
            
        /* Step 8: Rotate around X axis from ecliptic to equatorial coordinates */
        ecl = 23.439281 * RADS; //value is in Radians
                
        xEq = xG;
        yEq = yG * Math.cos(ecl) - zG * Math.sin(ecl);
        zEq = yG * Math.sin(ecl) + zG * Math.cos(ecl);
            
        /* Step 9: Calculate RIGHT ASCENSION and DECLINATION from rectangular equatorial coordinates: */
        g_rightAscension = planet_mod2Pi(Math.atan2(yEq, xEq)) * DEGS; // right ascension is in degrees
        g_declination = Math.atan(zEq / Math.sqrt(Math.pow(xEq, 2) + Math.pow(yEq, 2))) * DEGS;
        distance = Math.sqrt(Math.pow(xEq, 2) + Math.pow(yEq, 2) + Math.pow(zEq, 2));
        
        System.out.println("(1)g_dec = " + g_declination);
        
        //planet_convertRightAscensionToHrsMinSec(g_rightAscension);
        //planet_convertDeclinationToDegMinSec(g_declination);

        
    } // End planet_getIntermediateValues()
    
    @Override
     /**************************************************************************
     * 
     * METHOD: calculateHorizionCoordinates()
     * 
     * DESCRIPTION: gets the coordinates and time information that will be used
     *              to plot each of the planets.
     * 
     * @param latitude
     * @param longitude
     * @param greenwhichSiderealTime
     * 
    ***************************************************************************/
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime)
    {    
        //need to implement exception statements
        
        double hourAngle;
        double decRad;
        double latRad;
        double hrRad;
        double sin_alt;
        double cos_az;

        hourAngle = planet_meanSiderealTime(longitude, greenwichSiderealTime, g_year, 
                                                g_month, g_day) - g_rightAscension;
        if(hourAngle < 0) hourAngle += 360;
        
        // Convert Degrees to Radians
        decRad = g_declination * RADS; //Math.toRadians(g_declination);
        latRad = latitude * RADS; //Math.toRadians(latitude);
        hrRad = hourAngle * RADS; //Math.toRadians(hourAngle);
        
        // Calculate ELEVATION in Radians
        sin_alt = (Math.sin(decRad) * Math.sin(latRad)) + (Math.cos(decRad) * Math.cos(latRad) * Math.cos(hrRad));
        elevation = Math.sin(sin_alt);
        
        //Calculate AZIMUTH in Radians
        try{
            cos_az = (Math.sin(decRad) - Math.sin(elevation) * Math.sin(latRad)) / (Math.cos(elevation) * Math.cos(latRad));
            azimuth = Math.acos(cos_az);
        }catch (Exception ex){
            azimuth = 0;
        }
        
        // Convert EL and AZ to Degrees
        elevation = elevation * Math.toDegrees(elevation);
        azimuth = elevation * Math.toDegrees(elevation);
        
        if(Math.sin(hrRad) > 0.0)
            azimuth = 360.0 - azimuth;
        
//        if (elevation < 0)
//        {
//            elevation += 360;
//        }
//        
        if (azimuth > 360) azimuth -= 360;
            
        if(azimuth < 0) azimuth += 360;
            
        
        System.out.println("Azimuth = " + azimuth);
        System.out.println("Elevation = " + elevation + "\n");
        
    } // End calculateHorizonCoordinates()
    
} // End class Planet



//1) jd = 2458583.597916667
//2) cy = 67.3123503878622

//Planet name:              Mercury
//Mean Longitude:           5.747478731799017°
//Semi-major Axis:          0.38714335615131873 AU
//Eccentricity Of Orbit:    0.20733167309670356
//Inclination:              -0.007638280705852504°
//Longitude Ascending Node: -0.14541099325785878°
//Argument of Perihelion:   1.5390486173690672

//Current Azimuth/Elevation of Mercury: -52684.63259348455°, 30.53009457198231°
//Current Azimuth/Elevation of Venus: -43638.16877430354°, 27.82437951270314°
//Current Azimuth/Elevation of Earth/Sun: -43638.16877430354°, 27.82437951270314°
//Current Azimuth/Elevation of Mars: -43638.16877430354°, 27.82437951270314°
//Current Azimuth/Elevation of Jupiter: -43638.16877430354°, 27.82437951270314°
//Current Azimuth/Elevation of Saturn: -43638.16877430354°, 27.82437951270314°
//Current Azimuth/Elevation of Uranus: -43638.16877430354°, 27.82437951270314°
//Current Azimuth/Elevation of Neptune: -43638.16877430354°, 27.82437951270314°
//Current Azimuth/Elevation of Pluto: -43638.16877430354°, 27.82437951270314°'

//Current Azimuth/Elevation of Moon: 185.26179325774947°, -28.33997908709473°