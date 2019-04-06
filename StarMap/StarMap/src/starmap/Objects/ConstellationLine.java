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
    public ConstellationPoint pointA;
    
    public ConstellationPoint pointB;
    
    public ConstellationLine(ConstellationPoint pointA, ConstellationPoint pointB)
    {
        this.pointA = pointA;
        this.pointB = pointB;
    }
}
