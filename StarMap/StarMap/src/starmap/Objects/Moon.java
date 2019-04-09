/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starmap.Objects;

import java.lang.String;
import java.lang.Math;
import java.text.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import starmap.Calculation;

/**
 *
 * @author Dina Brown and Patrick Sewell
 */
public class Moon extends CelestialObject
{
    public final static double RADS = Math.PI / 180.0;
    
    public int g_year;
    public double g_calander_year;
    public double g_calander_month;
    public double g_calander_day;
    public double g_JD;
    
    public double rightAscension;
    public double declination;            
    
    public double g_L_angle; // Moon mean LONGITUDE - L
    public double g_M_angle; // Sun mean ANOMALY - M
    public double g_Mp_angle; // Moon mean ANOMALY - M'
    public double g_D_angle; // Moon mean ELONGATION - D
    public double g_F_angle; // Mean DISTANCE of Moon - F
    public double g_ecc;  //eecentricity
    
    public String phase;
    
    public enum Moon_Phases{
        g_new_moon, 
        g_first_quarter, 
        g_full_moon, 
        g_last_quarter
    }
//    enum Moon_Phases{
//        g_p_new_moon, g_p_first_quarter, g_p_full_moon, g_p_last_quarter,
//        g_f_new_moon, g_f_first_quarter, g_f_full_moon, g_f_last_quarter,
//    }
    
     /**************************************************************************
     *
     * DESCRIPTION: Constructor for class Moon
     * 
    ***************************************************************************/
    public Moon()
    {
        name = "Moon";
    } // End Moon()
    
     /**************************************************************************
     * 
     * METHOD: getIntermediateValues()
     * 
     * DESCRIPTION: gets the calculated values for each planet
     * 
     * @param julianDate
     * 
    ***************************************************************************/
    public void moon_convertJulianDateToCalanderDate(double julianDate)
    {
        double A, B, C, D, E, F, Z, alpha;
        double JD;
        
        //Step 1:
        JD = julianDate + 0.5;
        
        //Step 2:
        Z = (int) JD;
        F = JD - Z;
        
        if(Z < 229161)
            A = Z;
        else
        {
            alpha = (int) ((Z - 1867216.25) / 36524.25);
            A = Z + 1 + alpha - (int) (alpha / 4);
        }
        //Step 3:
        B = A + 1524;
        C = (int) ((B - 122.1) / 365.25);
        D = (int) (365.25 * C);
        E = (int) ((B - D) / 30.6001);
        
        //Step 4: Get the Day, Month, and Year in Calendar Date
        g_calander_day = B - D - (int)((30.6001 * E) + F);
        
        if(E < 13.5)
            g_calander_month = E - 1;
        else
            g_calander_month = E - 13;
        
        if(g_calander_month > 2.5)
            g_calander_year = C - 4716;
        else
            g_calander_year = C - 4715;
        
        
    } //moon_convertJulianDateToCalanderDate
    
