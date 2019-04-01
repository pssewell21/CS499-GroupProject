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
import java.util.ArrayList;
import javax.swing.JPanel;
import starmap.Objects.Star;

/**
 *
 * @author pssewell21
 */
public class MapPanel extends JPanel
{
    private final int sizeMultiplier = 6;
    
    public int imageWidth = 360 * sizeMultiplier;
    public int imageHeight = 180 * sizeMultiplier;
            
    private final int horizontalGridLabelWidth = 20;
    private final int verticalGridLabelWidth = 28;
    private final int gridLabelHeight = 12;
    
    private final int horizontalGridLabelXOffset = 5;
    private final int horizontalGridLabelYOffset = -5;
    
    private final int verticalGridLabelXOffset = -1 * horizontalGridLabelWidth;
    private final int verticalGridLabelYOffset = 0;    
    
    private final Color backgroundColor = Color.BLACK;
    private final Color gridLineColor = new Color(0, 80, 225);  
    private final Color starColor = Color.WHITE;
    
    private final ArrayList<Star> starList;
    
    public MapPanel(ArrayList<Star> starList)
    {
        this.starList = starList;
    }
    
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        setPreferredSize(new Dimension(imageWidth, imageHeight));        
        
        drawBackground(g2d);
        drawGrid(g2d);
        
        plotStars(g2d);
    }
    
    private void plotStars(Graphics2D g2d)
    {
        g2d.setColor(starColor);
        
        int count = 0;
        
        for (Star star : starList)
        {
            if (star.absoluteMagnitude > 6.0)
            {
                continue;
            }
            
            int magnitudeValue = 2;
            
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
            
            g2d.drawOval(horizontalPosition, verticalPosition, magnitudeValue, magnitudeValue);
            g2d.fillOval(horizontalPosition, verticalPosition, magnitudeValue, magnitudeValue);  
            
            //System.out.println("Drawing star " + star.name + " at " + star.azimuth + ", " + star.elevation);
            
            count++;
        }  
        
        System.out.println("Count of stars plotted: " + count);
    }
    
    
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
    }
    
    private void drawVerticalGridLines(Graphics2D g2d)
    {
        int xValue = imageWidth / 36 * 0;
        String labelText = "180°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 1;
        labelText = "190°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 2;
        labelText = "200°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 3;
        labelText = "210°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 4;
        labelText = "220°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 5;
        labelText = "230°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 6;
        labelText = "240°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 7;
        labelText = "250°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 8;
        labelText = "260°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 9;
        labelText = "270°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 10;
        labelText = "280°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 11;
        labelText = "290°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 12;
        labelText = "300°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 13;
        labelText = "310°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 14;
        labelText = "320°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 15;
        labelText = "330°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 16;
        labelText = "340°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 17;
        labelText = "350°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 18;
        labelText = "0°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 19;
        labelText = "10°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 20;
        labelText = "20°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 21;
        labelText = "30°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 22;
        labelText = "40°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 23;
        labelText = "50°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 24;
        labelText = "60°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 25;
        labelText = "70°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 26;
        labelText = "80°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 27;
        labelText = "90°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 28;
        labelText = "100°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 29;
        labelText = "110°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 30;
        labelText = "120°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 31;
        labelText = "130°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 32;
        labelText = "140°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 33;
        labelText = "150°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 34;
        labelText = "160°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
        
        g2d.setColor(gridLineColor);
        xValue = imageWidth / 36 * 35;
        labelText = "170°";
        g2d.drawString(labelText, xValue - verticalGridLabelWidth, gridLabelHeight);
        g2d.drawLine(xValue, 0, xValue, imageHeight);
    }
}
