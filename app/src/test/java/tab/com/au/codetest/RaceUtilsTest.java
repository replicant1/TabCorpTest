package tab.com.au.codetest;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import static org.junit.Assert.*;

public class RaceUtilsTest {

    @Test
    public void firstLetterToUpperCaseOnEmptyStringGivesEmptyString() {
        String str = RaceUtils.firstLetterToUpperCase("");
        assertNotNull(str);
        assertEquals(0, str.length());
    }

    @Test
    public void firstLetterToUpperCaseOnSingleCharString() {
        String str = RaceUtils.firstLetterToUpperCase("a");
        assertNotNull(str);
        assertEquals(1, str.length());
        assertEquals("A", str);
    }

    @Test
    public void firstLetterToUpperCaseOnWord() {
        String str = RaceUtils.firstLetterToUpperCase("word");
        assertNotNull(str);
        assertEquals("Word", str);
    }

    @Test
    public void capitalizeEmptyString() {
        String str = RaceUtils.capitalize("");
        assertNotNull(str);
        assertEquals(0, str.length());
    }

    @Test
    public void capitalizeOneWord() {
        String str = RaceUtils.capitalize("word");
        assertNotNull(str);
        assertEquals("Word", str);
    }

    @Test
    public void capitalizeTwoWords() {
        String str = RaceUtils.capitalize("two words");
        assertNotNull(str);
        assertEquals("Two Words", str);
    }

    @Test
    public void capitalizeTwoWordsPlusSymbol() {
        String str = RaceUtils.capitalize("$7 is seven dollars");
        assertNotNull(str);
        assertEquals("$7 Is Seven Dollars", str);
    }

    @Test
    public void capitalizeTwoWordsMultipleSpaces() {
        String str = RaceUtils.capitalize("two  words");
        assertNotNull(str);
        assertEquals("Two Words", str);
    }

    @Test
    public void capitalizeTrailingSpace() {
        String str = RaceUtils.capitalize("trailing ");
        assertEquals("Trailing", str);
    }

    @Test
    public void capitalizeLeadingSpace() {
        String str = RaceUtils.capitalize(" leading");
        assertEquals("Leading", str);
    }

    @Test
    public void capitalizeLeadingAndTrailingSpaces() {
        String str = RaceUtils.capitalize(" both ");
        assertEquals("Both", str);
    }

    @Test
    public void nineAMIsWithinAnHourOfTenAM() {
        LocalDateTime nine_am = DateTime.now().toLocalDateTime().withTime(9, 0, 0, 0);
        LocalDateTime ten_am = DateTime.now().toLocalDateTime().withTime(10, 0, 0, 0);
        assertTrue(RaceUtils.isWithinAnHourBefore(nine_am, ten_am));
    }

    @Test
    public void nine01AMIsWithinAnHourOfTenAM() {
        LocalDateTime nine_01_am = DateTime.now().toLocalDateTime().withTime(9, 1, 0, 0);
        LocalDateTime ten_am = DateTime.now().toLocalDateTime().withTime(10, 0, 0, 0);
        assertTrue(RaceUtils.isWithinAnHourBefore(nine_01_am, ten_am));
    }

    @Test
    public void eight59ZAMIsNotWithinAnHourOfTenAM() {
        LocalDateTime eight_59_am = DateTime.now().toLocalDateTime().withTime(8, 59, 0, 0);
        LocalDateTime ten_am = DateTime.now().toLocalDateTime().withTime(10, 0, 0, 0);
        assertFalse(RaceUtils.isWithinAnHourBefore(eight_59_am, ten_am));
    }

    @Test
    public void nine59AMIsWithinAnHourOfTenAM() {
        LocalDateTime nine_59_am = DateTime.now().toLocalDateTime().withTime(9, 59, 0, 0);
        LocalDateTime ten_am = DateTime.now().toLocalDateTime().withTime(10, 0, 0, 0);
        assertTrue(RaceUtils.isWithinAnHourBefore(nine_59_am, ten_am));
    }

    @Test
    public void tenAMIsWithinAnHourOfTenAM() {
        LocalDateTime ten_am = DateTime.now().toLocalDateTime().withTime(10, 0, 0, 0);
        assertTrue(RaceUtils.isWithinAnHourBefore(ten_am, ten_am));
    }

    @Test
    public void ten01AMIsNotWithinAnHourOfTenAM() {
        LocalDateTime ten_01_am = DateTime.now().toLocalDateTime().withTime(10, 1, 0, 0);
        LocalDateTime ten_am = DateTime.now().toLocalDateTime().withTime(10, 0, 0, 0);
        assertFalse(RaceUtils.isWithinAnHourBefore(ten_01_am, ten_am));
    }

    @Test
    public void eleven30amIsWithinAnHourOfTwelve15PM() {
        LocalDateTime eleven_30_am = DateTime.now().toLocalDateTime().withTime(11, 30, 0, 0);
        LocalDateTime twelve_15_pm = DateTime.now().toLocalDateTime().withTime(12, 15, 0, 0);
        assertTrue(RaceUtils.isWithinAnHourBefore(eleven_30_am, twelve_15_pm));
    }

    @Test
    public void eleven25pmIsNotWithinAnHourOfTwelve25AM() {
        LocalDateTime eleven_25_pm = DateTime.now().toLocalDateTime().withTime(23, 25, 0, 0);
        LocalDateTime twelve_25_am = DateTime.now().toLocalDateTime().withTime(12, 25, 0, 0);
        assertFalse(RaceUtils.isWithinAnHourBefore(eleven_25_pm, twelve_25_am));
    }
}