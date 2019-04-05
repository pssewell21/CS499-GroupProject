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
import starmap.Objects.Planet;

/**
 *
 * @author pssewell21
 */
public class PlanetDataReader extends DataReader
{
    public ArrayList<Planet> readData()
    {
        String filePath = "./src/resources/PlanetsNoCalculations.csv";
        
        File file = new File(filePath);
        System.out.println(filePath);        
        
        ArrayList<Planet> list = new ArrayList<>();
        
        try
        {
            String lineFromFile;
            
            BufferedReader reader = new BufferedReader(new FileReader(file));            
            int rowCount = 0;
            
            while ((reader.readLine()) != null)
            {
                rowCount++;
            }
            
            System.out.println(rowCount + " rows found");
            
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
                System.out.println(columnCount + " columns found");
            }
            
            reader = new BufferedReader(new FileReader(file));
            int i = 0;
            
            while ((lineFromFile = reader.readLine()) != null)
            {
                if (i > 0)
                {
                    String[] lineItems = lineFromFile.split(",");
                
                    String name = lineItems[0];
                    double lscal = Double.parseDouble(lineItems[1]);
                    double lprop = Double.parseDouble(lineItems[2]);
                    double ascal = Double.parseDouble(lineItems[3]);
                    double aprop = Double.parseDouble(lineItems[4]);
                    double escal = Double.parseDouble(lineItems[5]);
                    double eprop = Double.parseDouble(lineItems[6]);
                    double iscal = Double.parseDouble(lineItems[7]);
                    double iprop = Double.parseDouble(lineItems[8]);
                    double wscal = Double.parseDouble(lineItems[9]);
                    double wprop = Double.parseDouble(lineItems[10]);
                    double oscal = Double.parseDouble(lineItems[11]);
                    double oprop = Double.parseDouble(lineItems[12]);

                    Planet planet = new Planet(name, lscal, lprop, ascal, aprop, 
                            escal, eprop, iscal, iprop, wscal, wprop, oscal, oprop);
                
                    list.add(planet);
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
