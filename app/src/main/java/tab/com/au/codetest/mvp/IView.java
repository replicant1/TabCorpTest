package tab.com.au.codetest.mvp;

public interface IView {

	public void showProgress(int progressMessageId);

	public void hideProgress();

	public void showError(int errorMessageId);
}
