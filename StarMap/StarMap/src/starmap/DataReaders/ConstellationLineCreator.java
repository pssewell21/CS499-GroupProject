/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.DataReaders;

import java.util.ArrayList;
import starmap.Objects.ConstellationLine;
import starmap.Objects.ConstellationPoint;

/**
 *
 * @author pssewell21
 */
public class ConstellationLineCreator 
{
    public ArrayList<ConstellationLine> GetConstellationLineList(ArrayList<ConstellationPoint> constellationPointList)
    {
        ArrayList<ConstellationLine> constellationLineList = new ArrayList<>();
        
        for (int i = 0; i < constellationPointList.size() - 1; i++)
        {
            ConstellationPoint pointA = constellationPointList.get(i);
            ConstellationPoint pointB = constellationPointList.get(i + 1);
            
            // if both points are part of the same group of constellation lines
            if (pointA.name.equalsIgnoreCase(pointB.name))
            {                
                ConstellationLine constellationLine = new ConstellationLine(pointA, pointB);
                
                constellationLineList.add(constellationLine);
            }
        }
        
        return constellationLineList;
    }
}
