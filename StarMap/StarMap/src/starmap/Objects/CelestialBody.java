/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.time.LocalTime;

/**
 * OBJECT: CelestialBody
 * 
 * DESCRIPTION: This is the Parent class that shares the 
 *              calculateHorizonCoordinates() among its children.
 * 
 * @author pssewell21 (code) and Dina Brown (javadoc comments)
 */
public abstract class CelestialBody 
{
    public double azimuth;
    
    public double elevation;

    public String name;
    
    public abstract void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime) throws Exception;
} // End CelestialBody()
