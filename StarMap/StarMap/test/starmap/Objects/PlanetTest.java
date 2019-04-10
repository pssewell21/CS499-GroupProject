/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Assert;

/**
 *
 * @author dinabrown
 */
public class PlanetTest {
    
    double g_RADS = Math.PI / 180.0;
    
    public PlanetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of planet_meanSiderealTime method, of class Planet.
     */
//    @org.junit.Test
//    public void testPlanet_meanSiderealTime() {
//        System.out.println("planet_meanSiderealTime()");
//        
//        
//        Planet earth = new Planet("Earth", 100.46435, 129597740.6, 1.00000011,
//                                    0.00000005, 0.01671022, 0.00003804, 
//                                    0.00005, 46.94, 102.94719, 1198.28,
//                                    -11.26064, 18228.25);
//        
//        double new_longitude = -86.64638;
//        LocalTime hr, min, sec;
//
//        LocalTime new_time = null; //16:51:29
//        int year = 2018;
//        int month = 1;
//        int day = 23;
//        //Planet instance = null;
//        double expResult = 0.0;
//        double result = earth.planet_meanSiderealTime(new_longitude, new_time, year, month, day);
//        //assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * METHOD: testPlanet_true_anomaly()
     * 
     * DESCRIPTION: returns a true anomaly value
     */
    @org.junit.Test
    public void test_planet_true_anomaly()
    {
        System.out.println("Testing planet_true_anomaly()");
        double M = 2.0;
        double e = 1.0;
        
        Planet earth = new Planet("Earth", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0);
        
        double expResult = 3.141592653589793;
        double result = earth.planet_true_anomaly(M, e);
        Assert.assertEquals("ERROR", expResult, result, 0.0001);
        
    } // End test_planet_true_anomaly()

    /**
     * METHOD: test_planet_mod2Pi()
     * 
     * DESCRIPTION: returns a converted radian value on [0,360] interval 
     *              if it's over 360.
     */
    @org.junit.Test
    public void test_planet_mod2Pi()
    {
        System.out.println("Testing planet_mod2Pi()");
        double test_values[] = {0, 90 * g_RADS, 180 * g_RADS, 400 * g_RADS, 450 * g_RADS};
        
        double expResult[] = {
                            0.0,
             1.5707963267948966,
              3.141592653589793, 
             0.6981317007977321,
             1.5707963267948966
        };

        double result;
        Planet earth = new Planet("Earth", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0);
        
        for(int i = 0; i < test_values.length; i++)
        {
            result = earth.planet_mod2Pi(test_values[i]);
            assertEquals("ERROR", expResult[i], result, 0.0001);
        }
        
    } // End test_planet_mod2Pi()

    /**
     * METHOD: test_planet_convertRightAscensionToHrsMinSec()
     * 
     * DESCRIPTION: 
     */
    @org.junit.Test
    public void test_planet_convertRightAscensionToHrsMinSec()
    {
        System.out.println("planet_convertRightAscensionToHrsMinSec");
        double RA = 0.0;
        Planet instance = null;
        instance.planet_convertRightAscensionToHrsMinSec(RA);

    } // end test_planet_convertRightAscensionToHrsMinSec()
    
    
//    /**
//     * Test of planet_convertDeclinationToDegMinSec method, of class Planet.
//     */
//    @org.junit.Test
//    public void testPlanet_convertDeclinationToDegMinSec() {
//        System.out.println("planet_convertDeclinationToDegMinSec");
//        double Dec = 0.0;
//        Planet instance = null;
//        instance.planet_convertDeclinationToDegMinSec(Dec);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of planet_getIntermediateValues method, of class Planet.
//     */
//    @org.junit.Test
//    public void testPlanet_getIntermediateValues() {
//        System.out.println("planet_getIntermediateValues");
//        double julianDate = 0.0;
//        LocalDateTime dateTime = null;
//        Planet instance = null;
//        instance.planet_getIntermediateValues(julianDate, dateTime);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of calculateHorizonCoordinates method, of class Planet.
//     */
//    @org.junit.Test
//    public void testCalculateHorizonCoordinates() {
//        System.out.println("calculateHorizonCoordinates");
//        double latitude = 0.0;
//        double longitude = 0.0;
//        LocalTime greenwichSiderealTime = null;
//        Planet instance = null;
//        instance.calculateHorizonCoordinates(latitude, longitude, greenwichSiderealTime);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
