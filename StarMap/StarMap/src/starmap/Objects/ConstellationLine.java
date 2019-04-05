/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

/**
 *
 * @author pssewell21
 */
public class ConstellationLine
{
    public String name;
    
    public int rightAscensionHours ;
    
    public double rightAscensionMinutes ;
    
    public double declination;
    
    public String starName;
            
    public ConstellationLine(String name, int rightAscensionHours, double rightAscensionMinutes, double declination, String starName)
    {
        this.name = name;
        this.rightAscensionHours = rightAscensionHours;
        this.rightAscensionMinutes = rightAscensionMinutes;
        this.declination = declination;
        this.starName = starName;
    }
}
