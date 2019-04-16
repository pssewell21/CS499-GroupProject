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
import starmap.Objects.Constellation;

/**
 *
 * @author pssewell21
 */
public class ConstellationDataReader extends DataReader
{
    public ArrayList<Constellation> readData()
    {
        String filePath = "./src/resources/Constellations.csv";
        
        File file = new File(filePath);
        //System.out.println(filePath);        
        
        ArrayList<Constellation> list = new ArrayList<>();
        
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
                
                    String name = lineItems[0];
                    double rightAscension = Double.parseDouble(lineItems[3]);
                    double declination = Double.parseDouble(lineItems[4]);

                    Constellation constellation = new Constellation(name, rightAscension, declination);
                
                    list.add(constellation);
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
