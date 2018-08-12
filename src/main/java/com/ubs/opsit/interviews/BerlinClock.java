package com.ubs.opsit.interviews;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Pattern;

public class BerlinClock implements TimeConverter {

	private static final String NEW_LINE = System.getProperty("line.separator");
	private static final String NO_TIME_ERROR = "No time provided";
	private static final String INVALID_TIME_ERROR = "Invalid time provided.";
	private static final String NUMERIC_TIME_ERROR = "Time values must be numeric.";

	@Override
	public String convertTime(String aTime) {
		// TODO Auto-generated method stub
		if(validateInput(aTime)) {
			String[] times = aTime.split(":", 3);
			int hours, minutes, seconds = 0;
			hours = Integer.parseInt(times[0]);
			minutes = Integer.parseInt(times[1]);
			seconds = Integer.parseInt(times[2]);
			return processTime(hours, minutes, seconds);
		}
		return null;

	}

	public boolean validateInput(String aTime) {
		if (aTime == null || aTime.trim().isEmpty())
			throw new IllegalArgumentException(NO_TIME_ERROR);
		
		if (Pattern.matches("([2][0-4]|[0-1][0-9]|[1-9]):[0-5][0-9]:([0-5][0-9]|[6][0])$", aTime)) {

			return true;

		} else {
			throw new IllegalArgumentException(INVALID_TIME_ERROR);
		}
	}

	/**
	 * Convert individual hours, minutes and seconds into a BerlinTime object.
	 *
	 * @param hours
	 *            - an int representing Hours
	 * @param minutes
	 *            - an int representing Minutes
	 * @param seconds
	 *            - an int representing Seconds
	 *
	 * @return BerlinTime object created using the parameters.
	 */
	private String processTime(int hours, int minutes, int seconds) {

		String line1 = (seconds % 2 == 0) ? "Y" : "O";
		String line2 = rowString(hours / 5, 4, "R");
		String line3 = rowString(hours % 5, 4, "R");
		String line4 = rowString(minutes / 5, 11, "Y").replaceAll("YYY", "YYR");
		String line5 = rowString(minutes % 5, 4, "Y");

		return String.join(NEW_LINE, Arrays.asList(line1, line2, line3, line4, line5));

	}

	/**
	 * Creates a string for each row of the berlin clock.
	 * 
	 * @param litLights
	 * @param lightsInRow
	 * @param lampType
	 * @returnn A string representing a single row of the clock.
	 */
	private String rowString(int litLights, int lightsInRow, String lampType) {

		int unlitLights = lightsInRow - litLights;
		String lit = String.join("", Collections.nCopies(litLights, lampType));
		String unlit = String.join("", Collections.nCopies(unlitLights, "O"));

		return lit + unlit;
	}

}
