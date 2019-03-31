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
        System.out.println(filePath);        
        
        ArrayList<Star> list = new ArrayList<>();
        
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
            int columnCount = 0;
            
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
                
                columnCount = commas + 1;
                System.out.println(columnCount + " columns found");
            }
            
            //starMapData = new String[rowCount][columnCount];
            
            reader = new BufferedReader(new FileReader(file));
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
                    double absoluteMagnitude = Double.parseDouble(lineItems[11]);

//                    if (name.equalsIgnoreCase("Polaris")
//                            || name.equalsIgnoreCase("Diphda")
//                            || name.equalsIgnoreCase("Mirach")
//                            || name.equalsIgnoreCase("Alphard")
//                            || name.equalsIgnoreCase("Tarazed")
//                            || name.equalsIgnoreCase("Alnair"))
//                    {
                    Star star = new Star(name, rightAscension, declination, distance, absoluteMagnitude);  

                    list.add(star);                      
//                    }
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
