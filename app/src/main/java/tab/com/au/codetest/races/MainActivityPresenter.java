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

	@Override
	public void loadRaces() {
		Log.d(LOG_TAG, "About to load races");
		GetRacesUseCase usecase = new GetRacesUseCase();
		usecase.execute(new RacesObserver());
	}

	@Override
	public void onAttachView(IMainActivityView view, Object initData) {
		this.view = view;
	}

	@Override
	public void onDetachView() {
		view = null;
	}

	private class RacesObserver implements Observer<Races> {

		private String LOG_TAG = RacesObserver.class.getSimpleName();

		@Override
		public void onComplete() {
			Log.d(LOG_TAG, "onComplete");
			view.hideProgress();
		}

		@Override
		public void onError(Throwable e) {
			Log.e(LOG_TAG, "onError: " + e);
			view.hideProgress();
			view.showError(R.string.races_error_msg);
		}

		@Override
		public void onNext(Races races) {
			Log.d(LOG_TAG, "onNext: races=" + races);
			view.hideProgress();
			view.refresh(races);
			Log.d(LOG_TAG, "---- races -----");
			Log.d(LOG_TAG, races.toString());
		}

		@Override
		public void onSubscribe(Disposable d) {
			Log.d(LOG_TAG, "onSubscribe");
			view.showProgress(R.string.races_load_msg);
		}
	}
}
