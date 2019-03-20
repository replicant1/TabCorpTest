package tab.com.au.codetest.races;


import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import tab.com.au.codetest.R;
import tab.com.au.codetest.data.Races;
import tab.com.au.codetest.usecase.GetRacesUseCase;

/**
 * Presents a list of races of various types.
 * @see MainActivity
 */
public class MainActivityPresenter implements IMainActivityPresenter {

	private static final String LOG_TAG = MainActivityPresenter.class.getSimpleName();

	private IMainActivityView view;

	/**
	 * Local cache of most recently loaded races. Made static so it survives device
	 * orientation changes. Would be better implemented using ViewModel from Android
	 * Architectural Components because it is lifecycle-aware (this would avoid all the
	 * "if (view != null)" checks in the RacesObserver class below.
	 */
	private static Races racesCache;

	@Override
	public void loadRaces(boolean okToLoadFromCache) {
		Log.d(LOG_TAG, "About to load races from presenter, with okToLoadFromCache=" + okToLoadFromCache);
		if (okToLoadFromCache && racesCache != null) {
			new RacesObserver().onNext(racesCache);
		} else {
			GetRacesUseCase usecase = new GetRacesUseCase();
			usecase.execute(new RacesObserver());
		}
	}

	@Override
	public void onAttachView(IMainActivityView view) {
		this.view = view;
	}

	@Override
	public void onDetachView() {
		view = null;
	}

	/**
	 * Listens for completion or failure of remote retrieval of race data from server
	 */
	private class RacesObserver implements Observer<Races> {

		private String LOG_TAG = RacesObserver.class.getSimpleName();

		@Override
		public void onComplete() {
			Log.d(LOG_TAG, "onComplete");
			if (view != null) {
				view.hideProgress();
			}
		}

		@Override
		public void onError(Throwable e) {
			Log.e(LOG_TAG, "onError: " + e);
			if (view != null) {
				view.hideProgress();
				view.showError(R.string.races_error_msg);
			}
		}

		@Override
		public void onNext(Races races) {
			Log.d(LOG_TAG, "onNext: races=" + races);
			racesCache = races;
			if (view != null) {
				view.hideProgress();
				view.refresh(races);
			}
			Log.d(LOG_TAG, "---- races -----");
			Log.d(LOG_TAG, races.toString());
		}

		@Override
		public void onSubscribe(Disposable d) {
			Log.d(LOG_TAG, "onSubscribe");
			if (view != null) {
				view.showProgress(R.string.races_load_msg);
			}
		}
	}
}
