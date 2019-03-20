package tab.com.au.codetest;

import android.content.pm.ActivityInfo;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import tab.com.au.codetest.races.MainActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test for the screen showing a list of races of various types.
 */
@RunWith(AndroidJUnit4.class)
public class RaceListInstrumentedTest {

    public static final int MAX_WAIT_FOR_DATA_MILLIS = 30000;

    @Rule
    public ActivityTestRule<MainActivity_> mActivityRule = new ActivityTestRule(MainActivity_.class);

    @Test
    public void testOrientationChange() {
        // Ensure we start in Portrait orientation
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Race list is displayed, probably empty at this point
        onView(withId(R.id.rv_race_list)).check(matches(isDisplayed()));

        // Wait for data to load into recycler view (no more than 30 seconds)
        CustomActions.waitForViewIdVisible(R.id.rl_race_list_item, MAX_WAIT_FOR_DATA_MILLIS);

        // Switch to Landscape orientation
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Check that data is still displayed in landsape orienation
        CustomActions.waitForViewIdVisible(R.id.rl_race_list_item, MAX_WAIT_FOR_DATA_MILLIS);
    }

    @Test
    public void testSwipeRefresh() {
        // Ensure we start in Portrait orientation
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Race list is displayed, probably empty at this point
        onView(withId(R.id.rv_race_list)).check(matches(isDisplayed()));

        // Wait for data to load into recycler view (no more than 30 seconds)
        CustomActions.waitForViewIdVisible(R.id.rl_race_list_item, MAX_WAIT_FOR_DATA_MILLIS);

        // Swipe to refresh
        onView(withId(R.id.rv_race_list)).perform(ViewActions.swipeDown());

        // Check that data is still displayed in landsape orienation
        CustomActions.waitForViewIdVisible(R.id.rl_race_list_item, MAX_WAIT_FOR_DATA_MILLIS);
    }
}
