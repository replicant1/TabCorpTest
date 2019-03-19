package tab.com.au.codetest.mvp;

public interface IPresenter<V extends IView> {

	/**
	 * Call this from then end of your views' onResume() or onCreateView() method.
	 *
	 * @param view     The IView to be presented by this IPresenter
	 */
	public void onAttachView(V view);

	/**
	 * Call this from your views' onPause() or onDestroy() method. This will terminate
	 * any outstanding async server requests this presenter has initiated.
	 */
	public void onDetachView();
}





