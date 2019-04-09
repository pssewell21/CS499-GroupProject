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
import starmap.Calculation;
import starmap.Objects.Messier;

/**
 *
 * @author pssewell21
 */
public class MessierDataReader extends DataReader
{
    @Override
    public ArrayList<Messier> readData()
    {
        String filePath = "./src/resources/MessierObjects.csv";
        
        File file = new File(filePath);
        //System.out.println(filePath);        
        
        ArrayList<Messier> list = new ArrayList<>();
        
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
                    
                    int raHours = Integer.parseInt(lineItems[2]);
                    int raMinutes = Integer.parseInt(lineItems[3]);
                    int raSeconds = Integer.parseInt(lineItems[4]);
                    
                    int declinationDegrees = Integer.parseInt(lineItems[8]);
                    int declinationMinutes = Integer.parseInt(lineItems[9]);
                    int declinationSeconds = Integer.parseInt(lineItems[10]);
                    String declinationDirection = lineItems[11];
                    
                    if (declinationDirection.equalsIgnoreCase("N"))
                    {
                        declinationDirection = "North";
                    }
                    else if (declinationDirection.equalsIgnoreCase("S"))
                    {
                        declinationDirection = "South";
                    }
                    
                    double rightAscension = Calculation.getDecimalHours(raHours, 
                            raMinutes, raSeconds);
                    
                    double declination = Calculation.getDecimalCoordinate(
                            declinationDegrees, declinationMinutes, 
                            declinationSeconds, declinationDirection);
                
                    String name = lineItems[12];

                    Messier messier = new Messier(rightAscension, declination, name);
                
                    list.add(messier);
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
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        return list;
    }
}
