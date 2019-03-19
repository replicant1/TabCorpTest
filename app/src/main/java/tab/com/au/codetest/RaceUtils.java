package tab.com.au.codetest;

import org.joda.time.LocalDateTime;

import java.util.StringTokenizer;

public class RaceUtils {

	public static String capitalize(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, " ");
		StringBuffer result = new StringBuffer();

		while (tokenizer.hasMoreTokens()) {
			result.append(firstLetterToUpperCase(tokenizer.nextToken()));
			result.append(" ");
		}

		return result.toString().trim();
	}

	public static String firstLetterToUpperCase(String word) {
		Character firstLetterUpperCase = Character.toUpperCase(word.charAt(0));
		String remainderLowerCase = word.subSequence(1, word.length()).toString().toLowerCase();
		return firstLetterUpperCase + remainderLowerCase;
	}

	public static boolean isWithinAnHour(LocalDateTime nowLocal, LocalDateTime startLocal) {
		LocalDateTime oneHourBeforeStartLocal = startLocal.minusHours(1);

//		System.out.println("** Now=" + DateTime.now());
//		System.out.println("** NowLocal=" + DateTime.now().toLocalDateTime());
//		System.out.println("** startDateTime=" + startDateTime);
//		System.out.println("** startLocalDateTime=" + startLocalDateTime);
//		System.out.println("** oneHourBeforeStart=" + oneHourBeforeStart);
//		System.out.println("** oneHourBeforeStartLocal=" + oneHourBeforeStartLocal);
//		System.out.println("** oneHourBeforeStart.isBeforeNow()=" + oneHourBeforeStart.isBeforeNow());
//		System.out.println("** startDateTime.isAfterNow()=" + startDateTime.isAfterNow());
//		System.out.println("----------------------");

		return (oneHourBeforeStartLocal.isBefore(nowLocal) && (startLocal.isAfter(nowLocal)));
	}
}
