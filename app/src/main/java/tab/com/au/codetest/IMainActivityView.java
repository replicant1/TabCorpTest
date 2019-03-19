package tab.com.au.codetest;

import tab.com.au.codetest.data.Races;
import tab.com.au.codetest.mvp.IView;

/**
 * Created by rodbailey on 19/3/19.
 */

public interface IMainActivityView extends IView {

	/**
	 * Refresh the view from the data provided
	 */
	public void refresh(Races listData);
}
