package tab.com.au.codetest.races;

import tab.com.au.codetest.mvp.IPresenter;

public interface IMainActivityPresenter extends IPresenter<IMainActivityView> {

	/**
	 * Load races from some data source - perhaps remote.
	 */
	void loadRaces();
}
