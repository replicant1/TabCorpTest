package tab.com.au.codetest.usecase;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rodbailey on 18/3/19.
 */

public abstract class AbstractUseCase<T> {

	private Disposable disposable;

	protected abstract Observable<T> buildObservable();

	public void execute(Observer<T> subscriber) {
//		disposable =
		buildObservable()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(subscriber);
	}

	public void dispose() {
		if ((disposable != null) && (!disposable.isDisposed())) {
			disposable.dispose();
		}
	}

}
