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
        
        //Step 1,2: Calculate the elements of the planetary orbit of the planet and Earth:
        Planet earth_sun = new Planet("Earth", 100.46435, 129597740.6, 1.00000011,
                                    0.00000005, 0.01671022, 0.00003804, 
                                    0.00005, 46.94, 102.94719, 1198.28,
                                    -11.26064, 18228.25);
        earth_sun.calculate(3);
        
        Planet mars = new Planet("Mars", 355.45332, 68905103.78, 1.52366231,
                                    0.00007221, 0.09341233, 0.00011902, 
                                    1.85061, 25.47, 336.04084, 1560.78,
                                    49.57854, 1020.19);
        //mars.calculate(4);
        
        
        
        
        
        
        
        Planet mercury = new Planet("Mercury", 252.25084, 538101628.3, 0.38709893,
                                    0.00000066, 0.20563069, 0.00002527, 7.00487,
                                    23.51, 77.45645, 573.57, 48.33167, 446.3);
        //mercury.calculate(1);
        
        Planet venus = new Planet("Venus", 181.97973, 210664136.1, 0.72333199,
                                    0.00000092, 0.00677323, 0.00004938, 
                                    3.39471, 2.86, 131.53298, 108.8,
                                    76.68069, 76.68069);
        //venus.calculate(2);
        
        Planet jupiter = new Planet("Jupiter", 34.40438, 10925078.35, 5.20336301,
                                    0.00060737, 0.04839266, 0.0001288, 1.3053,
                                    -4.15, 14.75385, 839.93, 100.55615, 1217.17);
        //jupiter.calculate(5);
        
        Planet saturn = new Planet("Saturn", 49.94432, 4401052.95, 9.53707032,
                                    0.0030153, 0.0541506, 0.00036762, 2.48446,
                                    6.11, 92.43194, 1948.89, 113.71504, 1591.05);
        //saturn.calculate(6);
        
        Planet uranus = new Planet("Uranus",  313.23218, 1542547.79, 19.19126393,
                                    0.00152025, 0.04716771, 0.0001915, 0.76986,
                                    2.09, 170.96424, 1312.56, 74.22988, 1681.4);
        //uranus.calculate(7);
        
        Planet neptune = new Planet("Neptune", 304.88003, 786449.21, 30.06896348,
                                    0.00125196, 0.00858587,0.00125196, 1.76917,
                                    3.64, 44.97135, 844.43, 131.72169, 151.25);
        //neptune.calculate(8);
        
        Planet pluto = new Planet("Pluto", 238.92881, 522747.9, 39.48168677, 
                                    0.00076912, 0.24880766, 0.00006465, 17.14175,
                                    11.07, 224.06676, 132.25, 110.30347, 37.33);
        //pluto.calculate(9);
        
//        double mLongitude = 738.167166583533;
//        double perihelion = 2.1878065633754016;
//        double M = mLongitude - perihelion;
//        double ecc = 0.014149703597535935;
//        double e = Math.toRadians(0.8107182974998867);
//       
//       System.out.println("");
//       System.out.println("mLongitude value = " + mLongitude + " degrees is " + Math.toRadians(mLongitude) + " radians.");
//       System.out.println("Perihelion value = " + perihelion + " degrees is " + Math.toRadians(perihelion) + " radians.");
//       System.out.println("M = mLongitude - Perihelion = " + M + " degrees is " 
//                          + Math.toRadians(mLongitude - perihelion) + " radians.");
//       //System.out.println("Eccentricity value in degrees = " + Math.toDegrees(ecc));
//       System.out.println("Eccentricity value from degrees to radians = " + Math.toRadians(0.8107182974998867));
//       System.out.println("Eccentricity value in radians = " + Math.toRadians(ecc));
//       System.out.println();
       
        // M = 12.845263059072469, e = 0.014149703597535935
        //System.out.println("e = " + e);
        
        //double E, E1;
        //E = M + e * Math.sin(M) * (1.0 + e * Math.cos(M)); //735.9900587001409
        //System.out.println("E = " + E);
        
//        E1 = E; //735.9900587001409
//        System.out.println("E1 = " + E1);
//        
//        E = E1 - (E1 - e * Math.sin(E1) - M) / (1 - e * Math.cos(E1)); //735.9900590259884
//        System.out.println("E updated = " + E);
//        System.out.println("E1 = " + E1); 
//        
//        
//        double absOfE_E1 = Math.abs(E - E1);
//        System.out.println("E - E1 = " + absOfE_E1);
//        
//        double oneE12 = 1.0 * e - 12;
//        System.out.println("1.0 * e - 12 = " + oneE12);
         
        //Should only do this calculation once:
//        do {
//            E1 = E; //735.9900587001409
//            System.out.println("E1 = " + E1);
//            E = E1 - (E1 - e * Math.sin(E1) - M) / (1 - e * Math.cos(E1)); //735.9900590259884
//            System.out.println("E updated = " + E);
//            double condition1 = E - E1;
//            double condition2 = 1.0 * e - 12;
//            System.out.println("E - E1 = " + condition1);
//            System.out.println("1.0 * e - 12 = " + condition2);
//        } while(Math.abs(E - E1) > (1.0 * e - 12)); //3.2584750897513004E-7 > -11.985850296402464
        
        //System.out.println("SUCCESS");

        //double misc = Math.abs(E - E1) > (1.0 * ecc - 12);
        //double E1, V;
        
//        do{
//            E1 = E;
//            E = E1 - (E1 - ecc * Math.sin(E1) - M) / (1 - ecc * Math.cos(E1));
//        } while(Math.abs(E - E1) > (1.0 * ecc - 12));
//        
//        V = 2 * Math.atan(Math.sqrt((1 + ecc) / (1 - ecc)) * Math.tan(0.5 * E));
//        
//        if(V < 0) V = V + (2* Math.PI);
        
//        double angleX = Math.toRadians(-90); //90. 450, 540
//        double resultB, resultA;
//        
//        resultB = angleX / (2 * Math.PI);
//        resultA = (2 * Math.PI) * (resultB - Math.abs(Math.floor(resultB)));
//        
//        
//        System.out.println("angleX in radians = " + angleX);
//        //System.out.println("resultB = " + resultB);
//        System.out.println("resultA = " + resultA);
//        
//        if(resultA < 0)
//            resultA = (2 * Math.PI) + resultA;
//        
//        System.out.println("new resultA = " + resultA);
        
        
        
    }
    
}
