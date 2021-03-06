/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import java.util.ArrayList;
import starmap.Objects.Constellation;
import starmap.Objects.ConstellationLine;
import starmap.Objects.ConstellationPoint;
import starmap.Objects.Messier;
import starmap.Objects.Moon;
import starmap.Objects.Planet;
import starmap.Objects.Star;

/**
 * OBJECT: MapPanel
 * 
 * DESCRIPTION: This object creates a new window that will display the generated
 *              image.
 * 
 * @author pssewell21 (code) and Dina Brown (javadoc)
 */
public class MapPanel extends JPanel
{
     /**
     *  GLOBAL VARIABLES
     */
    private final ArrayList<Star> starList;
    private final ArrayList<Constellation> constellationList;
    private final ArrayList<ConstellationLine> constellationLineList;
    private final ArrayList<Planet> planetList;
    private final ArrayList<Messier> messierList;
    private final Moon moon;
    
    // boolean flags that control which items are visible on the star map
    private final boolean starVisibilityFlag;
    private final boolean constellationVisibilityFlag;
    private final boolean planetVisibilityFlag;
    private final boolean moonVisibilityFlag;
    private final boolean messierVisibilityFlag;
    
    private final boolean starLabelVisibilityFlag;
    private final boolean constellationLabelVisibilityFlag;
    private final boolean planetLabelVisibilityFlag;
    private final boolean moonPhaseVisibilityFlag;
    private final boolean messierLabelVisibilityFlag;    
    
    // Constants used for defining the behavior of object being drawn
    private final int sizeMultiplier = 12;
    private final double objectDiameterMultiplier = 0.15;
    
    public int imageWidth = 360 * sizeMultiplier;
    public int imageHeight = 180 * sizeMultiplier;
            
    private final int horizontalGridLabelWidth = 20;
    private final int verticalGridLabelWidth = 28;
    private final int gridLabelHeight = 12;
    
    private final int horizontalGridLabelXOffset = 5;
    private final int horizontalGridLabelYOffset = -5;
    
    private final Color backgroundColor = Color.BLACK;
    private final Color gridLineColor = new Color(0, 255, 80);  
    private final Color starColor = Color.WHITE;
    private final Color solColor = Color.YELLOW; 
    private final Color constellationColor = Color.CYAN;   
    private final Color planetColor = Color.RED;   
    private final Color messierColor = Color.MAGENTA;
    private final Color moonColor = Color.LIGHT_GRAY;
    
     /**************************************************************************
     *
     * DESCRIPTION: Constructor for class MapPanel
     * 
     * @param starList
     * @param constellationList the east/west direction in the sky
     * @param constellationLineList
     * @param planetList
     * @param messierList
     * @param moon
     * @param starVisibilityFlag
     * @param starLabelVisibilityFlag 
     * @param constellationVisibilityFlag
     * @param constellationLabelVisibilityFlag 
     * @param planetVisibilityFlag 
     * @param planetLabelVisibilityFlag 
     * @param messierVisibilityFlag, 
     * @param messierLabelVisibilityFlag
     * @param moonVisibilityFlag
     * @param moonPhaseVisibilityFlag
     * 
    ***************************************************************************/
    public MapPanel(ArrayList<Star> starList, 
                    ArrayList<Constellation> constellationList, 
                    ArrayList<ConstellationLine> constellationLineList, 
                    ArrayList<Planet> planetList, 
                    ArrayList<Messier> messierList, 
                    Moon moon, 
                    boolean starVisibilityFlag, 
                    boolean starLabelVisibilityFlag, 
                    boolean constellationVisibilityFlag, 
                    boolean constellationLabelVisibilityFlag, 
                    boolean planetVisibilityFlag, 
                    boolean planetLabelVisibilityFlag, 
                    boolean messierVisibilityFlag, 
                    boolean messierLabelVisibilityFlag, 
                    boolean moonVisibilityFlag, 
                    boolean moonPhaseVisibilityFlag)
    {
        this.starList = starList; 
        this.constellationList = constellationList;
        this.constellationLineList = constellationLineList;
        this.planetList = planetList;
        this.messierList = messierList;
        this.moon = moon;
        this.starVisibilityFlag = starVisibilityFlag;
        this.starLabelVisibilityFlag = starLabelVisibilityFlag;
        this.constellationVisibilityFlag = constellationVisibilityFlag;
        this.constellationLabelVisibilityFlag = constellationLabelVisibilityFlag;
        this.planetVisibilityFlag = planetVisibilityFlag;
        this.planetLabelVisibilityFlag = planetLabelVisibilityFlag;
        this.messierVisibilityFlag = messierVisibilityFlag;
        this.messierLabelVisibilityFlag = messierLabelVisibilityFlag;
        this.moonVisibilityFlag = moonVisibilityFlag;
        this.moonPhaseVisibilityFlag = moonPhaseVisibilityFlag;
    } // End MapPanel()
    
