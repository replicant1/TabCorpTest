package tab.com.au.codetest.mvp;

/**
 * Identifies a "View" in the "Model-View-Presenter" (MVP) sense.
 */
public interface IView {

	/**
	 * Display an indeterminate progress monitor with a given text message.
	 *
	 * @param progressMessageId String resource id of progress message e.g. "Loading data...:
	 */
	public void showProgress(int progressMessageId);

	/**
	 * Hide the indeterminate progress monitor currently showing. If none showing,
	 * has no effect.
	 */
	public void hideProgress();

	/**
	 * Show an error indicator with a given text message.
	 *
	 * @param errorMessageId String resource id of error message.
	 */
	public void showError(int errorMessageId);
}
