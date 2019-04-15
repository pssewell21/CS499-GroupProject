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
 * OBJECT: Planet
 * 
 * DESCRIPTION: This object contains the calculated planet elements. The elements
 *              are then used to determine the positions of 9 major planets. 
 *              The planet elements data file was provided by Dr. Rick Coleman (UAH).
 * 
 * DATE: 02-10-2019
 * 
 * @author  Dina Brown and Patrick Sewell
 */
public class Planet extends CelestialBody
{   
    /**
     *  GLOBAL VARIABLES
     */
    public final static double RADS = Math.PI / 180.0;
    public final static double DEGS = 180 / Math.PI;
    
    public int g_year;
    public int g_month;
    public int g_day;
    
    public double g_declination;
    public double g_rightAscension;
    public double g_positiveRightAsc;
    
    public double g_eSemiMajorAxis;
    public double g_pSemiMajorAxis;
    
    public double g_RA_hour;
    public double g_RA_min;
    public double g_RA_sec;
    
    public String g_str_RA_hour;
    public String g_str_RA_min;
    public String g_str_RA_sec;
    
    public double g_DEC_deg;
    public double g_DEC_min;
    public double g_DEC_sec;
    
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
     * DESCRIPTION: calculates the mean sidereal time. The pseudocode
     *              for this function was provided by Dr. Rick Coleman (UAH).
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
    public static double planet_meanSiderealTime (double new_longitude, LocalTime new_time, int year, int month, int day)// LocalDateTime new_date)
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
     * DESCRIPTION: calculates the true anomaly from the mean anomaly. The pseudocode
     *              for this function was provided by Dr. Rick Coleman (UAH).
     * 
     * @param M
     * @param e this is the Eccentricity for a given planet
     * @return calculated true anomaly
     * 
    ***************************************************************************/
    public static double planet_true_anomaly(double M, double e)
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
     *              example: 450 degrees is 90 degrees on the interval [0,360].
     *              The pseudocode for this function was provided by 
     *              Dr. Rick Coleman (UAH).
     * 
     * @param X Angle that will be converted to a new value.
     * @return an angle in radians
     * 
    ***************************************************************************/
    public static double planet_mod2Pi(double X)
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
     * DESCRIPTION: converts Right Ascension to HOURS, MINUTES, SECCONDS. The
     *              pseudocode for this function was provided by Dr. Rick Coleman
     *              (UAH).
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
        
