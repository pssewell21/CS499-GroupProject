/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.DataReaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import starmap.Objects.ConstellationPoint;

/**
 *
 * @author pssewell21
 */
public class ConstellationPointDataReader extends DataReader
{
    @Override
    public ArrayList<ConstellationPoint> readData()
    {
        String filePath = "./src/resources/ConstellationLines.csv";
        
        File file = new File(filePath);
        //System.out.println(filePath);        
        
        ArrayList<ConstellationPoint> list = new ArrayList<>();
        
        try
        {
            String lineFromFile;
            
            BufferedReader reader = new BufferedReader(new FileReader(file));            
            int rowCount = 0;
            
            while ((reader.readLine()) != null)
            {
                rowCount++;
            }
            
            //System.out.println(rowCount + " rows found");
            
            reader = new BufferedReader(new FileReader(file));
            
            if ((lineFromFile = reader.readLine()) != null)
            {
                int commas = 0;
                        
                for(int i = 0; i < lineFromFile.length(); i++) 
                {
                    if (lineFromFile.charAt(i) == ',') 
                    {
                        commas++;
                    }
                }
                
                int columnCount = commas + 1;
                //System.out.println(columnCount + " columns found");
            }
            
            reader = new BufferedReader(new FileReader(file));
            int i = 0;
            
            while ((lineFromFile = reader.readLine()) != null)
            {
                if (i > 0)
                {
                    String[] lineItems = lineFromFile.split(",");
                
                    String name = lineItems[0];
                    int rightAscensionHours = Integer.parseInt(lineItems[1]);
                    double rightAscensionMinutes = Double.parseDouble(lineItems[2]);
                    double declination = Double.parseDouble(lineItems[3]);                    
                    String starName;
                    
                    if (lineItems.length == 5)
                    {
                        starName = lineItems[4];
                    }
                    else
                    {
                        starName = "";
                    }
                    
                    double rightAscention = rightAscensionHours + (rightAscensionMinutes / 60.0);

                    ConstellationPoint constellationPoint = new ConstellationPoint(name, rightAscention, declination, starName);
                
                    list.add(constellationPoint);
                }
                
                i++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found at: " + filePath + "\n" + e.toString());
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
        
        return list;
    }
}
