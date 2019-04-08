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

/**
 *
 * @author dinabrown
 */
public class PlanetTest {
    
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
    @org.junit.Test
    public void testPlanet_meanSiderealTime() {
        System.out.println("planet_meanSiderealTime");
        double new_longitude = 0.0;
        LocalTime new_time = null;
        int year = 0;
        int month = 0;
        int day = 0;
        Planet instance = null;
        double expResult = 0.0;
        double result = instance.planet_meanSiderealTime(new_longitude, new_time, year, month, day);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of planet_true_anomaly method, of class Planet.
     */
    @org.junit.Test
    public void testPlanet_true_anomaly() {
        System.out.println("planet_true_anomaly");
        double M = 0.0;
        double e = 0.0;
        Planet instance = null;
        double expResult = 0.0;
        double result = instance.planet_true_anomaly(M, e);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of planet_mod2Pi method, of class Planet.
     */
    @org.junit.Test
    public void testPlanet_mod2Pi() {
        System.out.println("planet_mod2Pi");
        double X = 0.0;
        Planet instance = null;
        double expResult = 0.0;
        double result = instance.planet_mod2Pi(X);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of planet_convertRightAscensionToHrsMinSec method, of class Planet.
     */
    @org.junit.Test
    public void testPlanet_convertRightAscensionToHrsMinSec() {
        System.out.println("planet_convertRightAscensionToHrsMinSec");
        double RA = 0.0;
        Planet instance = null;
        instance.planet_convertRightAscensionToHrsMinSec(RA);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of planet_convertDeclinationToDegMinSec method, of class Planet.
     */
    @org.junit.Test
    public void testPlanet_convertDeclinationToDegMinSec() {
        System.out.println("planet_convertDeclinationToDegMinSec");
        double Dec = 0.0;
        Planet instance = null;
        instance.planet_convertDeclinationToDegMinSec(Dec);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of planet_getIntermediateValues method, of class Planet.
     */
    @org.junit.Test
    public void testPlanet_getIntermediateValues() {
        System.out.println("planet_getIntermediateValues");
        double julianDate = 0.0;
        LocalDateTime dateTime = null;
        Planet instance = null;
        instance.planet_getIntermediateValues(julianDate, dateTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateHorizonCoordinates method, of class Planet.
     */
    @org.junit.Test
    public void testCalculateHorizonCoordinates() {
        System.out.println("calculateHorizonCoordinates");
        double latitude = 0.0;
        double longitude = 0.0;
        LocalTime greenwichSiderealTime = null;
        Planet instance = null;
        instance.calculateHorizonCoordinates(latitude, longitude, greenwichSiderealTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
