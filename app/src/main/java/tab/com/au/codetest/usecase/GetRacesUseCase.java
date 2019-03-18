package tab.com.au.codetest.usecase;

import io.reactivex.Observable;
import tab.com.au.codetest.RaceData;
import tab.com.au.codetest.RacesLoader;
import tab.com.au.codetest.data.Races;

/**
 * Created by rodbailey on 18/3/19.
 */

public class GetRacesUseCase extends AbstractUseCase<Races> {
	@Override
	protected Observable<Races> buildObservable() {
		RacesLoader racesLoader = RaceData.getInstance().getRacesLoader();
		return racesLoader.getRacesObservable();
	}
}