     /**************************************************************************
     * 
     * METHOD: getIntermediateValues()
     * 
     * DESCRIPTION: gets the calculated values for each planet
     * 
     * @param dateTime access the current year
     * 
    ***************************************************************************/
    public void getIntermediateValues(LocalDateTime dateTime)
    {
        // TODO: Remove julian date argument from method signature - PS
        
        // START MOON PHASE CALCULATIONS - PS
        double newMoonJulianDate = 2451549.5;
        double periodLength = 29.53;
        
        double julianDate = Calculation.getJulianDate(dateTime);
        
        double dateDifference = julianDate - newMoonJulianDate;
        
        double numNewMoons = dateDifference / periodLength;
        
        //System.out.println("Number of new moons since " + newMoonJulianDate + " = " + numNewMoons);
        
        double amountOfCycleElapsed = numNewMoons - (int)Math.floor(numNewMoons);
        
        //System.out.println("Amount of cycle elapsed " + amountOfCycleElapsed); 
        
        if (amountOfCycleElapsed > 0.97 || amountOfCycleElapsed <= 0.03)
        {
            phase = "NEW MOON";
        }
        else if (amountOfCycleElapsed > 0.03 || amountOfCycleElapsed <= 0.22)
        {
            phase = "WAXING CRESCENT";
        }
        else if (amountOfCycleElapsed > 0.22 || amountOfCycleElapsed <= 0.28)
        {
            phase = "FIRST QUARTER";
        }
        else if (amountOfCycleElapsed > 0.28 || amountOfCycleElapsed <= 0.47)
        {
            phase = "WAXING GIBBOUS";
        }
        else if (amountOfCycleElapsed > 0.47 || amountOfCycleElapsed <= 0.53)
        {
            phase = "FULL MOON";
        }
        else if (amountOfCycleElapsed > 0.53 || amountOfCycleElapsed <= 0.72)
        {
            phase = "WANING GIBBOUS";
        }
        else if (amountOfCycleElapsed > 0.72 || amountOfCycleElapsed <= 0.78)
        {
            phase = "THIRD QUARTER";
        }
        else if (amountOfCycleElapsed > 0.78 || amountOfCycleElapsed <= 0.97)
        {
            phase = "WANING CRESCENT";
        }
        else
        {
            phase = "ERROR OCCURRED";
        }
        // END MOON PHASE CALCULATIONS
        
        
        
        
        
        
        
        
        double k_new_moon, k_first_quarter, k_full_moon, k_last_quarter;
        DecimalFormat df_two = new DecimalFormat("##");
        
        
        double t_lunar, k_lunar;
        double JD; //2458489.000000
        g_year = dateTime.getYear();
        double updated_year = g_year + .087;
        
        k_lunar = (int) ((updated_year - 1900.0) * 12.3685);
        t_lunar = k_lunar / 1236.85;
        int local_fail = 0;
        
//        k_first_quarter = k_lunar + 0.25;
//        k_full_moon = k_lunar + .50;
//        k_last_quarter = k_lunar + .75;
        
        // Initializes new String objects to be used:
        //Year str_year = dateTime.getYear();
        
        int day_str_dateTime;
        int mon_str_dateTime;
        int year_str_dateTime;
        
//        double moon_phases[] = { 
//            k_lunar,
//            k_first_quarter,
//            k_full_moon,
//            k_last_quarter
//        };
//        
//        String phase_msgs[] = {
//            "The moon phase is a NEW MOON",
//            "The moon phase is a FIRST QUARTER MOON",
//            "The moon phase is a FULL MOON",
//            "The moon phase is a LAST QUARTER MOON"
//        };
//        
//        System.out.println("\nCurrent year = " + updated_year);
//        System.out.println("\nPast Moon Phase Calculations:");
//        
//        for(int i = 0; i < moon_phases.length; i++)
//        {
//            JD = 2415020.75933 + (29.53058868 * moon_phases[i]) + (0.0001178 * Math.pow(t_lunar, 2))
//            + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
//            - ((0.009173 * RADS) * Math.pow(t_lunar, 2)))));
//          
//            g_JD = Math.floor(JD); //Calculated Julian Date
//            System.out.println("New moon (Julian Day) = " + g_JD);
//          
//            // Converts JD to Calendar Date:
//            moon_convertJulianDateToCalanderDate(g_JD);
//            System.out.println("JD = " + JD);
//          
//            System.out.println("\nCalander Date:");
//            System.out.println("Day = " + g_calander_day);
//            System.out.println("Month = " + g_calander_month);
//            System.out.println("Year = " + g_calander_year + "\n");
//        
//            // Gets the current DAY generated in Star Map:
//            day_str_dateTime = dateTime.getDayOfMonth();
//            System.out.println("day_str_dateTime = " + day_str_dateTime);
//        
//            // Gets the current MONTH generated in Star Map:
//            mon_str_dateTime = dateTime.getMonthValue();
//            System.out.println("mon_str_dateTime = " + mon_str_dateTime);
//        
//            // Gets the current YEAR generated in Star Map:
//            year_str_dateTime = dateTime.getYear();
//            System.out.println("year_str_dateTime = " + year_str_dateTime);
//                
//            // Converts the returned generated DAY, MONTH, YEAR in Star Map to a string:
//            dateDay_str = String.valueOf(day_str_dateTime);
//            dateMon_str = String.valueOf(mon_str_dateTime);
//            dateYear_str = String.valueOf(year_str_dateTime);
//        
//            // Converts the CALANDER DATE to a string:
//            day_str = String.valueOf(df_two.format(g_calander_day));
//            month_str = String.valueOf(df_two.format(g_calander_month));
//            year_str = String.valueOf(df_two.format(g_calander_year));
//        
//            System.out.println("\nconvert Day to string = " + day_str);
//            System.out.println("convert Month to string = " + month_str);
//            System.out.println("convert Year to string = " + year_str + "\n");
//        
//            // Compares the Calander Date the LocalDateTime
//            if (day_str.equals(dateDay_str) && month_str.equals(dateMon_str) &&
//                year_str.equals(dateYear_str))
//            {
//                phase = phase_msgs[i];
//                //phase = "The moon phase is a NEW MOON";
//            
//            }
//            else
//                phase = "Not available!";
//            
//         } // End of FOR-loop
        
        //=====================================================================
        // 1) Julian Day of a PAST NEW MOON:
        //=====================================================================
        JD = 2415020.75933 + (29.53058868 * k_lunar) + (0.0001178 * Math.pow(t_lunar, 2))
            + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
            - ((0.009173 * RADS) * Math.pow(t_lunar, 2))))); 
            
        g_JD = Math.floor(JD); //Calculated Julian Date
        System.out.println("New moon (Julian Day) = " + g_JD); //new_moon
        
        // Converts JD to Calendar Date:
        moon_convertJulianDateToCalanderDate(g_JD);
        System.out.println("JD = " + JD);
        //double past_new_moon = g_JD;
        
        System.out.println("\nCalander Date:");
        System.out.println("Day = " + g_calander_day);
        System.out.println("Month = " + g_calander_month);
        System.out.println("Year = " + g_calander_year + "\n");
        

        // Gets the current DAY generated in Star Map:
        day_str_dateTime = dateTime.getDayOfMonth();
        System.out.println("day_str_dateTime = " + day_str_dateTime);
        
        // Gets the current MONTH generated in Star Map:
        mon_str_dateTime = dateTime.getMonthValue();
        System.out.println("mon_str_dateTime = " + mon_str_dateTime);
        
        // Gets the current YEAR generated in Star Map:
        year_str_dateTime = dateTime.getYear();
        System.out.println("year_str_dateTime = " + year_str_dateTime);
                
        // Converts the returned generated DAY, MONTH, YEAR in Star Map to a string:
        String dateDay_str = String.valueOf(day_str_dateTime);
        String dateMon_str = String.valueOf(mon_str_dateTime);
        String dateYear_str = String.valueOf(year_str_dateTime);
        
        // Converts the CALANDER DATE to a string:
        String day_str = String.valueOf(df_two.format(g_calander_day));
        String month_str = String.valueOf(df_two.format(g_calander_month));
        String year_str = String.valueOf(df_two.format(g_calander_year));
        
        System.out.println("\nconvert Day to string = " + day_str);
        System.out.println("convert Month to string = " + month_str);
        System.out.println("convert Year to string = " + year_str + "\n");
        
        // Compares the Calander Date the LocalDateTime
        if (day_str.equals(dateDay_str) && month_str.equals(dateMon_str) &&
                year_str.equals(dateYear_str))
        {
            phase = "The moon phase is a NEW MOON";
            
        }
        else
            phase = "Not available!";
   
        //=====================================================================
        // 2) Julian Day of a PAST FIRST QUARTER:
        //=====================================================================
        k_first_quarter = k_lunar + 0.25;
        JD = 2415020.75933 + (29.53058868 * k_first_quarter) + (0.0001178 * Math.pow(t_lunar, 2))
                + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
                - ((0.009173 * RADS) * Math.pow(t_lunar, 2)))));
            
        g_JD = Math.floor(JD); //Calculated Julian Date
        System.out.println("first quarter = " + g_JD); //first_quarter
        
        
        // Converts JD to Calendar Date:
        moon_convertJulianDateToCalanderDate(g_JD);
        
        System.out.println("\nCalander Date:");
        System.out.println("Day = " + g_calander_day);
        System.out.println("Month = " + g_calander_month);
        System.out.println("Year = " + g_calander_year + "\n");

        // Gets the current DAY generated in Star Map:
        day_str_dateTime = dateTime.getDayOfMonth();
        System.out.println("day_str_dateTime = " + day_str_dateTime);
        
        // Gets the current MONTH generated in Star Map:
        mon_str_dateTime = dateTime.getMonthValue();
        System.out.println("mon_str_dateTime = " + mon_str_dateTime);
        
        // Gets the current YEAR generated in Star Map:
        year_str_dateTime = dateTime.getYear();
        System.out.println("year_str_dateTime = " + year_str_dateTime);
                
        // Converts the returned generated DAY, MONTH, YEAR in Star Map to a string:
        dateDay_str = String.valueOf(day_str_dateTime);
        dateMon_str = String.valueOf(mon_str_dateTime);
        dateYear_str = String.valueOf(year_str_dateTime);
        
        // Converts the CALANDER DATE to a string:
        day_str = String.valueOf(df_two.format(g_calander_day));
        month_str = String.valueOf(df_two.format(g_calander_month));
        year_str = String.valueOf(df_two.format(g_calander_year));
        
        System.out.println("\nconvert Day to string = " + day_str);
        System.out.println("convert Month to string = " + month_str);
        System.out.println("convert Year to string = " + year_str + "\n");
        
        // Compares the Calander Date the LocalDateTime
        if (day_str.equals(dateDay_str) && month_str.equals(dateMon_str) &&
                year_str.equals(dateYear_str))
        {
            phase = "The moon phase is FIRST QUARTER";
            
        }
        else
            phase = "Not available!";
        
        //=====================================================================
        // 3) Julian Day of a PAST FULL MOON:
        //=====================================================================
        k_full_moon = k_lunar + .50;
        JD = 2415020.75933 + (29.53058868 * k_full_moon) + (0.0001178 * Math.pow(t_lunar, 2))
            + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
            - ((0.009173 * RADS) * Math.pow(t_lunar, 2)))));
        
        g_JD = Math.floor(JD); // Calculated Julian Date
        System.out.println("full_moon = " + g_JD); //full_moon
        
        
        // Converts JD to Calendar Date:
        moon_convertJulianDateToCalanderDate(g_JD);
        
        System.out.println("\nCalander Date:");
        System.out.println("Day = " + g_calander_day);
        System.out.println("Month = " + g_calander_month);
        System.out.println("Year = " + g_calander_year + "\n");

        // Gets the current DAY generated in Star Map:
        day_str_dateTime = dateTime.getDayOfMonth();
        System.out.println("day_str_dateTime = " + day_str_dateTime);
        
        // Gets the current MONTH generated in Star Map:
        mon_str_dateTime = dateTime.getMonthValue();
        System.out.println("mon_str_dateTime = " + mon_str_dateTime);
        
        // Gets the current YEAR generated in Star Map:
        year_str_dateTime = dateTime.getYear();
        System.out.println("year_str_dateTime = " + year_str_dateTime);
                
        // Converts the returned generated DAY, MONTH, YEAR in Star Map to a string:
        dateDay_str = String.valueOf(day_str_dateTime);
        dateMon_str = String.valueOf(mon_str_dateTime);
        dateYear_str = String.valueOf(year_str_dateTime);
        
        // Converts the CALANDER DATE to a string:
        day_str = String.valueOf(df_two.format(g_calander_day));
        month_str = String.valueOf(df_two.format(g_calander_month));
        year_str = String.valueOf(df_two.format(g_calander_year));
        
        System.out.println("\nconvert Day to string = " + day_str);
        System.out.println("convert Month to string = " + month_str);
        System.out.println("convert Year to string = " + year_str + "\n");
        
        // Compares the Calander Date the LocalDateTime
        if (day_str.equals(dateDay_str) && month_str.equals(dateMon_str) &&
                year_str.equals(dateYear_str))
        {
            phase = "The moon phase is FULL MOON";
            
        }
        else
            phase = "Not available!";
        
        //=====================================================================
        // 4) Julian Day of a PAST LAST QUARTER MOON:
        //=====================================================================
        k_last_quarter = k_lunar + .75;
        JD = 2415020.75933 + (29.53058868 * k_last_quarter) + (0.0001178 * Math.pow(t_lunar, 2))
            + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
            - ((0.009173 * RADS) * Math.pow(t_lunar, 2)))));
            
        g_JD = Math.floor(JD); // Calculated Julian Date
        System.out.println("last_quarter = " + g_JD); //last_quarter
        
        
        // Converts JD to Calendar Date:
        moon_convertJulianDateToCalanderDate(g_JD);
        
        System.out.println("\nCalander Date:");
        System.out.println("Day = " + g_calander_day);
        System.out.println("Month = " + g_calander_month);
        System.out.println("Year = " + g_calander_year + "\n");

        // Gets the current DAY generated in Star Map:
        day_str_dateTime = dateTime.getDayOfMonth();
        System.out.println("day_str_dateTime = " + day_str_dateTime);
        
        // Gets the current MONTH generated in Star Map:
        mon_str_dateTime = dateTime.getMonthValue();
        System.out.println("mon_str_dateTime = " + mon_str_dateTime);
        
        // Gets the current YEAR generated in Star Map:
        year_str_dateTime = dateTime.getYear();
        System.out.println("year_str_dateTime = " + year_str_dateTime);
                
        // Converts the returned generated DAY, MONTH, YEAR in Star Map to a string:
        dateDay_str = String.valueOf(day_str_dateTime);
        dateMon_str = String.valueOf(mon_str_dateTime);
        dateYear_str = String.valueOf(year_str_dateTime);
        
        // Converts the CALANDER DATE to a string:
        day_str = String.valueOf(df_two.format(g_calander_day));
        month_str = String.valueOf(df_two.format(g_calander_month));
        year_str = String.valueOf(df_two.format(g_calander_year));
        
        System.out.println("\nconvert Day to string = " + day_str);
        System.out.println("convert Month to string = " + month_str);
        System.out.println("convert Year to string = " + year_str + "\n");
        
        // Compares the Calander Date the LocalDateTime
        if (day_str.equals(dateDay_str) && month_str.equals(dateMon_str) &&
                year_str.equals(dateYear_str))
        {
            phase = "The moon phase is LAST QUARTER";
            
        }
        else
            phase = "Not available!";
        
        
        System.out.println("\nFuture Moon Phase Calculations:");
        
        //=====================================================================
        // 5) Julian Day of a FUTURE NEW MOON:
        //=====================================================================
        JD = 2415020.75933 + (29.53058868 * k_lunar) + (0.0001178 * Math.pow(t_lunar, 2))
            + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
            - ((0.009173 * RADS) * Math.pow(t_lunar, 2)))));
            
        g_JD = Math.ceil(JD); // Calculated Julian Date
        System.out.println("new_moon = " + g_JD); //new_moon
        
        // Converts JD to Calendar Date:
        moon_convertJulianDateToCalanderDate(g_JD);
        
        System.out.println("\nCalander Date:");
        System.out.println("Day = " + g_calander_day);
        System.out.println("Month = " + g_calander_month);
        System.out.println("Year = " + g_calander_year + "\n");

        // Gets the current DAY generated in Star Map:
        day_str_dateTime = dateTime.getDayOfMonth();
        System.out.println("day_str_dateTime = " + day_str_dateTime);
        
        // Gets the current MONTH generated in Star Map:
        mon_str_dateTime = dateTime.getMonthValue();
        System.out.println("mon_str_dateTime = " + mon_str_dateTime);
        
        // Gets the current YEAR generated in Star Map:
        year_str_dateTime = dateTime.getYear();
        System.out.println("year_str_dateTime = " + year_str_dateTime);
                
        // Converts the returned generated DAY, MONTH, YEAR in Star Map to a string:
        dateDay_str = String.valueOf(day_str_dateTime);
        dateMon_str = String.valueOf(mon_str_dateTime);
        dateYear_str = String.valueOf(year_str_dateTime);
        
        // Converts the CALANDER DATE to a string:
        day_str = String.valueOf(df_two.format(g_calander_day));
        month_str = String.valueOf(df_two.format(g_calander_month));
        year_str = String.valueOf(df_two.format(g_calander_year));
        
        System.out.println("\nconvert Day to string = " + day_str);
        System.out.println("convert Month to string = " + month_str);
        System.out.println("convert Year to string = " + year_str + "\n");
        
        // Compares the Calander Date the LocalDateTime
        if (day_str.equals(dateDay_str) && month_str.equals(dateMon_str) &&
                year_str.equals(dateYear_str))
        {
            phase = "The moon phase is NEW MOON";
            
        }
        else
            phase = "Not available!";
        
        //=====================================================================
        // 6) Julian Day of a FUTURE FIRST QUARTER MOON:
        //=====================================================================
        k_first_quarter = k_lunar + 0.25;
        JD = 2415020.75933 + (29.53058868 * k_first_quarter) + (0.0001178 * Math.pow(t_lunar, 2))
            + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
            - ((0.009173 * RADS) * Math.pow(t_lunar, 2)))));
            
        g_JD = Math.ceil(JD); // Calculated Julian Date
        System.out.println("first quarter = " + g_JD); //first_quarter
        
        // Converts JD to Calendar Date:
        moon_convertJulianDateToCalanderDate(g_JD);
        
        System.out.println("\nCalander Date:");
        System.out.println("Day = " + g_calander_day);
        System.out.println("Month = " + g_calander_month);
        System.out.println("Year = " + g_calander_year + "\n");

        // Gets the current DAY generated in Star Map:
        day_str_dateTime = dateTime.getDayOfMonth();
        System.out.println("day_str_dateTime = " + day_str_dateTime);
        
        // Gets the current MONTH generated in Star Map:
        mon_str_dateTime = dateTime.getMonthValue();
        System.out.println("mon_str_dateTime = " + mon_str_dateTime);
        
        // Gets the current YEAR generated in Star Map:
        year_str_dateTime = dateTime.getYear();
        System.out.println("year_str_dateTime = " + year_str_dateTime);
                
        // Converts the returned generated DAY, MONTH, YEAR in Star Map to a string:
        dateDay_str = String.valueOf(day_str_dateTime);
        dateMon_str = String.valueOf(mon_str_dateTime);
        dateYear_str = String.valueOf(year_str_dateTime);
        
        // Converts the CALANDER DATE to a string:
        day_str = String.valueOf(df_two.format(g_calander_day));
        month_str = String.valueOf(df_two.format(g_calander_month));
        year_str = String.valueOf(df_two.format(g_calander_year));
        
        System.out.println("\nconvert Day to string = " + day_str);
        System.out.println("convert Month to string = " + month_str);
        System.out.println("convert Year to string = " + year_str + "\n");
        
        // Compares the Calander Date the LocalDateTime
        if (day_str.equals(dateDay_str) && month_str.equals(dateMon_str) &&
                year_str.equals(dateYear_str))
        {
            phase = "The moon phase is FIRST QUARTER";
            
        }
        else
            phase = "Not available!";
        //=====================================================================
        // 7) Julian Day of a FUTURE FULL MOON:
        //=====================================================================
        k_full_moon = k_lunar + .50;
        JD = 2415020.75933 + (29.53058868 * k_full_moon) + (0.0001178 * Math.pow(t_lunar, 2))
            + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
            - ((0.009173 * RADS) * Math.pow(t_lunar, 2)))));
            
        g_JD = Math.ceil(JD); // Calculated Julian Date
        System.out.println("full_moon = " + g_JD); //full_moon
        
        // Converts JD to Calendar Date:
        moon_convertJulianDateToCalanderDate(g_JD);
        
        System.out.println("\nCalander Date:");
        System.out.println("Day = " + g_calander_day);
        System.out.println("Month = " + g_calander_month);
        System.out.println("Year = " + g_calander_year + "\n");

        // Gets the current DAY generated in Star Map:
        day_str_dateTime = dateTime.getDayOfMonth();
        System.out.println("day_str_dateTime = " + day_str_dateTime);
        
        // Gets the current MONTH generated in Star Map:
        mon_str_dateTime = dateTime.getMonthValue();
        System.out.println("mon_str_dateTime = " + mon_str_dateTime);
        
        // Gets the current YEAR generated in Star Map:
        year_str_dateTime = dateTime.getYear();
        System.out.println("year_str_dateTime = " + year_str_dateTime);
                
        // Converts the returned generated DAY, MONTH, YEAR in Star Map to a string:
        dateDay_str = String.valueOf(day_str_dateTime);
        dateMon_str = String.valueOf(mon_str_dateTime);
        dateYear_str = String.valueOf(year_str_dateTime);
        
        // Converts the CALANDER DATE to a string:
        day_str = String.valueOf(df_two.format(g_calander_day));
        month_str = String.valueOf(df_two.format(g_calander_month));
        year_str = String.valueOf(df_two.format(g_calander_year));
        
        System.out.println("\nconvert Day to string = " + day_str);
        System.out.println("convert Month to string = " + month_str);
        System.out.println("convert Year to string = " + year_str + "\n");
        
        // Compares the Calander Date the LocalDateTime
        if (day_str.equals(dateDay_str) && month_str.equals(dateMon_str) &&
                year_str.equals(dateYear_str))
        {
            phase = "The moon phase is FULL MOON";
            
        }
        else
            phase = "Not available!";
        
        //=====================================================================
        // 8) Julian Day of a FUTURE LAST QUARTER MOON:
        //=====================================================================
        k_last_quarter = k_lunar + .75;
        JD = 2415020.75933 + (29.53058868 * k_last_quarter) + (0.0001178 * Math.pow(t_lunar, 2))
            + (0.00033 * Math.sin(((166.56 * RADS) + ((132.87 * RADS) * t_lunar) 
            - ((0.009173 * RADS) * Math.pow(t_lunar, 2)))));
            
        g_JD = Math.ceil(JD); // Calculated Julian Date
        System.out.println("last_quarter = " + g_JD); //last_quarter
        
        // Converts JD to Calendar Date:
        moon_convertJulianDateToCalanderDate(g_JD);
        
        System.out.println("\nCalander Date:");
        System.out.println("Day = " + g_calander_day);
        System.out.println("Month = " + g_calander_month);
        System.out.println("Year = " + g_calander_year + "\n");

        // Gets the current DAY generated in Star Map:
        day_str_dateTime = dateTime.getDayOfMonth();
        System.out.println("day_str_dateTime = " + day_str_dateTime);
        
        // Gets the current MONTH generated in Star Map:
        mon_str_dateTime = dateTime.getMonthValue();
        System.out.println("mon_str_dateTime = " + mon_str_dateTime);
        
        // Gets the current YEAR generated in Star Map:
        year_str_dateTime = dateTime.getYear();
        System.out.println("year_str_dateTime = " + year_str_dateTime);
                
        // Converts the returned generated DAY, MONTH, YEAR in Star Map to a string:
        dateDay_str = String.valueOf(day_str_dateTime);
        dateMon_str = String.valueOf(mon_str_dateTime);
        dateYear_str = String.valueOf(year_str_dateTime);
        
        // Converts the CALANDER DATE to a string:
        day_str = String.valueOf(df_two.format(g_calander_day));
        month_str = String.valueOf(df_two.format(g_calander_month));
        year_str = String.valueOf(df_two.format(g_calander_year));
        
        System.out.println("\nconvert Day to string = " + day_str);
        System.out.println("convert Month to string = " + month_str);
        System.out.println("convert Year to string = " + year_str + "\n");
        
        // Compares the Calander Date the LocalDateTime
        if (day_str.equals(dateDay_str) && month_str.equals(dateMon_str) &&
                year_str.equals(dateYear_str))
        {
            phase = " The moon phase is LAST QUARTER";
            
        }
        else
            phase = " Moon phase not available for " + month_str
                                 + "/" + day_str + "/" + year_str;
        
        //Moon_Phases ph, new_val;

        //for(Moon_Phases phases: Moon_Phases.values())
           
        
        //Lunar Location calculation:
        DecimalFormat df = new DecimalFormat("#.######");
        
        julianDate = 2444214.5;
        double t = (julianDate - 2415020.0) / 36525; //43549.4361111
        
        System.out.print("\n******************************");
        System.out.println("\nLunar Location calculations:");
        System.out.println("******************************");
        System.out.println("t = " + Math.round(t));  
        System.out.println("julian day " + julianDate);
        
        //Angles:
        g_L_angle = 270.434164 + (481267.8831 * t);
        g_M_angle = 358.475833 + (35999.0498 * t);
        g_Mp_angle = 296.104608 + (477198.8491 * t);
        g_D_angle = 350.737486 + (445267.1142 * t);
        g_F_angle = 11.250889 + (483202.0251 * t);
        g_ecc = 1 - (0.002495 * t) - (0.00000752 * t * t);
        
        System.out.println("moonMeanLongitude (L')  = " + g_L_angle);
        System.out.println("sunMeanAnomaly (M)     = " + g_M_angle);
        System.out.println("moonMeanAnomaly (M')    = " + g_Mp_angle);
        System.out.println("moonMeanElongation (D)  = " + g_D_angle);
        System.out.println("moonMeanDistance (F)   = " + g_F_angle);
        System.out.println("eccentricity       = " + df.format(g_ecc));
        
        // Angles converted to Radians
        
        double L_RADS = Math.toRadians(g_L_angle); // L'
        double M_RADS = Math.toRadians(g_M_angle); // M
        double Mp_RADS = Math.toRadians(g_Mp_angle); //M'
        double D_RADS = Math.toRadians(g_D_angle); // D - angular distance east of the Sun at any time.
        double F_RADS = Math.toRadians(g_F_angle); // F
        
        System.out.println("\nLunar Angles after RADS:");
        System.out.println("L' = " + L_RADS);
        System.out.println("M = " + M_RADS);
        System.out.println("M' = " + Mp_RADS);
        System.out.println("D = " + D_RADS);
        System.out.println("F = " + F_RADS + "\n");
        
        // Calculates the Moon's geocentric latitude and longitude
        double geocentricLongitudeRadians = g_L_angle 
                + (6.288750 * Math.sin(g_Mp_angle))
                + (1.274018 * Math.sin((2 * g_D_angle) - g_Mp_angle))
                + (0.658309 * Math.sin(2 * g_D_angle))
                + (0.213616 * Math.sin(2 * g_Mp_angle))
                - (0.185596 * Math.sin(g_M_angle) * g_ecc)
                - (0.114336 * Math.sin(2 * g_F_angle))
                + (0.058793 * Math.sin((2 * g_D_angle) - (2 * g_Mp_angle)))
                + (0.057212 * Math.sin((2 * g_F_angle) - g_M_angle - g_Mp_angle) * g_ecc)
                + (0.053320 * Math.sin((2 * g_F_angle) + g_Mp_angle))
                + (0.045874 * Math.sin((2 * g_F_angle) - g_M_angle) * g_ecc);
        
        double geoentricLatitudeRadians = (5.128189 * Math.sin(g_F_angle))
                + (0.280606 * Math.sin(g_Mp_angle + g_F_angle))
                + (0.277693 * Math.sin(g_Mp_angle - g_F_angle))
                + (0.173238 * Math.sin((2 * g_D_angle) - g_F_angle))
                + (0.055413 * Math.sin((2 * g_D_angle) + g_F_angle - g_Mp_angle))
                + (0.046272 * Math.sin((2 * g_D_angle) - g_F_angle - g_Mp_angle))
                + (0.032573 * Math.sin((2 * g_D_angle) + g_F_angle))
                + (0.017198 * Math.sin((2 * g_Mp_angle) + g_F_angle))
                + (0.009267 * Math.sin((2 * g_D_angle) + g_Mp_angle - g_F_angle))
                + (0.008823 * Math.sin((2 * g_Mp_angle) - g_F_angle));
        
        double geocentricLongitude = Calculation.getDegreesFromRadians(geocentricLongitudeRadians);
        double geoentricLatitude = Calculation.getDegreesFromRadians(geoentricLatitudeRadians);
        
        System.out.println("\nGEO LAT and LON:");
        System.out.println("geocentricLongitude = " + geocentricLongitude);
        System.out.println("geoentricLatitude = " + geoentricLatitude + "\n");
        
        //Calculte the Right Ascension for Moon
        if (geocentricLongitude < 0)
        {
            rightAscension = (geocentricLongitude + 360) / 15;
        }
        else
        {
            rightAscension = geocentricLongitude / 15;
        }        
        
        declination = geoentricLatitude;    
        
        System.out.println("rightAscension = " + rightAscension);
        System.out.println("declination = " + declination);
    }
    
    @Override
    /**************************************************************************
     *
     * METHOD: calculateHorizonCoordinates()
     * 
     * DESCRIPTION: gets the coordinates and time information that will be used
     *              to plot Moon.
     * 
     * @param latitude
     * @param longitude
     * @param greenwichSiderealTime
     * 
    ***************************************************************************/
    public void calculateHorizonCoordinates(double latitude, double longitude, LocalTime greenwichSiderealTime) throws Exception
    {

//        if (rightAscension < 0 || rightAscension > 24)
//        {
//            throw new Exception("Invalid value of " + rightAscension + " for rightAscension passed into Star.calculateHorizonCoordinates");
//        }
        
        System.out.println("HorCords: rightAscension = " + rightAscension); //38297.97343336875
        System.out.println("HorzCords: Declination = " + declination);
//        if (declination < -90 || declination > 90)
//        {
//            throw new Exception("Invalid value of " + declination + " for declination passed into Star.calculateHorizonCoordinates");
//        }

//        if (rightAscension < 0 || rightAscension > 24)
//        {
//            throw new Exception("Invalid value of " + rightAscension + " for rightAscension passed into Moon.calculateHorizonCoordinates");
//        }
//        
//        if (declination < -90 || declination > 90)
//        {
//            throw new Exception("Invalid value of " + declination + " for declination passed into Moon.calculateHorizonCoordinates");
//        }

        
        double decimalHours = greenwichSiderealTime.getHour() + (greenwichSiderealTime.getMinute() / 60.0) + (greenwichSiderealTime.getSecond() / (60.0 * 60));
        
        // Longitude passed is negative if west of Greenwich and will be subtracted in this case
        double hourAngleDegrees = (decimalHours - rightAscension) * 15 + longitude;        
        
//        System.out.println("hourAngleDegrees = " + hourAngleDegrees);
        
        double hourAngleRadians = Calculation.getRadiansFromDegrees(hourAngleDegrees);
        double declinationRadians = Calculation.getRadiansFromDegrees(declination);
        double latitudeRadians = Calculation.getRadiansFromDegrees(latitude);
        
        double elevationRadians = Math.asin(
                (Math.cos(hourAngleRadians) * Math.cos(declinationRadians) * Math.cos(latitudeRadians))
                + (Math.sin(declinationRadians) * Math.sin(latitudeRadians)));
        double azimuthRadians = Math.atan2(
                -1 * Math.sin(hourAngleRadians),
                (Math.tan(declinationRadians) * Math.cos(latitudeRadians))
                        - (Math.sin(latitudeRadians) * Math.cos(hourAngleRadians)));  
        
        double azimuthDegrees = Calculation.getDegreesFromRadians(azimuthRadians);
        double elevationDegrees = Calculation.getDegreesFromRadians(elevationRadians);
        
//        System.out.println("azimuthDegrees = " + azimuthDegrees);
//        System.out.println("elevationDegrees = " + elevationDegrees);  
        
        if (azimuthDegrees < 0)
        {
            azimuthDegrees += 360;
        }
        
        if (azimuthDegrees > 360)
        {
            azimuthDegrees -= 360;
        }
        
//        System.out.println("hourAngleDegrees = " + hourAngleDegrees);

        azimuth = azimuthDegrees;
        elevation = elevationDegrees;
    }
}
