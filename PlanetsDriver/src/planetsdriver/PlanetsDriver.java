/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetsdriver;
import java.io.*;

/**
 *
 * @author dinabrown
 */
public class PlanetsDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Planet object consists of Name, Lscal, Lprop, Ascal, Aprop, Escal,
        //                          Eprop, Iscal, Wscal, Wprop, Osca, Oprop
        
        Planet mercury = new Planet("Mercury", 252.25084, 538101628.3, 0.38709893,
                                    0.00000066, 0.20563069, 0.00002527, 7.00487,
                                    23.51, 77.45645, 573.57, 48.33167, 446.3);
        mercury.calculate(1);
        
        Planet venus = new Planet("Venus", 181.97973, 210664136.1, 0.72333199,
                                    0.00000092, 0.00677323, 0.00004938, 
                                    3.39471, 2.86, 131.53298, 108.8,
                                    76.68069, 76.68069);
        venus.calculate(2);
        
        Planet earth_sun = new Planet("Earth", 100.46435, 129597740.6, 1.00000011,
                                    0.00000005, 0.01671022, 0.00003804, 
                                    0.00005, 46.94, 102.94719, 1198.28,
                                    -11.26064, 18228.25);
        earth_sun.calculate(3);
        
        Planet mars = new Planet("Mars", 355.45332, 68905103.78, 1.52366231,
                                    0.00007221, 0.09341233, 0.00011902, 
                                    1.85061, 25.47, 336.04084, 1560.78,
                                    49.57854, 1020.19);
        mars.calculate(4);
        
        Planet jupiter = new Planet("Jupiter", 34.40438, 10925078.35, 5.20336301,
                                    0.00060737, 0.04839266, 0.0001288, 1.3053,
                                    -4.15, 14.75385, 839.93, 100.55615, 1217.17);
        jupiter.calculate(5);
        
        Planet saturn = new Planet("Saturn", 49.94432, 4401052.95, 9.53707032,
                                    0.0030153, 0.0541506, 0.00036762, 2.48446,
                                    6.11, 92.43194, 1948.89, 113.71504, 1591.05);
        saturn.calculate(6);
        
        Planet uranus = new Planet("Uranus",  313.23218, 1542547.79, 19.19126393,
                                    0.00152025, 0.04716771, 0.0001915, 0.76986,
                                    2.09, 170.96424, 1312.56, 74.22988, 1681.4);
        uranus.calculate(7);
        
        Planet neptune = new Planet("Neptune", 304.88003, 786449.21, 30.06896348,
                                    0.00125196, 0.00858587,0.00125196, 1.76917,
                                    3.64, 44.97135, 844.43, 131.72169, 151.25);
        neptune.calculate(8);
        
        Planet pluto = new Planet("Pluto", 238.92881, 522747.9, 39.48168677, 
                                    0.00076912, 0.24880766, 0.00006465, 17.14175,
                                    11.07, 224.06676, 132.25, 110.30347, 37.33);
        pluto.calculate(9);
        
    }
    
}
