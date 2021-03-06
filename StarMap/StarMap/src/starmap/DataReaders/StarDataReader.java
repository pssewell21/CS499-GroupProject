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
import starmap.Objects.Star;

/**
 *
 * @author pssewell21
 */
public class StarDataReader extends DataReader
{
    @Override
    public ArrayList<Star> readData()
    {
        String filePath = "./src/resources/hyg.csv";
        
        File file = new File(filePath);
        //System.out.println(filePath);        
        
        ArrayList<Star> list = new ArrayList<>();
        
        try
        {
            String lineFromFile;
            
            BufferedReader reader = new BufferedReader(new FileReader(file)); 
            
            int i = 0;
            
            while ((lineFromFile = reader.readLine()) != null)
            {
                if (i > 0)
                {
                    String[] lineItems = lineFromFile.split(",");
                
                    String name = lineItems[6];
                    double rightAscension = Double.parseDouble(lineItems[7]);
                    double declination = Double.parseDouble(lineItems[8]);
                    double distance = Double.parseDouble(lineItems[9]);
                    double magnitude = Double.parseDouble(lineItems[10]);
                    
                    Star star = new Star(name, rightAscension, declination, distance, magnitude);  

                    list.add(star);
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
