/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.util.Map;

/**
 *
 * @author pssewell21
 */
public abstract class CelestrialObject 
{
    double azimuth;
    
    double elevation;

    String name;
    
    public abstract Map<Double, Double> getHorizonCoorfinates();
}
