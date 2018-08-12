package com.ubs.opsit.interviews;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class BerlinClockTest {

   
    @Before
    public void setUpStreams() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLowerInvalidHours() {
    	new BerlinClock().convertTime("-01:00:00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLowerInvalidMinutes() {
    	new BerlinClock().convertTime("00:-01:00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLowerInvalidSeconds() {
    	new BerlinClock().convertTime("00:00:-01");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidString() {
    	new BerlinClock().convertTime("00:00");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullString() {
    	new BerlinClock().convertTime(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyString() {
    	new BerlinClock().convertTime("");
    }
        
    @Test
    public void testMidnight1() {
    	TimeConverter clock = new BerlinClock();    	

        String expected = "Y\n" +
                "OOOO\n" +
                "OOOO\n" +
                "OOOOOOOOOOO\n" +
                "OOOO";

        assertEquals(expected, clock.convertTime("00:00:00"));
    }
    
    @Test
    public void testMiddleOfTheAfternoon() {
    	TimeConverter clock = new BerlinClock();    	

        String expected = "O\n" +
                "RROO\n" +
                "RRRO\n" +
                "YYROOOOOOOO\n" +
                "YYOO";

        assertEquals(expected, clock.convertTime("13:17:01"));
    }
    
    @Test
    public void testJustBeforeMidnight() {
    	TimeConverter clock = new BerlinClock();    	

        String expected = "O\n" +
                "RRRR\n" +
                "RRRO\n" +
                "YYRYYRYYRYY\n" +
                "YYYY";

        assertEquals(expected, clock.convertTime("23:59:59"));
    }
    
    @Test
    public void testMidnight2() {
    	TimeConverter clock = new BerlinClock();    	

        String expected = "Y\n" +
                "RRRR\n" +
                "RRRR\n" +
                "OOOOOOOOOOO\n" +
                "OOOO";

        assertEquals(expected, clock.convertTime("24:00:00"));
    }
}
