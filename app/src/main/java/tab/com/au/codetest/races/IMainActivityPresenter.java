package tab.com.au.codetest.races;

import tab.com.au.codetest.mvp.IPresenter;

public interface IMainActivityPresenter extends IPresenter<IMainActivityView> {

	public void loadRaces();
}
