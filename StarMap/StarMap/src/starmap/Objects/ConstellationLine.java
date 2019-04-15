/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

/**
 * OBJECT: ConstellationLine
 * 
 * DESCRIPTION: This is class provides constellation lines that are used for
 *              drawing.
 *
 * @author pssewell21 (code) and Dina Brown (javadoc)
 */
public class ConstellationLine 
{
     /**
     *  GLOBAL VARIABLES
     */
    public ConstellationPoint pointA;
    public ConstellationPoint pointB;
    
    /**************************************************************************
     *
     * DESCRIPTION: Constructor for class ConstellationLine
     * 
     * @param pointA beginning point
     * @param pointB ending point
     * 
    ***************************************************************************/
    public ConstellationLine(ConstellationPoint pointA, ConstellationPoint pointB)
    {
        this.pointA = pointA;
        this.pointB = pointB;
        
    } // End ConstellationLine()
} // End ConstellationLine Class
