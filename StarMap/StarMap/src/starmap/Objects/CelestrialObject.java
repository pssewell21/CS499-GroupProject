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
public abstract class CelestrialObject 
{
    public double azimuth;
    
    public double elevation;

    public String name;
    
    public abstract void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime) throws Exception;
}
