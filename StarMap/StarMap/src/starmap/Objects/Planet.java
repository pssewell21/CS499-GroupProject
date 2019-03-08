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
public class Planet extends CelestialObject
{    
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
    
    public Planet(String name, double lscal, double lprop, double ascal, 
            double aprop, double escal, double eprop, double iscal, double iprop,
            double wscal, double wprop, double oscal, double oprop)
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
    }
    
    public void getIntermediateValues(double julianDate)
    {
        double meanLongitude;
        double semiMajorAxis;
        double eccentricityOfOrbit; 
        double inclination;
        double longitudeAscNode;
        double perihelion;
        
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
        double rads = Math.PI / 180.0;
        
        // TODO: Put in logic to + or - as needed per planet for each value calculation
        // TODO: toRadians is incorrect, implement Mod2Pi
        meanLongitude = Math.toRadians((lscal + lprop * cy / 3600) * rads);
        semiMajorAxis = ascal - aprop * cy;
        eccentricityOfOrbit = escal - eprop * cy;
        inclination = ( iscal - iprop * cy / 3600) * rads;
        perihelion = (wscal + wprop * cy / 3600) * rads;
        longitudeAscNode = (oscal - oprop * cy / 3600) * rads;

        System.out.println(String.format("Planet name:              " + name +
                 "\nMean Longitude:           " + meanLongitude + "°" +
                 "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                 "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                 "\nInclination:              " + inclination + "°" +
                 "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                 "\nArgument of Perihelion:   " + perihelion));
    }
    
    @Override
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime)
    {        
    }
}
