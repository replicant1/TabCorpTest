package tab.com.au.codetest.races;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import tab.com.au.codetest.R;
import tab.com.au.codetest.data.Races;

/**
 * Presents a list of today's races across various race types. Order of presentation in
 * list = order returned from server.
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements IMainActivityView {

	private static final String LOG_TAG = MainActivity.class.getSimpleName();

	// This could be injected with Dagger etc.
	private final IMainActivityPresenter presenter = new MainActivityPresenter();
	@ViewById(R.id.srl_race_list_swipe_refresh_layout)
	SwipeRefreshLayout swipeRefreshLayout;
	@ViewById(R.id.rv_race_list)
	RecyclerView recyclerView;
	private RaceListAdapter adapter;

	@AfterViews
	void afterViews() {
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		swipeRefreshLayout.setOnRefreshListener(new SwipeListener());
	}

	@Override
	public void hideProgress() {
		if (swipeRefreshLayout != null) {
			swipeRefreshLayout.setRefreshing(false);
		}
	}

	@Override
	protected void onPause() {
		Log.d(LOG_TAG, "Into onPause() with presenter = " + presenter);
		super.onPause();
		presenter.onDetachView();
	}

	protected void onResume() {
		Log.d(LOG_TAG, "Into onResume() with presenter=" + presenter);
		super.onResume();
		presenter.onAttachView(this);

		// The very first time this activity appears, automatically kick off a load of the race data
		if (adapter == null) {
			presenter.loadRaces(true);
		}
	}

	@Override
	public void refresh(Races races) {
		adapter = new RaceListAdapter(races);
		Log.d(LOG_TAG, "Race count in adapter =" + adapter.getItemCount());
		if (recyclerView != null) {
			recyclerView.setAdapter(adapter);
		}
	}

	@Override
	public void showError(int errorMessageId) {
		// TODO: Show modal error dialog.
	}

	@Override
	public void showProgress(int progressMessageId) {
		if (swipeRefreshLayout != null) {
			swipeRefreshLayout.setRefreshing(true);
		}
	}

	private class SwipeListener implements SwipeRefreshLayout.OnRefreshListener {
		@Override
		public void onRefresh() {
			presenter.loadRaces(false);
		}
	}
}
