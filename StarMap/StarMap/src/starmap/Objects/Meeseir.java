/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author pssewell21
 */
public class Meeseir extends CelestrialObject
{
    public Map<Double, Double> getHorizonCoorfinates()
    {
        Map<Double, Double> map = new HashMap<>();
    
        map.put(1.0, 2.0);
        
        return map;            
    }   
}
