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
    
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime)
    {        
    }
    
    public void getIntermediateValues()
    {
        
    }
}
