package tab.com.au.codetest.races;

import tab.com.au.codetest.data.Races;
import tab.com.au.codetest.mvp.IView;

/**
 * A view onto a list of races of various types
 */
public interface IMainActivityView extends IView {

	/**
	 * Refresh the view from the data provided
	 */
	public void refresh(Races races);
}
