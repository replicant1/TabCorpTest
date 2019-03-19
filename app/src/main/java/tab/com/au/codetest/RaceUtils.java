package tab.com.au.codetest;

import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;

import java.util.StringTokenizer;

public class RaceUtils {

	public static final int SECONDS_PER_HOUR = 60 * 60;
	public static final String SPACE_CHAR = " ";

	/**
	 * @param str String to convert to camel case
	 * @return The Camel-Case equivalent of str
	 */
	public static String capitalize(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, SPACE_CHAR);
		StringBuffer result = new StringBuffer();

		while (tokenizer.hasMoreTokens()) {
			result.append(firstLetterToUpperCase(tokenizer.nextToken()));
			result.append(" ");
		}

		return result.toString().trim();
	}

	/**
	 * @param word A sequence of alphanumeric characters or an empty string
	 * @return The given word with the first character upper case and all subsequent characters lower case
	 */
	public static String firstLetterToUpperCase(String word) {
		String result = null;

		if (word.length() == 0) {
			result = "";
		} else {
			Character firstLetterUpperCase = Character.toUpperCase(word.charAt(0));
			if (word.length() == 1) {
				result = firstLetterUpperCase.toString();
			} else {
				String remainderLowerCase = word.subSequence(1, word.length()).toString().toLowerCase();
				result = firstLetterUpperCase + remainderLowerCase;
			}
		}
		return result;
	}

	/**
	 * @param earlierLocal Previous time
	 * @param laterLocal   Later time
	 * @return True if the earlierLocal time is within an hour before laterLocal. False if more than an
	 * hour before or if earlierLocal is after laterLocal.
	 */
	public static boolean isWithinAnHourBefore(LocalDateTime earlierLocal, LocalDateTime laterLocal) {
		Seconds between = Seconds.secondsBetween(earlierLocal, laterLocal);
		return (between.getSeconds() <= SECONDS_PER_HOUR) && (between.getSeconds() >= 0);
	}
}
