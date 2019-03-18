package tab.com.au.codetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import tab.com.au.codetest.data.Race;
import tab.com.au.codetest.data.Races;
import tab.com.au.codetest.usecase.GetRacesUseCase;

public class MainActivity extends AppCompatActivity {

	private static final String LOG_TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GetRacesUseCase usecase = new GetRacesUseCase();
		usecase.execute(new Observer<Races>() {
			@Override
			public void onSubscribe(Disposable d) {
				Log.d(LOG_TAG, "onSubscribe - log");
			}

			@Override
			public void onNext(Races races) {
				Log.d(LOG_TAG, "*******************");
				Log.d(LOG_TAG, "onNext. races=" + races);
				if (races != null) {
					List<Race> raceList = races.getRaces();
					if (raceList != null) {
						for (int i = 0; i < raceList.size(); i++) {
							Race race = raceList.get(i);
							Log.d(LOG_TAG, "Meeting=" + race.getMeeting() + ", race name=" + race.getRaceName()
									+ ",race number= " + race.getNumber() + ",start time=" + race.getRaceStartTime());
						}
					}
				}
				Log.d(LOG_TAG, "********************");
			}

			@Override
			public void onError(Throwable e) {
				Log.e(LOG_TAG, "onError: e=" + e);
			}

			@Override
			public void onComplete() {
				Log.d(LOG_TAG, "onComplete");
			}
		});
	}

}
