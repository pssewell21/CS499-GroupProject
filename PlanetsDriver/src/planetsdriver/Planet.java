/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetsdriver;
import java.io.*;
import java.lang.Math;
import java.util.Calendar;
//import java.lang.*;


/**
 *
 * @author dinabrown
 */
public class Planet {
    
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
    
    public void calculate(int new_value)
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
        
        double JD = 2458540; //2458534.5; //Julian Day
        double cy = JD/36525;
        double RADS = Math.PI / 180.0;
        
        String output;

        
        switch(new_value)
       {
            /* Mercury */
            case 1:
                meanLongitude = Math.toRadians((Lscal + Lprop * cy / 3600) * RADS);
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
                         "\nArgument of Perihelion:   " + perihelion + " °");
                System.out.println(output);
                break;
        /* Venus */
            case 2:
                meanLongitude = Math.toRadians((Lscal + Lprop * cy / 3600) * RADS);
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
                         "\nArgument of Perihelion:   " + perihelion + " °");
                System.out.println(output);
                break;
        /* Earth/Sun */
            case 3:
                meanLongitude = Math.toRadians((Lscal + Lprop * cy / 3600) * RADS);
                semiMajorAxis = Ascal - Aprop * cy;
                eccentricityOfOrbit = Escal - Eprop * cy;
                inclination = ( Iscal - Iprop * cy / 3600) * RADS;
                perihelion = (Wscal + Wprop * cy / 3600) * RADS;
                longitudeAscNode = (Oscal - Oprop * cy / 3600) * RADS;
                
                output = String.format("Planet name:              " + name +
                         "\nMean Longitude:           " + meanLongitude + "°" +
                         "\nSemi-major Axis:          " + semiMajorAxis + " AU" +
                         "\nEccentricity Of Orbit:    " + eccentricityOfOrbit +
                         "\nInclination:              " + inclination + "°" +
                         "\nLongitude Ascending Node: " + longitudeAscNode + "°" +
                         "\nArgument of Perihelion:   " + perihelion + " °");
                System.out.println(output);
                break;
        /* Mars */
            case 4:
                meanLongitude =  Math.toRadians((Lscal + Lprop * cy / 3600) * RADS);
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
                         "\nArgument of Perihelion:   " + perihelion + " °");
                System.out.println(output);
                break;
        /* Jupiter */
            case 5:
                meanLongitude = Math.toRadians((Lscal + Lprop * cy / 3600) * RADS);
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
                         "\nArgument of Perihelion:   " + perihelion + " °");
                System.out.println(output);
                break;
        /* Saturn */
            case 6:
                meanLongitude = Math.toRadians((Lscal + Lprop * cy / 3600) * RADS);
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
                         "\nArgument of Perihelion:   " + perihelion + " °");
                System.out.println(output);
                break;
        /* Uranus */
            case 7:
                meanLongitude = Math.toRadians((Lscal + Lprop * cy / 3600) * RADS);
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
                         "\nArgument of Perihelion:   " + perihelion + " °");
                System.out.println(output);
                break;
        /* Neptune */
            case 8:
                meanLongitude = Math.toRadians((Lscal + Lprop * cy / 3600) * RADS);
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
                         "\nArgument of Perihelion:   " + perihelion + " °");
                System.out.println(output);
                break;
        /* Pluto */
            case 9:
                meanLongitude = Math.toRadians((Lscal + Lprop * cy / 3600) * RADS);
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
                         "\nArgument of Perihelion:   " + perihelion + " °");
                System.out.println(output);
                break;
            default:
                break;
        }
        
    } // End of Calculate()
    
    public void displayPlanetInfo()
    {
        System.out.println("Name: " + name);
        System.out.println("Lscal: "+ Lscal);
        System.out.println("Lprop: "+ Lprop);
        System.out.println("Ascal: "+ Ascal);
        System.out.println("Aconst: "+ Aprop);
        System.out.println("Escal: "+ Escal);
        System.out.println("Eprop: "+ Eprop);
        System.out.println("Iscal: "+ Iscal);
        System.out.println("Iprop: "+ Iprop);
        System.out.println("Wscal: "+ Wscal);
        System.out.println("Oscal: "+ Oscal);
        System.out.println("Oprop: "+ Oprop);
        


    }
    

}