    @Override
     /**************************************************************************
     * 
     * METHOD: paintComponent()
     * 
     * DESCRIPTION: draws the Star Map image
     * 
     * @param g
     * 
    ***************************************************************************/
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        setPreferredSize(new Dimension(imageWidth, imageHeight));        
        
        drawBackground(g2d);
        drawGrid(g2d);
        
        plotStars(g2d);
        plotConstellations(g2d);
        plotPlanets(g2d);
        plotMessierObjects(g2d);
        plotMoon(g2d);
    }
     /**************************************************************************
     * 
     * METHOD: plotStars()
     * 
     * DESCRIPTION: draws the stars
     * 
     * @param g2d
     * 
    ***************************************************************************/
    private void plotStars(Graphics2D g2d)
    {
        if (starVisibilityFlag)
        {
            g2d.setColor(starColor);

            for (Star star : starList)
            {
                if (star.magnitude > 6.0)
                {
                    continue;
                }    

                int starDiameter = (int)Math.round((7.0 - star.magnitude) * (sizeMultiplier * objectDiameterMultiplier));

                if (star.name.equalsIgnoreCase("Sol"))
                {
                    g2d.setColor(solColor);
                }

                int horizontalPosition;

                if (star.azimuth < 180)
                {
                    horizontalPosition = (int)Math.round((star.azimuth + 180) * sizeMultiplier);
                }
                else
                {
                    horizontalPosition = (int)Math.round((star.azimuth - 180) * sizeMultiplier);
                }

                int verticalPosition = (int)Math.round((90 + (-1 * star.elevation)) * sizeMultiplier);

                if (star.name.equalsIgnoreCase("Sol"))
                {
                    try
                    {
                        BufferedImage icon = ImageIO.read(new File("./src/resources/Icons/Sun-icon.png"));
                        Image resizedIcon = icon.getScaledInstance(starDiameter, starDiameter, Image.SCALE_DEFAULT);
                        g2d.drawImage(resizedIcon, horizontalPosition, verticalPosition, null);
                    }
                    catch (IOException e)
                    {
                        System.out.println(e.toString());
                    }
                }  
                else
                {
                    g2d.fillOval(horizontalPosition, verticalPosition, starDiameter, starDiameter);
                }

                if (starLabelVisibilityFlag && star.name.length() > 0)
                {
                    g2d.drawString(star.name, 
                            (int)Math.round(horizontalPosition + starDiameter + 2), 
                            (int)Math.round(verticalPosition + (starDiameter / 2) + (gridLabelHeight / 2)));                
                }

                //System.out.println("Drawing star " + star.name + " at " + star.azimuth + ", " + star.elevation);

                if (star.name.equalsIgnoreCase("Sol"))
                {
                    g2d.setColor(starColor);
                }
            }
        }
    } // End plotStars()
    /**************************************************************************
     * 
     * METHOD: plotConstellations()
     * 
     * DESCRIPTION: draws the constellations
     * 
     * @param g2d
     * 
    ***************************************************************************/
    private void plotConstellations(Graphics2D g2d)
    {
        if (constellationVisibilityFlag)
        {
            g2d.setColor(constellationColor);            
            
            for (Constellation constellation : constellationList)
            {
                int objectDiameter = (int)Math.round(3.0 * sizeMultiplier * objectDiameterMultiplier);

                int horizontalPosition;

                if (constellation.azimuth < 180)
                {
                    horizontalPosition = (int)Math.round((constellation.azimuth + 180) * sizeMultiplier);
                }
                else
                {
                    horizontalPosition = (int)Math.round((constellation.azimuth - 180) * sizeMultiplier);
                }

                int verticalPosition = (int)Math.round((90 + (-1 * constellation.elevation)) * sizeMultiplier);

                g2d.fillOval(horizontalPosition, verticalPosition, objectDiameter, objectDiameter);  

                if (constellationLabelVisibilityFlag && constellation.name.length() > 0)
                {
                    g2d.drawString(constellation.name, 
                            (int)Math.round(horizontalPosition + objectDiameter + 2), 
                            (int)Math.round(verticalPosition + (objectDiameter / 2) + (gridLabelHeight / 2)));                
                }
            }  
            
            for (ConstellationLine constellationLine : constellationLineList)
            {
                ConstellationPoint pointA = constellationLine.pointA;
                ConstellationPoint pointB = constellationLine.pointB;
                
                // If the line will wrap around the image horizontally, draw 2 lines
                if ((pointA.azimuth > 180 && pointB.azimuth <= 180 && pointA.azimuth - pointB.azimuth < 180) 
                        || (pointA.azimuth <= 180 && pointB.azimuth > 180 && pointB.azimuth - pointA.azimuth < 180))
                {
                    // line from point A to off screen                    
                    double azimuthDifference = pointB.azimuth - pointA.azimuth;
                    double elevationDifference = pointB.elevation - pointA.elevation;
                    
                    int pointAHorizontalPosition;
                            
                    if (pointA.azimuth < 180)
                    {
                        pointAHorizontalPosition = (int)Math.round((pointA.azimuth + 180) * sizeMultiplier);
                    }
                    else
                    {
                        pointAHorizontalPosition = (int)Math.round((pointA.azimuth - 180) * sizeMultiplier);
                    }

                    int pointAVerticalPosition = (int)Math.round((90 + (-1 * pointA.elevation)) * sizeMultiplier);
                    
                    int pointBHorizontalPosition = (int)Math.round(pointAHorizontalPosition + (azimuthDifference * sizeMultiplier));
                    int pointBVerticalPosition = (int)Math.round(pointAVerticalPosition - (elevationDifference * sizeMultiplier));
                    
                    g2d.drawLine(pointAHorizontalPosition, pointAVerticalPosition, pointBHorizontalPosition, pointBVerticalPosition);
                            
                    // line from point B to off screen       
                    azimuthDifference = pointA.azimuth - pointB.azimuth;
                    elevationDifference = pointA.elevation - pointB.elevation;
                            
                    if (pointB.azimuth < 180)
                    {
                        pointBHorizontalPosition = (int)Math.round((pointB.azimuth + 180) * sizeMultiplier);
                    }
                    else
                    {
                        pointBHorizontalPosition = (int)Math.round((pointB.azimuth - 180) * sizeMultiplier);
                    }

                    pointBVerticalPosition = (int)Math.round((90 + (-1 * pointB.elevation)) * sizeMultiplier);
                    
                    pointAHorizontalPosition = (int)Math.round(pointBHorizontalPosition + (azimuthDifference * sizeMultiplier));
                    pointAVerticalPosition = (int)Math.round(pointBVerticalPosition - (elevationDifference * sizeMultiplier));
                    
                    g2d.drawLine(pointBHorizontalPosition, pointBVerticalPosition, pointAHorizontalPosition, pointAVerticalPosition);
                }
                else
                {
                    int pointAHorizontalPosition;

                    if (pointA.azimuth < 180)
                    {
                        pointAHorizontalPosition = (int)Math.round((pointA.azimuth + 180) * sizeMultiplier);
                    }
                    else
                    {
                        pointAHorizontalPosition = (int)Math.round((pointA.azimuth - 180) * sizeMultiplier);
                    }

                    int pointAVerticalPosition = (int)Math.round((90 + (-1 * pointA.elevation)) * sizeMultiplier);

                    int pointBHorizontalPosition;

                    if (pointB.azimuth < 180)
                    {
                        pointBHorizontalPosition = (int)Math.round((pointB.azimuth + 180) * sizeMultiplier);
                    }
                    else
                    {
                        pointBHorizontalPosition = (int)Math.round((pointB.azimuth - 180) * sizeMultiplier);
                    }

                    int pointBVerticalPosition = (int)Math.round((90 + (-1 * pointB.elevation)) * sizeMultiplier);

                    g2d.drawLine(pointAHorizontalPosition, pointAVerticalPosition, pointBHorizontalPosition, pointBVerticalPosition);                    
                }
            }
        }
    }  // End plotConstellations()
    
    /**************************************************************************
     * 
     * METHOD: plotPlanets()
     * 
     * DESCRIPTION: draws planets
     * 
     * @param g2d
     * 
    ***************************************************************************/
    private void plotPlanets(Graphics2D g2d)
    {
        if (planetVisibilityFlag)
        {
            g2d.setColor(planetColor);

            for (Planet planet : planetList) 
            {
                // Do not draw Earth/Sun, sun is drawn by the star class
                if (!planet.name.equalsIgnoreCase("Earth/Sun"))
                {
                    int objectDiameter = (int)Math.round(18.0 * sizeMultiplier * objectDiameterMultiplier);

                    int horizontalPosition;

                    if (planet.azimuth < 180)
                    {
                        horizontalPosition = (int)Math.round((planet.azimuth + 180) * sizeMultiplier);
                    }
                    else
                    {
                        horizontalPosition = (int)Math.round((planet.azimuth - 180) * sizeMultiplier);
                    }

                    int verticalPosition = (int)Math.round((90 + (-1 * planet.elevation)) * sizeMultiplier);

                    try
                    {
                        BufferedImage icon = ImageIO.read(new File("./src/resources/Icons/" + planet.name + "-icon.png"));
                        Image resizedIcon = icon.getScaledInstance(objectDiameter, objectDiameter, Image.SCALE_DEFAULT);
                        g2d.drawImage(resizedIcon, horizontalPosition, verticalPosition, null);
                    }
                    catch (IOException e)
                    {
                        System.out.println(e.toString());
                    }

                    if (planetLabelVisibilityFlag && planet.name.length() > 0)
                    {
                        g2d.drawString(planet.name, 
                                (int)Math.round(horizontalPosition + objectDiameter + 2), 
                                (int)Math.round(verticalPosition + (objectDiameter / 2) + (gridLabelHeight / 2)));                
                    }
                }
            }  
        }
    }  // End plotPlanets()
    
    /**************************************************************************
     * 
     * METHOD: plotMessierObjects()
     * 
     * DESCRIPTION: draws the Messier objects
     * 
     * @param g2d
     * 
    ***************************************************************************/
    private void plotMessierObjects(Graphics2D g2d)
    {
        if (messierVisibilityFlag)
        {
            g2d.setColor(messierColor);

            for (Messier messier : messierList)
            {
                int objectDiameter = (int)Math.round(3.0 * sizeMultiplier * objectDiameterMultiplier);

                int horizontalPosition;

                if (messier.azimuth < 180)
                {
                    horizontalPosition = (int)Math.round((messier.azimuth + 180) * sizeMultiplier);
                }
                else
                {
                    horizontalPosition = (int)Math.round((messier.azimuth - 180) * sizeMultiplier);
                }

                int verticalPosition = (int)Math.round((90 + (-1 * messier.elevation)) * sizeMultiplier);

                g2d.fillOval(horizontalPosition, verticalPosition, objectDiameter, objectDiameter);  

                if (messierLabelVisibilityFlag && messier.name.length() > 0)
                {
                    g2d.drawString(messier.name, 
                            (int)Math.round(horizontalPosition + objectDiameter + 2), 
                            (int)Math.round(verticalPosition + (objectDiameter / 2) + (gridLabelHeight / 2)));                
                }
            }
        }
    } // End plotMessierObjects()
    /**************************************************************************
     * 
     * METHOD: plotMoon()
     * 
     * DESCRIPTION: draws Moon
     * 
     * @param g2d
     * 
    ***************************************************************************/
    private void plotMoon(Graphics2D g2d)
    {
        if (moonVisibilityFlag)
        {
            g2d.setColor(moonColor);
            
            int objectDiameter = (int)Math.round(18.0 * sizeMultiplier * objectDiameterMultiplier);

            int horizontalPosition;

            if (moon.azimuth < 180)
            {
                horizontalPosition = (int)Math.round((moon.azimuth + 180) * sizeMultiplier);
            }
            else
            {
                horizontalPosition = (int)Math.round((moon.azimuth - 180) * sizeMultiplier);
            }

            int verticalPosition = (int)Math.round((90 + (-1 * moon.elevation)) * sizeMultiplier);

            try
            {
                BufferedImage icon = ImageIO.read(new File("./src/resources/Icons/" + moon.phase + "-moon-icon.png"));
                Image resizedIcon = icon.getScaledInstance(objectDiameter, objectDiameter, Image.SCALE_DEFAULT);
                g2d.drawImage(resizedIcon, horizontalPosition, verticalPosition, null);
            }
            catch (IOException e)
            {
                System.out.println(e.toString());
            }
            
            //g2d.fillOval(horizontalPosition, verticalPosition, objectDiameter, objectDiameter);  

            //TODO: Implement showing moon phase in some way
            if (moonPhaseVisibilityFlag && moon.name.length() > 0)
            {
                g2d.drawString(moon.phase, 
                        (int)Math.round(horizontalPosition + objectDiameter + 2), 
                        (int)Math.round(verticalPosition + (objectDiameter / 2) + (gridLabelHeight / 2)));                
            }

            //System.out.println("Drawing moon " + moon.phase + " at " + moon.azimuth + ", " + moon.elevation);
        }
    } // plotMoon()
     /**************************************************************************
     * 
     * METHOD: plotBackground()
     * 
     * DESCRIPTION: draws background
     * 
     * @param g2d
     * 
    ***************************************************************************/  
    private void drawBackground(Graphics2D g2d)
    {
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, imageWidth, imageHeight);
    }
    
    private void drawGrid(Graphics2D g2d)
    {
        drawHorizontalGridLines(g2d);
        drawVerticalGridLines(g2d);  
    }
    
    private void drawHorizontalGridLines(Graphics2D g2d)
    {
        g2d.setColor(gridLineColor);
        int yValue = imageHeight / 18 * 0;
        String labelText = "90°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
                       
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 1;
        labelText = "80°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 2;
        labelText = "70°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 3;
        labelText = "60°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 4;
        labelText = "50°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 5;
        labelText = "40°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 6;
        labelText = "30°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 7;
        labelText = "20°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 8;
        labelText = "10°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 9;
        labelText = "0°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 10;
        labelText = "-10°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 11;
        labelText = "-20°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 12;
        labelText = "-30°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 13;
        labelText = "-40°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 14;
        labelText = "-50°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 15;
        labelText = "-60°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 16;
        labelText = "-70°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 17;
        labelText = "-80°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
        
        g2d.setColor(gridLineColor);
        yValue = imageHeight / 18 * 18;
        labelText = "-90°";
        g2d.drawString(labelText, horizontalGridLabelXOffset, yValue + horizontalGridLabelYOffset);
        g2d.drawString(labelText, imageWidth - horizontalGridLabelXOffset - horizontalGridLabelWidth, yValue + horizontalGridLabelYOffset);
        g2d.drawLine(0, yValue, imageWidth, yValue);
    } // drawBackground()
    
     /**************************************************************************
     * 
     * METHOD: drawVerticalGridLines()
     * 
     * DESCRIPTION: draws vertical grid lines
     * 
     * @param g2d
     * 
    ***************************************************************************/
    private void drawVerticalGridLines(Graphics2D g2d)
    {        
        g2d.setColor(gridLineColor);
        int xValue = imageWidth / 36 * 1;
        String labelText = "190°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 2;
        labelText = "200°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 3;
        labelText = "210°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 4;
        labelText = "220°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 5;
        labelText = "230°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 6;
        labelText = "240°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 7;
        labelText = "250°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 8;
        labelText = "260°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 9;
        labelText = "270°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 10;
        labelText = "280°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 11;
        labelText = "290°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 12;
        labelText = "300°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 13;
        labelText = "310°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 14;
        labelText = "320°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 15;
        labelText = "330°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 16;
        labelText = "340°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 17;
        labelText = "350°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 18;
        labelText = "0°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 19;
        labelText = "10°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 20;
        labelText = "20°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 21;
        labelText = "30°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 22;
        labelText = "40°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 23;
        labelText = "50°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 24;
        labelText = "60°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 25;
        labelText = "70°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 26;
        labelText = "80°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 27;
        labelText = "90°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 28;
        labelText = "100°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 29;
        labelText = "110°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 30;
        labelText = "120°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 31;
        labelText = "130°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 32;
        labelText = "140°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 33;
        labelText = "150°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 34;
        labelText = "160°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 35;
        labelText = "170°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);        
        
        xValue = imageWidth / 36 * 36;
        labelText = "180°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, imageHeight - gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
    } // End drawVerticalGridLines()
} // End MapPanel Class