        g_RA_hour = hours;
        g_RA_min = minutes;
        g_RA_sec = seconds;

    } // End planet_convertRightAscensionToHrsMinSec()
    
    /*************************************************************************** 
    *  NAME: planet_convertDeclinationToDegMinSec()
    *
    *  DESCRIPTION: converts Declination to DEGREES, MINUTES, SECCONDS. The pseudocode
    *              for this function was provided by Dr. Rick Coleman (UAH).
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
        
        g_DEC_deg = degrees;
        g_DEC_min = minutes;
        g_DEC_sec = seconds;
        
        //System.out.println("DEC = " + Dec);
//        System.out.println("Dec in Degrees = " + g_DEC_deg);
//        System.out.println("Dec in Minutes = " + minutes);
//        System.out.println("Dec in Seconds = " + seconds);

        
    } // End convertRightAscensionToDegMinSec()
    
     /**************************************************************************
     * 
     * METHOD: planet_getIntermediateValues()
     * 
     * DESCRIPTION: gets the calculated values for each planet. The pseudocode
     *              for this function was provided by Dr. Rick Coleman (UAH).
     * @param julianDate 
     * @param dateTime given date that is used to set 
     * //@return calculated values
     * 
    ***************************************************************************/
    public void planet_getIntermediateValues(LocalDateTime dateTime)
    {
        double julianDate = Calculation.getJulianDate(dateTime);
        
        double meanLongitude;
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
        double cy = julianDate/36525; //67.31235181382614
//        System.out.println("1) jd = " + julianDate);
//        System.out.println("2) cy = " + cy + "\n");
        
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
            g_pSemiMajorAxis = g_ascal + g_aprop * cy;       //not in Radians            
        }
        else if(name.equalsIgnoreCase("Mars")
                || name.equalsIgnoreCase("Saturn")
                || name.equalsIgnoreCase("Neptune")
                || name.equalsIgnoreCase("Pluto"))
        {
            g_pSemiMajorAxis = g_ascal - g_aprop * cy;       //not in Radians
        }
        else if(name.equalsIgnoreCase("Earth"))
        {
            //semiMajorAxis = g_ascal - g_aprop * cy;
            g_eSemiMajorAxis = g_ascal - g_aprop * cy;
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
         //System.out.println(String.format("\nPlanet name: " + name));
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
        rEarth = g_eSemiMajorAxis * (1 - Math.pow(eccentricityOfOrbit, 2)) / 
                                    (1 + eccentricityOfOrbit * Math.cos(vEarth));
            
        /* Step 4: Calculate the HELIOCENTRIC rectangular coordinates of Earth */
        xEarth = rEarth * Math.cos(vEarth + perihelion);
        yEarth = rEarth * Math.sin(vEarth + perihelion);
        zEarth = 0.0;
            
        /* Step 5: Calculate the position of the planet in its' orbit */
        mPlanet = planet_mod2Pi(meanLongitude - perihelion);
        vPlanet = planet_true_anomaly(mPlanet, Math.toRadians(eccentricityOfOrbit));
        rPlanet = g_pSemiMajorAxis * (1 - Math.pow(eccentricityOfOrbit, 2)) / (1 + eccentricityOfOrbit * Math.cos(vPlanet));
            
        if(name.equalsIgnoreCase("Earth"))
        {
            /* Step 6: Calcuate the heliocentric rectangular coordinates of Earth */
            xH = yH = zH = 0.0;
        }
        else
        {
            /* Step 6: Calculate the heliocentric rectangular coordinates of the planet */
            
            xH = rPlanet * (Math.cos(longitudeAscNode) * Math.cos(vPlanet + perihelion - longitudeAscNode)
                    - Math.sin(longitudeAscNode) * Math.sin(vPlanet + perihelion - longitudeAscNode)
                    * Math.cos(inclination));
            
            yH = rPlanet * (Math.sin(longitudeAscNode) * Math.cos(vPlanet + perihelion - longitudeAscNode)
                    + Math.cos(longitudeAscNode) * Math.sin(vPlanet + perihelion - longitudeAscNode)
                    * Math.cos(inclination));
            
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
        
//        System.out.println("Planet: g_DEC = " + g_declination);
//        System.out.println("Planet: g_RA = " + g_rightAscension);

        planet_convertDeclinationToDegMinSec(g_declination);
        
        // Making RA POSITIVE number, if it's calculated as a negative value:
        if(g_rightAscension < 0)
        {
//            g_RA_hour = 0;
//            g_RA_min = 0;
//            g_RA_sec = 0;
//            planet_convertRightAscensionToHrsMinSec(g_RA_hour);
            g_positiveRightAsc = Math.abs(g_rightAscension);
            planet_convertRightAscensionToHrsMinSec(g_positiveRightAsc);
        }

//        System.out.println("g_positiverightAsc " + g_positiveRightAsc);
//
//        System.out.println("\nRA_hr = " + g_RA_hour);
//        System.out.println("RA_min = " + g_RA_min);
//        System.out.println("RA_sec = " + g_RA_sec);

    } // End planet_getIntermediateValues()
    
    @Override
     /**************************************************************************
     * 
     * METHOD: calculateHorizionCoordinates()
     * 
     * DESCRIPTION: gets the coordinates and time information that will be used
     *              to plot each of the planets.
     * 
     * @param latitude the north/south direction of the surface of the earth.
     * @param longitude the east/west direction of the surface of the earth.
     * @param greenwhichSiderealTime
     * 
    ***************************************************************************/
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime) throws Exception
    {    
        if (g_RA_hour < 0 || g_RA_hour > 24)
        {
            throw new Exception("Invalid value of " + g_RA_hour + " for rightAscension passed into Planet.calculateHorizonCoordinates");
        }
        
        if (g_DEC_deg < -90 || g_DEC_deg > 90)
        {
            throw new Exception("Invalid value of " + g_DEC_deg + " for declination passed into Planet.calculateHorizonCoordinates");
        }
        
        double decimalHours = greenwichSiderealTime.getHour() + (greenwichSiderealTime.getMinute() / 60.0) + (greenwichSiderealTime.getSecond() / (60.0 * 60));
        
        // Longitude passed is negative if west of Greenwich and will be subtracted in this case
        double hourAngleDegrees = (decimalHours - g_RA_hour) * 15 + longitude;        
        
//        System.out.println("hourAngleDegrees = " + hourAngleDegrees);
        
        double hourAngleRadians = Calculation.getRadiansFromDegrees(hourAngleDegrees);
        double declinationRadians = Calculation.getRadiansFromDegrees(g_DEC_deg);
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
    
} // End class Planet